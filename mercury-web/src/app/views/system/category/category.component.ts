import {Component, OnInit} from '@angular/core';
import {CrudConfig, DisplayType, FieldConfig, InputType, TableType} from 'zorro-lib';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.less']
})
export class CategoryComponent implements OnInit {
  config: CrudConfig = new CrudConfig()
    .setServiceName('sysCategory')
    .setTableType(TableType.EXPAND_TABLE)
    .setBordered(false)
    .addField('name', FieldConfig.newField('分类名称', InputType.TEXT_INPUT)
      .setRequired(true)
      .setDisplay(DisplayType.BLOCK))
    .addField('code', FieldConfig.newField('分类编码'))
    .setFieldsInList('name', 'code')
    .setFieldsToEdit('name', 'code');

  constructor() {
  }

  ngOnInit(): void {
  }

}
