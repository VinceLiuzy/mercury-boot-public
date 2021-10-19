import {Component, OnInit} from '@angular/core';
import {CrudConfig, DisplayType, FieldConfig, InputType, SearchOperator} from 'zorro-lib';

@Component({
  selector: 'app-sub-system',
  templateUrl: './sub-system.component.html',
  styleUrls: ['./sub-system.component.less']
})
export class SubSystemComponent implements OnInit {
  config: CrudConfig = new CrudConfig()
    .setServiceName('subSystem')
    .addField('name', FieldConfig.newField('系统名称', InputType.TEXT_INPUT)
      .setDisplay(DisplayType.BLOCK)
      .setRequired(true)
    )
    .addField('systemCode', FieldConfig.newField('系统编号')
      .setDisplay(DisplayType.BLOCK)
      .setRequired(true)
      .setUpdatable(false)
    )
    .addField('description', FieldConfig.newField('备注', InputType.TEXTAREA_INPUT)
      .setDisplay(DisplayType.BLOCK)
    )
    .addField('createTime', FieldConfig.newField('创建时间', InputType.DATE_PICKER))
    .setFieldsInSearch(
      {key: 'name', operator: SearchOperator.LIKE},
      {key: 'systemCode'}
    )
    .setFieldsToEdit('name', 'systemCode', 'description');

  constructor() {
  }

  ngOnInit(): void {
  }

}
