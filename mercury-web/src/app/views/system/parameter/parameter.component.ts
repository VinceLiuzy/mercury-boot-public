import {Component, OnInit} from '@angular/core';
import {AlignType, CrudConfig, DisplayType, FieldConfig, InputType, SearchOperator} from 'zorro-lib';

@Component({
  selector: 'app-parameter',
  templateUrl: './parameter.component.html',
  styleUrls: ['./parameter.component.less']
})
export class ParameterComponent implements OnInit {

  config: CrudConfig = new CrudConfig()
    .setServiceName('sysParameter')
    .setShowCheckbox(false)
    .setBordered(false)
    .addField('paramName', FieldConfig.newField('参数名称', InputType.TEXT_INPUT).setAlign(AlignType.LEFT).setRequired(true))
    .addField('paramValue', FieldConfig.newField('参数值').setAlign(AlignType.LEFT).setRequired(true))
    .addField('description', FieldConfig.newField('描述', InputType.TEXTAREA_INPUT).setAlign(AlignType.LEFT)
      .setDisplay(DisplayType.BLOCK))
    .setFieldsInSearch({key: 'paramName', operator: SearchOperator.LIKE});

  constructor() {
  }

  ngOnInit(): void {
  }

}
