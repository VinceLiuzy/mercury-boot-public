import {Inject, Injectable} from '@angular/core';
import {APP_CONFIG, AppConfig, HttpService} from 'zorro-lib';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SystemService {

  constructor(private http: HttpService, @Inject(APP_CONFIG) private config: AppConfig) {
  }

  /** 角色分配 */
  roleAssign(param: { userId: string, systemCode: string, roleCodes: any[] }): Observable<object> {
    return this.http.simplePost(`${this.config.BASE_SERVICE}user/roleAssign`, param);
  }

  /** 重置密码 */
  resetPassword(param: { id: string }): Observable<object> {
    return this.http.simplePost(`${this.config.BASE_SERVICE}user/resetPassword`, param);
  }

  /** 保存角色权限 */
  saveRolePermission(param: { roleId: string, menuList: string[] }): Observable<object> {
    return this.http.simplePost(`${this.config.BASE_SERVICE}role/saveRolePermission`, param);
  }

  /** 查询角色权限 */
  queryRolePermission(param: { id: string }): Observable<object> {
    return this.http.simplePost(`${this.config.BASE_SERVICE}role/queryRolePermission`, param);
  }

  /** 剔除会话 */
  kickOut(param: { id: string }): Observable<object> {
    return this.http.simplePost(`${this.config.BASE_SERVICE}auth/kickOut`, param);
  }
}
