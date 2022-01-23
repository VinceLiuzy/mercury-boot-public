import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ViewsRoutingModule} from './views-routing.module';
import {HomeComponent} from './home/home.component';
import {IndexComponent} from './index/index.component';
import {ZorroLibModule} from 'zorro-lib';
import {UserComponent} from './system/user/user.component';
import {SubSystemComponent} from './system/sub-system/sub-system.component';
import {MenuComponent} from './system/menu/menu.component';
import {NzLayoutModule} from 'ng-zorro-antd/layout';
import {DictComponent} from './system/dict/dict.component';
import {RoleComponent} from './system/role/role.component';
import {CategoryComponent} from './system/category/category.component';
import {ParameterComponent} from './system/parameter/parameter.component';
import {NzMessageModule} from 'ng-zorro-antd/message';

@NgModule({
  declarations: [HomeComponent, IndexComponent, UserComponent, SubSystemComponent, MenuComponent, DictComponent,
    RoleComponent, CategoryComponent, ParameterComponent],
  imports: [
    CommonModule,
    ViewsRoutingModule,
    ZorroLibModule,
    NzMessageModule,
    NzLayoutModule
  ]
})
export class ViewsModule {
}
