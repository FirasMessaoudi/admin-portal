import {Component, OnInit} from '@angular/core';

import {Router} from '@angular/router';
import {AuthenticationService} from '@app/_core/services';
import {I18nService} from "@dcc-commons-ng/services";
import {EAuthority} from "@model/enum/authority.enum";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  currentUser: any;
  public isMenuCollapsed = false;
  links: {}[];

  constructor(
    public router: Router,
    private i18nService: I18nService,
    private authenticationService: AuthenticationService
  ) {
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  setLanguage(language: string) {
    this.i18nService.language = language;
  }

  ngOnInit() {
    this.currentUser = this.authenticationService.currentUser
    this.links = [
      {
        title: 'home.title',
        roles: [EAuthority.ADMIN_DASHBOARD],
        display: false,
        routerLink: '/',
      },
      {
        title: 'user-management.title',
        roles: [EAuthority.USER_MANAGEMENT],
        display: false,
        routerLink: '/users/list',
      },
      {
        title: 'role-management.title',
        roles: [EAuthority.ROLE_MANAGEMENT],
        display: false,
        routerLink: '/roles/list',
      },
      {
        title: 'card-management.title',
        roles: [EAuthority.USER_MANAGEMENT],
        display: false,
        routerLink: '/cards/list',
      }
    ];
    // filtering access according to connected user authorities
    let user: any = this.authenticationService.currentUser;

    if (user && user.authorities && (user.authorities instanceof Array)) {
      // loop on links
      this.links.forEach((link: any) => {
        // loop on authorities
        user.authorities.forEach((auth: any) => {
          // loop on link roles
          link.roles.forEach((role: any) => {

            if (role == auth.authority) {
              link['display'] = true;
            }
          });
        });
      });
    }
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


}
