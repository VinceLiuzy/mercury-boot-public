import {Component, OnInit, ViewChild} from '@angular/core';
import {
  ApiDictionary,
  ApiFilter,
  ApiParameter,
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
  SearchOperator,
  TreeComponent,
  UtilsService
} from 'zorro-lib';
import {from} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import {NzFormatEmitEvent} from 'ng-zorro-antd/tree';
import {SystemService} from '../../serivce/system.service';
import {startsWith} from 'lodash-es';


@Component({
  selector: 'app-role',
  templateUrl: './role.component.html',
  styleUrls: ['./role.component.less']
})
export class RoleComponent implements OnInit {
  @ViewChild('mzCrud', {static: false}) mzCrud!: CrudComponent; // 角色crud
  @ViewChild('menuDrawer', {static: false}) menuDrawer!: DrawerComponent;
  @ViewChild('userDrawer', {static: false}) userDrawer!: DrawerComponent;
  @ViewChild('tree', {static: false}) tree!: TreeComponent;
  @ViewChild('menuTree', {static: false}) menuTree!: TreeComponent;

  // 子系统树
  querySysParam: ApiParameter = ApiParameter.getInstance('subSystem', false);
  keys: string[] = [];

  // 菜单树
  queryMenuParam: ApiParameter = ApiParameter.getInstance('menu', false);
  queryCheckedParam: ApiParameter = ApiParameter.getInstanceOfUnPaged('rolePermission');
  currentRoleId = '';

  config: CrudConfig = new CrudConfig()
    .setServiceName('role')
    .setShowIndex(true)
    .addField('roleName', FieldConfig.newField('角色名称', InputType.TEXT_INPUT).setRequired(true))
    .addField('roleCode', FieldConfig.newField('角色编码').setRequired(true))
    .addField('description', FieldConfig.newField('备注', InputType.TEXTAREA_INPUT).setDisplay(DisplayType.BLOCK))
    .addField('createTime', FieldConfig.newField('创建时间', InputType.DATE_PICKER))
    .addField('lastModifiedTime', FieldConfig.newField('更新时间', InputType.DATE_TIME_PICKER))
    .setFieldsToEdit('roleName', 'roleCode', 'description')
    .setFieldsInSearch(
      {
        key: 'roleName',
        operator: SearchOperator.LIKE
      });

  userConfig: CrudConfig = new CrudConfig()
    .setServiceName('user')
    .setNzSpan(8)
    .addField('username', FieldConfig.newField('用户账号', InputType.TEXT_INPUT)
      .setRequired(true)
      .setMinlength(6)
      .setMaxlength(18)
      .setUpdatable(false)
    )
    .addField('realName', FieldConfig.newField('用户姓名'))
    .addField('avatar', FieldConfig.newField('头像'))
    .addField('birthday', FieldConfig.newField('生日', InputType.DATE_PICKER))
    .addField('sex', FieldConfig.newField('性别', InputType.SELECT)
      .setDictionary(ApiDictionary.getInstance('dict', 'sex')))
    .addField('phone', FieldConfig.newField('手机号码'))
    .addField('email', FieldConfig.newField('邮箱'))
    .addField('status', FieldConfig.newField('状态', InputType.SELECT)
      .setDictionary(ApiDictionary.getInstance('dict', 'user_status')))
    .setFieldsInSearch(
      {key: 'username', operator: SearchOperator.LIKE, alias: '账号'},
    )
    .setFieldsInList('username', 'realName', 'status')
    .setFieldsToEdit('username', 'realName', 'sex', 'phone', 'birthday', 'email');

  constructor(private utilsService: UtilsService, private systemService: SystemService) {
  }

  cancel(): void {
    this.menuDrawer.close();
  }

