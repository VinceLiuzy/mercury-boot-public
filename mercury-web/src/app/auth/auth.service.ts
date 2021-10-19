import {Inject, Injectable} from '@angular/core';
import {APP_CONFIG, AppConfig, HttpService, StorageService} from 'zorro-lib';
import {from, Observable} from 'rxjs';
import {Router} from '@angular/router';
import {first} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpService: HttpService, @Inject(APP_CONFIG) private config: AppConfig, public storage: StorageService) {
  }

  public signIn(data: { username: string, password: string }): Observable<object> {
    return this.httpService.signIn(this.config.BASE_SERVICE + 'auth/signIn', data);
  }

  queryMenus(): Promise<any> {
    return new Promise((resolve: any) => {
      this.httpService.post(this.config.BASE_SERVICE + 'menu/list')
        .subscribe((res: any) => {
          this.storage.LOGIN_USER_MENU = res[`data`].userMenus;
          this.storage.LOGIN_USER_RAW_MENU = res[`data`].rawMenus;

          resolve();
        });
    });
  }

  async navigate(router: Router): Promise<any> {
    await this.queryMenus();

    if (this.storage.LOGIN_USER_TABS == null) {
      this.storage.LOGIN_USER_TABS = {
        index: 0,
        tabs: [this.storage.LOGIN_USER_MENU[0]]
      };
    } else {
      from(this.storage.LOGIN_USER_TABS.tabs)
        .pipe(first(item => this.storage.LOGIN_USER_RAW_MENU[item.id] == null, false))
        .subscribe(res => {
          if (res) {
            // 用户菜单改变
            this.storage.LOGIN_USER_TABS = {
              index: 0,
              tabs: [this.storage.LOGIN_USER_MENU[0]]
            };
          }
        });
    }

    const url = this.storage.LOGIN_USER_TABS.tabs[this.storage.LOGIN_USER_TABS.index].url;

    return router.navigate([url]);
  }

  // queryMenus(): Observable<object> {
  //   return this.httpService.simplePost(this.config.BASE_SERVICE + 'menu/list');
  // this.storage.LOGIN_USER_MENU = [
  //   {
  //     level: 1,
  //     title: '首页',
  //     icon: 'home',
  //     url: '/views/index',
  //     open: true,
  //     selected: true,
  //     disabled: false,
  //   },
  //   {
  //     level: 1,
  //     title: '系统管理',
  //     icon: 'mail',
  //     open: true,
  //     selected: false,
  //     disabled: false,
  //     children: [
  //       {
  //         level: 2,
  //         title: 'Group 1',
  //         icon: 'bars',
  //         open: false,
  //         selected: false,
  //         disabled: false,
  //         children: [
  //           {
  //             level: 3,
  //             title: 'Option 1',
  //             selected: false,
  //             disabled: false,
  //           },
  //           {
  //             level: 3,
  //             title: 'Option 2',
  //             selected: false,
  //             disabled: true
  //           }
  //         ]
  //       },
  //       {
  //         level: 2,
  //         title: 'Group 2',
  //         icon: 'bars',
  //         selected: false,
  //         disabled: false
  //       },
  //       {
  //         level: 2,
  //         title: 'Group 3',
  //         icon: 'bars',
  //         selected: false,
  //         disabled: false
  //       }
  //     ]
  //   },
  //   {
  //     level: 1,
  //     title: '系统监控',
  //     icon: 'team',
  //     open: false,
  //     selected: false,
  //     disabled: false,
  //     children: [
  //       {
  //         level: 2,
  //         title: '性能监控',
  //         icon: 'user',
  //         selected: false,
  //         disabled: false
  //       },
  //       {
  //         level: 2,
  //         title: '日志管理',
  //         icon: 'user',
  //         selected: false,
  //         disabled: false
  //       }
  //     ]
  //   },
  //   {
  //     level: 1,
  //     title: '系统设置',
  //     icon: 'setting',
  //     open: false,
  //     selected: false,
  //     disabled: false,
  //     children: [
  //       {
  //         level: 2,
  //         title: '用户管理',
  //         icon: 'appstore',
  //         url: '/views/isystem/user',
  //         open: false,
  //         selected: false,
  //         disabled: false
  //       },
  //       {
  //         level: 2,
  //         title: '菜单管理',
  //         icon: 'menu',
  //         url: '/views/isystem/menu',
  //         selected: false,
  //         disabled: false
  //       },
  //       {
  //         level: 2,
  //         title: '数据字典',
  //         icon: 'appstore',
  //         url: '/views/isystem/dict',
  //         selected: false,
  //         disabled: false
  //       },
  //       {
  //         level: 2,
  //         title: '分类字典',
  //         icon: 'appstore',
  //         url: '/views/isystem/category',
  //         selected: false,
  //         disabled: false
  //       },
  //       {
  //         level: 2,
  //         title: '角色管理',
  //         icon: 'bars',
  //         url: '/views/isystem/role',
  //         selected: false,
  //         disabled: false
  //       },
  //       {
  //         level: 2,
  //         title: '数据权限',
  //         icon: 'bars',
  //         url: '/views/isystem/promission',
  //         selected: false,
  //         disabled: false
  //       },
  //       {
  //         level: 2,
  //         title: '系统参数',
  //         icon: 'bars',
  //         url: '/views/isystem/parameter',
  //         selected: false,
  //         disabled: false
  //       },
  //       {
  //         level: 2,
  //         title: '在线用户',
  //         icon: 'bars',
  //         url: '/views/isystem/session',
  //         selected: false,
  //         disabled: false
  //       },
  //       {
  //         level: 2,
  //         title: '系统管理',
  //         icon: 'bars',
  //         url: '/views/isystem/subSystem',
  //         selected: false,
  //         disabled: false
  //       }
  //     ]
  //   }
  // ];
  // }
}
