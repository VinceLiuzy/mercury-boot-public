import {Component, OnInit} from '@angular/core';
import {MenuOptions, StorageService} from 'zorro-lib';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.less']
})
export class HomeComponent implements OnInit {
  menus: MenuOptions[] = [];

  constructor(private storage: StorageService) {
  }

  ngOnInit(): void {
    this.menus = this.storage.LOGIN_USER_MENU;
  }

}
