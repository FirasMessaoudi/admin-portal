import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {EAuthority} from "@model/enum/authority.enum";
import {AuthenticationService} from '@app/_core/services';

@Component({
  selector: 'app-side-nav',
  templateUrl: './side-nav.component.html',
  styleUrls: ['./side-nav.component.scss']
})
export class SideNavComponent implements OnInit {
  currentUser: any;
  links: {}[];
  constructor(
    public router: Router,
    private authenticationService: AuthenticationService
  ) { }

  ngOnInit() {
    this.currentUser = this.authenticationService.currentUser
    this.links = [
      {
        title: 'home.title',
        roles: [EAuthority.ADMIN_DASHBOARD],
        display: false,
        icon: 'portrait',
        iconFa:'fa-w-12',
        routerLink: '/',
      },
      {
        title: 'card-management.title',
        roles: [EAuthority.CARD_MANAGEMENT],
        display: false,
        icon: 'address-card',
        iconFa: 'fa-w-18',
        routerLink: '/cards/list',
      },
      {
        title: 'printing-management.title',
        roles: [EAuthority.PRINTING_REQUEST_MANAGEMENT],
        display: false,
        icon: 'print',
        iconFa:'fa-w-16',
        routerLink: '/print-requests/list',
      },
      {
        title: 'role-management.title',
        roles: [EAuthority.ROLE_MANAGEMENT],
        display: false,
        icon: 'file-alt',
        iconFa:'fa-w-12',
        routerLink: '/roles/list',
      },
      {
        title: 'user-management.title',
        roles: [EAuthority.USER_MANAGEMENT],
        display: false,
        icon: 'users',
        iconFa:'fa-w-20',
        routerLink: '/users/list',
      },
/*
      TODO Uncomment the following block
      {
        title: 'rule-management.title',
        roles: [EAuthority.USER_MANAGEMENT],
        display: false,
        icon: 'users',
        iconFa:'fa-w-20',
        routerLink: '/rules/list',
      },*/
      {
        title: 'data-request-management.title',
        roles: [EAuthority.DATA_REQUEST_MANAGEMENT],
        display: false,
        icon: 'file-upload',
        iconFa: 'fa-w-12',
        routerLink: '/data-requests/list',
      },
      {
        title: 'notification-management.title',
        roles: [EAuthority.NOTIFICATION_MANAGEMENT],
        display: false,
        icon: 'notification',
        iconFa: 'fa-w-18',
        routerLink: '/notification/list',
      },
      //TODO Change this to create submenus
      {
        title: 'notification-management.user_defined_notifications',
        roles: [EAuthority.NOTIFICATION_MANAGEMENT],
        display: false,
        icon: 'megaphone',
        iconFa: 'fa-w-18',
        routerLink: '/user-defined-notification/list',
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

}
