import {Component, OnInit} from '@angular/core';

import {Router} from '@angular/router';
import {AuthenticationService} from '@app/_core/services';
import {I18nService} from "@dcc-commons-ng/services";
import {Location} from "@angular/common";
import {UtilityService} from "@core/utilities/utility.service";

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
    private authenticationService: AuthenticationService,
    private utilityService: UtilityService) {}

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  setLanguage(language: string) {
    this.i18nService.language = language;
  }

  setBackUrl(url: string) {
    this.utilityService.backUrl = url;
  }

  ngOnInit() {
    this.currentUser = this.authenticationService.currentUser

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

  goBack() {
    if(this.utilityService.backUrl)
      this.router.navigate([this.utilityService.backUrl]);
    else
      this.location.back();
  }
}
