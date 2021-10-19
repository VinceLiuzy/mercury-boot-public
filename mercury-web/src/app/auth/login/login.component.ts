import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthService} from '../auth.service';
import {NzNotificationService} from 'ng-zorro-antd/notification';
import {DatePipe} from '@angular/common';
import {StorageService} from 'zorro-lib';
import {Md5} from 'ts-md5';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.less']
})
export class LoginComponent implements OnInit {
  passwordVisible = false;
  validateForm!: FormGroup;

  constructor(private fb: FormBuilder, public router: Router, private authService: AuthService, public storage: StorageService,
              private notification: NzNotificationService, private datePipe: DatePipe) {
  }

  submitForm(): void {
    // 重置Token
    this.storage.clear();

    this.authService.signIn({
      username: this.validateForm.value.username,
      password: Md5.hashStr(this.validateForm.value.password).toString()
    }).subscribe((res: any) => {
      if (res.success) {
        // 缓存登陆用户
        this.storage.LOGIN_USER = res.data[0];
        // 缓存菜单
        // this.authService.queryMenus();

        this.notification.success(
          '欢迎',
          this.datePipe.transform(res.timestamp, 'BBBB', '', 'zh') + '好，欢迎回来',
          {nzDuration: 3000}
        );

        this.authService.navigate(this.router);
      }
    });
  }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      username: ['vinceLiu', [Validators.required]],
      password: ['123456', [Validators.required]],
      remember: [true]
    });
  }

}