  // 保存角色权限
  submitForm(isClose: boolean): void {
    const menuList: string[] = [];

    // 全选
    from(this.menuTree.nzTreeComponent.getCheckedNodeList())
      .subscribe((item: any) => {
        menuList.push(item.key);
      });

    // 查找children节点
    if (menuList.length > 0) {
      from(this.menuTree.dataSource)
        .pipe(filter((item: any) => this.utilsService.contains(menuList, item.id)))
        .subscribe(item => {
          if (item.parentId == null) {
            // 根菜单
            from(this.menuTree.dataSource)
              .pipe(filter((child: any) => child.parentId != null && startsWith(child.path, item.id)))
              .subscribe(child => {
                menuList.push(child.key);
              });
          } else {
            // 子菜单
            from(this.menuTree.dataSource)
              .pipe(filter((child: any) => startsWith(child.path, item.path + '-' + item.id)))
              .subscribe(child => {
                menuList.push(child.key);
              });
          }
        });

      // 半选
      from(this.menuTree.nzTreeComponent.getHalfCheckedNodeList())
        .pipe(map((item: any) => item.key))
        .subscribe(k => {
          menuList.push(k);
        });
    }

    this.systemService.saveRolePermission({roleId: this.currentRoleId, menuList})
      .subscribe(() => {
        if (isClose) {
          this.menuDrawer.close();
        }
      });
  }

  // 子系统加载完成事件
  onComplete = () => {
    // 子系统树加载成功, 默认选择第一个节点
    this.keys = this.tree.defaultSelectedKeys = [this.tree.nodes[0][this.tree.keyColumn]];
    this.loadData(this.tree.defaultSelectedKeys[0]);
  }

  // 角色[crud]过滤器
  loadData = (key: string) => {
    this.config.addFilters(ApiFilter.getInstance('systemCode', key));

    this.mzCrud.loadData();
  }

  // 子系统树节点单击事件
  onClickNode(event: NzFormatEmitEvent): void {
    const tmp: any[] = event.keys || [];

    if (tmp.length <= 0 || tmp[0] === this.keys[0]) {
      return;
    }

    this.keys = event.keys || [];
    // 角色分页查询
    this.loadData(this.keys[0]);
  }

  ngOnInit(): void {
    // 页面新增按钮
    this.config.getPageAddBtn().subscribe((res: any) => {
      res.button.setCallBack((param: any) => {
        this.config.editData.systemCode = this.keys[0];
      });
    });

    this.config
      .resetRowBtn()
      .addRowBtn(
        // 用户列表
        CrudButtonConfig.newCrudButton('users', 1, ButtonConfig.newButton('用户')
          .setCallBack((param: IParam) => {

            this.userDrawer.open('用户');

            // this.menuDrawer.open('角色权限配置')
            //
            // this.parentId = param.rowData['id'];
            // this.itemConfig.addFilters(ApiFilter.getInstance('dictId', param.rowData['id']));
          }).setType(ButtonType.DEFAULT)),

        this.config.getMoreBtn(2)
      );

    // 更多
    this.config.getRowMoreBtn().subscribe((res: any) => {
      res.button.addMenuItems(
        // 授权
        ButtonConfig.newButton('授权')
          .setCallBack((param: IParam) => {
            this.queryMenuParam.filters = [ApiFilter.getInstance('systemCode', this.keys[0])];
            this.currentRoleId = param.rowData.id;
            this.queryCheckedParam.filters = [ApiFilter.getInstance('roleId', this.currentRoleId)];

            this.menuDrawer.open('角色权限配置');
          }),
        // 编辑
        this.config.getEditBtn(1, null).button,
        // 删除
        this.config.getDeleteBtn(2, null).button
      );
    });

    this.userConfig
      .resetPageBtn()
      .addPageBtn(
        CrudButtonConfig.newCrudButton('addUserRole', 1, ButtonConfig.newButton('添加已用用户', 'plus')),
        CrudButtonConfig.newCrudButton('addUser', 1, ButtonConfig.newButton('用户录入', 'plus')),
        this.userConfig.getBatchDeleteConfig()
      );
  }

}
