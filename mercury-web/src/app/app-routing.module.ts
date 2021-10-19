import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuard, PageNotFoundComponent} from 'zorro-lib';

const routes: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {
    path: 'login',
    loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule),
  },
  {
    path: 'views',
    loadChildren: () => import('./views/views.module').then(m => m.ViewsModule),
    data: {preload: true},
    canLoad: [AuthGuard],
    canActivateChild: [AuthGuard],
    canActivate: [AuthGuard],
  },
  {path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
