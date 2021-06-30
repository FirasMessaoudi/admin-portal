import {Component, OnDestroy, OnInit} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder} from "@angular/forms";
import {Role, User} from '@app/_shared/model';
import {AuthenticationService, UserService} from '@app/_core/services';
import {ToastService} from '@shared/components/toast/toast-service';
import {I18nService} from "@dcc-commons-ng/services";
import {TranslateService} from "@ngx-translate/core";
import {DatePipe} from "@angular/common";
import {RoleService} from "@core/services/role/role.service";
import {EAuthority} from "@model/enum/authority.enum";
import {NavigationService} from "@core/utilities/navigation.service";

@Component({
  selector: "app-user-details",
  templateUrl: "./user-details.component.html",
  styleUrls: ["./user-details.component.scss"],
  providers: [DatePipe]
})
export class UserDetailsComponent implements OnInit, OnDestroy {
  userId: number;
  user: User;
  url: any = 'assets/images/default-avatar.svg';
  file: File;
  systemAdminUser: boolean;

  constructor(private activeRoute: ActivatedRoute,
              private router: Router,
              private toastr: ToastService,
              private translate: TranslateService,
              private formBuilder: FormBuilder,
              private i18nService: I18nService,
              private userService: UserService,
              private roleService: RoleService,
              private authentication: AuthenticationService,
              private navigation: NavigationService,
              public datePipe: DatePipe,
              private authenticationService: AuthenticationService,
              private navigationService: NavigationService) {
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  ngOnInit() {
    this.userId = this.activeRoute.snapshot.params.id;
    this.navigationService.showGoBackLink({renderGoBackLink: true, goBackURL: ''});

    if (this.userId) {
      // load user details
      this.userService.find(this.userId).subscribe(data => {
        if (data && data.id) {
          this.user = data;
        } else {
          this.toastr.error(this.translate.instant('general.route_item_not_found', {itemId: this.userId}),
            this.translate.instant('general.dialog_error_title'));
          this.goToList();
        }
      });
    } else {
      this.toastr.error(this.translate.instant('general.route_id_param_not_found'),
        this.translate.instant('general.dialog_error_title'));
      this.goToList();
    }
  }

  ngOnDestroy() {
    this.navigationService.showGoBackLink({renderGoBackLink: false, goBackURL: ''});
  }

  goToList() {
    this.router.navigate(['/users/list']);
  }

  formatDate(date: any) {
    return date.year + '-' + ('00' + date.month).slice(-2) + '-' + ('00' + date.day).slice(-2);
  }

  toCalender(date: any) {
    let latest_date = new Date(Date.parse(this.datePipe.transform(date)));
    console.log(latest_date);
    return {
      year: latest_date.getFullYear(),
      month: latest_date.getMonth() + 1,
      day: latest_date.getDate()
    };
  }

  get canSeeUserDetails(): boolean {
    let requiredAuthorities: EAuthority [] = new Array(EAuthority.USER_MANAGEMENT, EAuthority.VIEW_MY_PROFILE);
    return this.authenticationService.hasAnyAuthority(requiredAuthorities);
  }

  get userMainRole(): Role {
    if (this.user) {
      let userRole = this.user.userRoles.find(userRole => userRole.mainRole);
      if (userRole) return userRole.role;
    }
    return null;
  }


}
