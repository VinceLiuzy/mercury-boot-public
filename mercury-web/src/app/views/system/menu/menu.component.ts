import {Component, OnInit, ViewChild} from '@angular/core';
import {
  AlignType,
  ApiDictionary,
  ApiFilter,
  ApiParameter,
  ApiSort,
  CrudComponent,
  CrudConfig,
  CrudService,
  DisplayType,
  FieldConfig,
  InputType,
  IParam,
  ModeType,
  SearchOrder,
  TableType,
  TreeComponent
} from 'zorro-lib';
import {NzMessageService} from 'ng-zorro-antd/message';
import {NzFormatEmitEvent} from 'ng-zorro-antd/tree';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.less']
})
export class MenuComponent implements OnInit {
  @ViewChild('mzCrud', {static: false}) mzCrud!: CrudComponent;
  @ViewChild('tree', {static: false}) tree!: TreeComponent;

  keys: string[] = [];

  config: CrudConfig = new CrudConfig()
    .setServiceName('menu')
    .setDeleteUrl('menu/deleteByIds')
    .setTableType(TableType.EXPAND_TABLE)
    .setBordered(false)
    .setPageFlag(false)
    .addField('name', FieldConfig.newField('菜单名称', InputType.TEXT_INPUT)
      .setRequired(true)
      .setDisplay(DisplayType.BLOCK)
      .setAlign(AlignType.LEFT))
    .addField('parentId', FieldConfig.newField('上级菜单', InputType.TREE_SELECT)
      .setRequired(true)
      .setDictionary(ApiDictionary.getInstance('menu', 'sysMenu'))
      .setDisplay(DisplayType.BLOCK))
    .addField('icon', FieldConfig.newField('icon', InputType.ICON_PICKER).setDisplay(DisplayType.BLOCK).setAlign(AlignType.LEFT))
    .addField('url', FieldConfig.newField('路由').setDisplay(DisplayType.BLOCK).setAlign(AlignType.LEFT))
    .addField('sortNo', FieldConfig.newField('排序', InputType.NUMBER_INPUT)
      .setNumberInputOption({
        min: 0,
        max: 999,
        step: 0.1
      })
      .setDisplay(DisplayType.BLOCK).setAlign(AlignType.LEFT)
    )
    .addField('systemCode', FieldConfig.newField('所属系统', InputType.SELECT)
      .setRequired(true)
      .setDictionary(ApiDictionary.getInstance('subSystem', 'subSystem'))
      .setDisplay(DisplayType.BLOCK)
      .setAlign(AlignType.LEFT)
      .setAddable(false)
      .setUpdatable(false)
    )
    .addField('menuType', FieldConfig.newField('菜单类型', InputType.RADIO_GROUP)
      .setDictionary(ApiDictionary.getInstance('dict', 'menu_type'))
      .setRequired(true)
      .setDefaultValue('0')
      .setFeedback(false)
      .setDictionary(ApiDictionary.getInstance('dict', 'menu_type'))
      .setDisplay(DisplayType.BLOCK))
    .addField('open', FieldConfig.newField('展开', InputType.SWITCH)
      .setDefaultValue(false)
      .setSwitchOption({
        checked: '是',
        unChecked: '否'
      })
    )
    .addField('selected', FieldConfig.newField('选中', InputType.SWITCH)
      .setDefaultValue(false)
      .setSwitchOption({
        checked: '是',
        unChecked: '否'
      })
    )
    .addField('disabled', FieldConfig.newField('禁用', InputType.SWITCH)
      .setDefaultValue(false)
      .setSwitchOption({
        checked: '是',
        unChecked: '否'
      })
    )
    .setFieldsInList('name', 'icon', 'url', 'sortNo')
    .setFieldsToEdit('menuType', 'name', 'systemCode', 'parentId', 'icon', 'url', 'sortNo', 'open', 'selected', 'disabled')
    .setSorts(ApiSort.getInstance('sortNo', SearchOrder.ASC))
    .setLoadData(false);

  queryParam: ApiParameter = ApiParameter.getInstance('subSystem', false);

  constructor(public crudService: CrudService, private message: NzMessageService) {
    this.config.fields['parentId']
      .setEditWhen(() => this.config.editData['menuType'] === '1');

    // 提交前处理数据
    this.config.setBeforeCommit(() => {
      if (this.config.editData['menuType'] === '0') {
        // 一级菜单时 parentId = null
        this.config.editData['parentId'] = null;
      } else if (this.config.editData['menuType'] === '1') {
        // 子菜单
        if (this.config.editData['parentId'] === this.config.editData['id']) {
          this.message.warning('上级菜单不能选择自己!');

          return false;
        }
      }

      return true;
    });

    // 新增或编辑后重新加载字典
    this.config.setCommitCallBack(() => {
      this.crudService.loadDictionary(this.config);
    });

    // 页面删除按钮
    this.config.getPageDelBtn().subscribe((res: any) => {
      res.button.setCallBack((param: IParam) => {
        this.crudService.loadDictionary(this.config);
      });
    });

    // 行按钮配置
    this.config
      .resetRowBtn()
      .addRowBtn(
        this.config.getEditBtn(1, null),
        this.config.getMoreBtn(2)
      );

    // 更多
    this.config.getRowMoreBtn().subscribe((res: any) => {
      // 查看详情
      res.button.addMenuItems(
        this.config.getEditBtn(1, null, '详情').button
          .setCallBack(param => {
            this.config.mode = ModeType.VIEW;
          }),
        // 添加子菜单
        this.config.getAddBtn(2, '', '添加下级').button
          .setCallBack((param: IParam) => {
            this.config.editData['menuType'] = '1';
            this.config.editData['parentId'] = param.rowData.id;
          }),
        // 行删除按钮
        this.config.getDeleteBtn(3, null).button
          .setCallBack((param: IParam) => {
            this.crudService.loadDictionary(this.config);
          })
      );
    });
  }

  onClickNode(event: NzFormatEmitEvent): void {
    if (event.keys == null || event.keys.length <= 0 || event.keys[0] === this.keys[0]) {
      return;
    }

    this.keys = event.keys;
    this.loadData(this.keys[0]);
  }

  onComplete = () => {
    // 树加载完成执行, 默认选择第一个节点
    this.tree.defaultSelectedKeys = [this.tree.nodes[0][this.tree.keyColumn]];
    this.loadData(this.tree.defaultSelectedKeys[0]);
  }

  loadData = (key: string) => {
    this.config.addFilters(ApiFilter.getInstance('systemCode', key));
    this.config.fields['parentId'].dictionary.setFilters(ApiFilter.getInstance('systemCode', key));
    this.config.fields['systemCode'].setDefaultValue(key);

    this.mzCrud.loadData();
  }

  ngOnInit(): void {
  }

}
