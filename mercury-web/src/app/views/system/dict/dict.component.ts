import {Component, OnInit, ViewChild} from '@angular/core';
import {
  AlignType,
  ApiFilter,
  ButtonConfig,
  ButtonType,
  CrudButtonConfig,
  CrudComponent,
  CrudConfig,
  DisplayType,
  DrawerComponent,
  FieldConfig,
  InputType,
  IParam,
  ModalTableComponent,
  SearchOperator
} from 'zorro-lib';

@Component({
  selector: 'app-dict',
  templateUrl: './dict.component.html',
  styleUrls: ['./dict.component.less']
})
export class DictComponent implements OnInit {
  @ViewChild('drawer', {static: false}) drawer!: DrawerComponent;
  @ViewChild('crud', {static: false}) crudComponent!: CrudComponent;
  @ViewChild('modalTable', {static: false}) modalTable!: ModalTableComponent;
  @ViewChild('crudItem', {static: false}) crudItemComponent!: CrudComponent;

  parentId = '';

  config: CrudConfig = new CrudConfig()
    .setServiceName('dict')
    .setShowCheckbox(false)
    .setBordered(false)
    .addField('dictName', FieldConfig.newField('字典名称', InputType.TEXT_INPUT).setAlign(AlignType.LEFT).setRequired(true))
    .addField('dictCode', FieldConfig.newField('字典编号').setAlign(AlignType.LEFT))
    .addField('description', FieldConfig.newField('描述', InputType.TEXTAREA_INPUT).setAlign(AlignType.LEFT)
      .setDisplay(DisplayType.BLOCK))
    .setFieldsInSearch({key: 'dictName', operator: SearchOperator.LIKE}, {key: 'dictCode'});

  itemConfig: CrudConfig = new CrudConfig()
    .setServiceName('dictItem')
    .setShowCheckbox(false)
    .setShowIndex(true)
    .setBordered(false)
    // .setScroll()
    .setNzSpan(8)
    .addField('itemText', FieldConfig.newField('名称', InputType.TEXT_INPUT)
      .setBreakWord(true)
      .setAlign(AlignType.LEFT)
      .setRequired(true)
    )
    .addField('itemValue', FieldConfig.newField('数据值').setAlign(AlignType.CENTER).setBreakWord(true).setRequired(true))
    .addField('description', FieldConfig.newField('描述', InputType.TEXTAREA_INPUT).setAlign(AlignType.CENTER)
      .setDisplay(DisplayType.BLOCK)
    )
    .addField('sortNo', FieldConfig.newField('排序值', InputType.NUMBER_INPUT))
    .addField('status', FieldConfig.newField('状态', InputType.SWITCH).setAlign(AlignType.CENTER)
      .setSwitchOption({
        checked: '启用',
        unChecked: '禁用'
      })
      .setSearchType(InputType.SELECT)
      .setSelectOption([
        {
          id: '',
          itemText: '启用',
          itemValue: true,
          parentId: ''
        },
        {
          id: '',
          itemText: '禁用',
          itemValue: false,
          parentId: ''
        }
      ])
    )
    .setFieldsInList('itemText', 'itemValue', 'status')
    .setFieldsInSearch({key: 'itemText'}, {key: 'status', value: true});

  userConfig: CrudConfig = new CrudConfig()
    .setServiceName('user')
    .setBodyStyle({padding: '0', background: '#ffffff', 'min-height': '100px'})
    .addField('username', FieldConfig.newField('用户账号', InputType.TEXT_INPUT))
    .addField('realName', FieldConfig.newField('用户姓名'))
    .resetPageBtn()
    .resetRowBtn();

  selectedUserConfig: CrudConfig = new CrudConfig()
    .setServiceName('user')
    .setLoadData(false)
    .setBodyStyle({padding: '0', background: '#ffffff', 'min-height': '100px'})
    .addField('username', FieldConfig.newField('用户账号', InputType.TEXT_INPUT))
    .resetPageBtn()
    .resetRowBtn();

  constructor() {
    // 字典行操作配置
    this.config.resetRowBtn()
      .addRowBtn(
        this.config.getEditBtn(1, 'edit'),
        // 查看字典配置
        CrudButtonConfig.newCrudButton('setting', 2, ButtonConfig.newButton('字典配置', 'setting')
          .setExecutor((rowData: any) => {
              return Promise.resolve(rowData);
            }
          ).setCallBack((param: IParam) => {
            this.drawer.open('字典列表');

            this.parentId = param.rowData.id;

            this.itemConfig.setFieldsInSearch({key: 'itemText'}, {key: 'status', value: true})
              .addFilters(ApiFilter.getInstance('dictId', param.rowData.id));
          }).setType(ButtonType.DEFAULT)),

        CrudButtonConfig.newCrudButton('setting', 2, ButtonConfig.newButton('test', 'setting')
          .setExecutor((rowData: any) => {
              return Promise.resolve(rowData);
            }
          ).setCallBack((param: IParam) => {

            this.modalTable.createModal();
          }).setType(ButtonType.DEFAULT)),


        // 删除按钮
        this.config.getDeleteBtn(3)
      );

    // 新增字典项方法, 传入字典[ID]
    const addBtnConfig: CrudButtonConfig = this.itemConfig.getAddBtn();
    addBtnConfig.button.setExecutor((data: any) => {
      return new Promise<any>((resolve, reject) => {
        this.crudItemComponent.config.editData = {};
        this.crudItemComponent.crudChild.openEdit(data.name);

        resolve(this);
      });
    }).setCallBack((param: IParam) => {
      this.drawer.drawerRef.nzOffsetX = 180;
      param.context.crudConfig.editData.dictId = this.parentId;

      param.context.drawer.drawerRef.afterClose.subscribe(() => {
        this.drawer.drawerRef.nzOffsetX = 0;
      });
    });

    this.itemConfig.resetPageBtn().addPageBtn(addBtnConfig);

    // 编辑字典项方法
    this.itemConfig.getButton(this.itemConfig.rowBtnKey, this.itemConfig.editBtnKey).subscribe(res => {
      res.button
        .setCallBack((param: IParam) => {
          this.drawer.drawerRef.nzOffsetX = 180;

          param.context.drawer.drawerRef.afterClose.subscribe(() => {
            this.drawer.drawerRef.nzOffsetX = 0;
          });
        });
    });
  }

  ngOnInit(): void {
  }

}
