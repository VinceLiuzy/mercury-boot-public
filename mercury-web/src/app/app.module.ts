import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NZ_I18N, zh_CN} from 'ng-zorro-antd/i18n';
import {DatePipe, registerLocaleData} from '@angular/common';
import zh from '@angular/common/locales/zh';
import localeFrExtra from '@angular/common/locales/extra/zh';
import {ZorroLibModule} from 'zorro-lib';
import {APP_DEV_DI_CONFIG} from '../app.config';

registerLocaleData(zh, localeFrExtra);

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ZorroLibModule.forRoot(APP_DEV_DI_CONFIG)
  ],
  providers: [
    {provide: NZ_I18N, useValue: zh_CN},
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
