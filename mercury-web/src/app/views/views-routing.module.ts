import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {IndexComponent} from './index/index.component';
import {UserComponent} from './system/user/user.component';
import {PageNotFoundComponent} from 'zorro-lib';
import {SubSystemComponent} from './system/sub-system/sub-system.component';
import {MenuComponent} from './system/menu/menu.component';
import {DictComponent} from './system/dict/dict.component';
import {RoleComponent} from './system/role/role.component';
import {CategoryComponent} from './system/category/category.component';
import {ParameterComponent} from './system/parameter/parameter.component';

const routes: Routes = [{
  path: '',
  component: HomeComponent,
  children: [
    {path: 'index', component: IndexComponent, pathMatch: 'full'},
    {
      path: 'sys',
      children: [
        {path: 'menu', component: MenuComponent, pathMatch: 'full'},
        {path: 'user', component: UserComponent, pathMatch: 'full'},
        {path: 'role', component: RoleComponent, pathMatch: 'full'},
        {path: 'subSystem', component: SubSystemComponent, pathMatch: 'full'},
        {path: 'dict', component: DictComponent, pathMatch: 'full'},
        {path: 'category', component: CategoryComponent, pathMatch: 'full'},
        {path: 'parameter', component: ParameterComponent, pathMatch: 'full'},
      ]
    },
    {path: '**', component: PageNotFoundComponent}
  ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ViewsRoutingModule { }
