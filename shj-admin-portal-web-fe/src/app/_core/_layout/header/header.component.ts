import {Component, OnInit} from '@angular/core';

import {Router} from '@angular/router';
import {AuthenticationService} from '@app/_core/services';
import {I18nService} from "@dcc-commons-ng/services";
import {Location} from "@angular/common";
import {NavigationService} from "@core/utilities/navigation.service";
import {LangChangeEvent, TranslateService} from "@ngx-translate/core";
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  currentUser: any;

  constructor(private location: Location,
              public router: Router,
              private i18nService: I18nService,
              private authenticationService: AuthenticationService, private titleService: Title,
              private translate: TranslateService) {
  }


  ngOnInit() {
    this.currentUser = this.authenticationService.currentUser
    this.titleService.setTitle(this.translate.instant('general.app_title'));
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

  viewCurrentUserProfile() {
    const userId = this.authenticationService.currentUser.id;
    this.router.navigate(["/users/details/" + userId], {queryParams: {myProfile: true}});
  }

  isSupervisor(): boolean {
    return true;
  }

  setLanguage(language: string) {
    this.i18nService.language = language;
    this.translate.onLangChange.subscribe((event: LangChangeEvent) => {
      this.translate.get('general.app_title').subscribe((res: string) => {
        this.titleService.setTitle(res);
      });
    });
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

}
