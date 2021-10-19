import {Component, Inject, OnInit} from '@angular/core';
import {
  ApiDictionary,
  APP_CONFIG,
  AppConfig,
  CrudConfig,
  FieldConfig,
  HttpService,
  InputType,
  SearchOperator,
  ValidateType
} from 'zorro-lib';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.less']
})
export class UserComponent implements OnInit {
  config: CrudConfig = new CrudConfig()
    .setServiceName('user')
    .setShowIndex(true)
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
    .addField('phone', FieldConfig.newField('手机号码')
      .setValidate({validateType: ValidateType.REGEXP, regExp: /^1[3-9]\d{9}$/, message: '手机号码格式不正确'})
      .setMaxlength(11)
    )
    .addField('email', FieldConfig.newField('邮箱')
      .setValidate({validateType: ValidateType.EMAIL, message: '邮箱格式不正确'})
    )
    .addField('userStatus', FieldConfig.newField('状态', InputType.SELECT)
      .setDictionary(ApiDictionary.getInstance('dict', 'user_status')))
    .setFieldsInSearch(
      {key: 'username', operator: SearchOperator.LIKE, alias: '账号'},
      {key: 'userStatus'}
    )
    .setFieldsInList('username', 'realName', 'avatar', 'sex', 'birthday', 'phone', 'email', 'userStatus')
    .setFieldsInAdvancedSearch(
      {key: 'email', operator: SearchOperator.LIKE},
      {key: 'phone'},
      {key: 'sex'})
    .setFieldsToEdit('username', 'realName', 'sex', 'phone', 'birthday', 'email');

  constructor(private http: HttpService, @Inject(APP_CONFIG) private appConfig: AppConfig) {
  }

  ngOnInit(): void {
  }

}
