import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EAuthority } from '@model/enum/authority.enum';
import { AuthenticationService } from '@app/_core/services';
import * as moment_ from 'moment-hijri';
const momentHijri = moment_;
@Component({
  selector: 'app-side-nav',
  templateUrl: './side-nav.component.html',
  styleUrls: ['./side-nav.component.scss'],
})
export class SideNavComponent implements OnInit {
  currentUser: any;
  links: {}[];

  constructor(
    public router: Router,
    private authenticationService: AuthenticationService
  ) {}

  ngOnInit() {
    this.currentUser = this.authenticationService.currentUser;
    this.links = [
      {
        title: 'home.title',
        roles: [EAuthority.ADMIN_DASHBOARD],
        display: false,
        icon: 'home',
        iconFa: 'fa-w-18',
        routerLink: '/dashboard',
        submenu: false,
      },
      {
        title: 'card-management.title',
        roles: [EAuthority.CARD_MANAGEMENT],
        display: false,
        icon: 'address-card',
        iconFa: 'fa-w-18',
        routerLink: '/cards/list',
        submenu: true,
        isCollapsed: true,
        menuItems: [
          {
            title: 'card-management.applicant_title',
            display: false,
            roles: [EAuthority.CARD_MANAGEMENT],
            routerLink: '/cards/list',
          },
          {
            title: 'card-management.staff_title',
            display: false,
            roles: [EAuthority.CARD_MANAGEMENT],
            routerLink: '/staff-cards/list',
          },
        ],
      },
      {
        title: 'printing-management.title',
        roles: [
          EAuthority.STAFF_PRINTING_REQUEST_MANAGEMENT,
          EAuthority.APPLICANT_PRINTING_REQUEST_MANAGEMENT,
        ],
        display: false,
        icon: 'print',
        iconFa: 'fa-w-16',
        submenu: true,
        isCollapsed: true,
        menuItems: [
          {
            title: 'printing-management.applicant_title',
            display: false,
            roles: [EAuthority.APPLICANT_PRINTING_REQUEST_MANAGEMENT],
            routerLink: '/print-requests/list',
          },
          {
            title: 'printing-management.staff_title',
            display: false,
            roles: [EAuthority.STAFF_PRINTING_REQUEST_MANAGEMENT],
            routerLink: '/staff-print-requests/list',
          },
        ],
      },
      {
        title: 'incident-management.title',
        //TODO Change this roles: [EAuthority.INCIDENT_MANAGEMENT]
        roles: [EAuthority.USER_MANAGEMENT],
        display: false,
        icon: 'flag',
        iconFa: 'fa-w-16',
        routerLink: '/incidents/list',
        submenu: false,
      },
      {
        title: 'role-management.title',
        roles: [EAuthority.ROLE_MANAGEMENT],
        display: false,
        icon: 'file-alt',
        iconFa: 'fa-w-12',
        routerLink: '/roles/list',
        submenu: false,
      },
      {
        title: 'user-management.title',
        roles: [EAuthority.USER_MANAGEMENT],
        display: false,
        icon: 'users',
        iconFa: 'fa-w-20',
        routerLink: '/users/list',
        submenu: false,
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
        submenu: false,
      },
      {
        title: 'notification-management.title',
        roles: [EAuthority.NOTIFICATION_MANAGEMENT],
        display: false,
        icon: 'megaphone',
        iconFa: 'fa-w-16',
        submenu: true,
        isCollapsed: true,
        menuItems: [
          {
            title: 'notification-management.system_notifications',
            roles: [EAuthority.NOTIFICATION_MANAGEMENT],
            display: false,
            routerLink: '/notification/list',
          },
          {
            title: 'notification-management.user_notifications',
            roles: [EAuthority.NOTIFICATION_MANAGEMENT],
            display: false,
            routerLink: '/user-defined-notification/list',
          },
        ],
      },
      // ,
      // {
      //   title: 'notification-management.title',
      //   roles: [EAuthority.NOTIFICATION_MANAGEMENT],
      //   display: false,
      //   icon: 'megaphone',
      //   iconFa: 'fa-w-18',
      //   routerLink: '/notification/list',
      //   submenu: false,
      // },
      //TODO Change this to create submenus
      // {
      //   title: 'notification-management.user_defined_notifications',
      //   roles: [EAuthority.NOTIFICATION_MANAGEMENT],
      //   display: false,
      //   icon: 'megaphone',
      //   iconFa: 'fa-w-18',
      //   routerLink: '/user-defined-notification/list',
      //   submenu: false,
      // }
    ];
    // filtering access according to connected user authorities
    let user: any = this.authenticationService.currentUser;

    if (user && user.authorities && user.authorities instanceof Array) {
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

          if (link.submenu) {

            if(!this.checkIfSubMenuItemHasRole(link,user.authorities))
            {
              link['submenu'] =false;
            }

            link.menuItems.forEach((menuItem: any) => {
              // loop on link submenu roles
              menuItem.roles.forEach((role: any) => {
                if (role == auth.authority) {
                  menuItem['display'] = true;
                }
              });
            });
          }
        });
      });
    }
  }

checkIfSubMenuItemHasRole(link :any,authorities:any) : boolean
{
      let hasRole : boolean=false;
      if(authorities && authorities.length>0)
      {
        authorities.forEach(i=> 
        {
            if(link && link.menuItems && link.menuItems.length>0)
            {
               link.menuItems.forEach(k=> 
              {
                  if(k && k.roles && k.roles.length>0)
                  {
                    if(k.roles.some(l=> l == i.authority))
                    hasRole=true;
                  }

              });                
                 
            }
        });
      }
       return hasRole;
}

  resetSeasonYear() {
    let seasonYear = momentHijri(new Date()).iYear();
    localStorage.setItem('seasonYear', String(seasonYear));
  }
  setCollapsed(index: number) {
    let updateItem: any = this.links[index];
    updateItem.isCollapsed = !updateItem.isCollapsed;
    this.links[index] = updateItem;
  }
}
