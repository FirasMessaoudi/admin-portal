import {Component, OnInit} from '@angular/core';
import {combineLatest} from "rxjs";
import {map} from "rxjs/operators";
import {I18nService} from "@dcc-commons-ng/services";
import {ActivatedRoute, Router} from "@angular/router";
import {ToastService} from "@shared/components/toast";
import {TranslateService} from "@ngx-translate/core";
import {AuthenticationService, NotificationService} from "@core/services";
import {LookupService} from "@core/utilities/lookup.service";
import {NotificationTemplate} from "@model/notification-template.model";
import {Lookup} from "@model/lookup.model";
import {NotificationTemplateContent} from "@model/notification-template-content.model";

@Component({
  selector: 'app-system-defined-notification-details',
  templateUrl: './system-defined-notification-details.component.html',
  styleUrls: ['./system-defined-notification-details.component.scss']
})
export class SystemDefinedNotificationDetailsComponent implements OnInit {
  notificationId: number;
  isLoading: boolean;
  notification: NotificationTemplate;
  notificationTemplateCategories: Lookup[] = [];
  notificationTemplateNames: Lookup[] = [];

  constructor(private i18nService: I18nService,
              private route: ActivatedRoute,
              private router: Router,
              private toastr: ToastService,
              private translate: TranslateService,
              private authenticationService: AuthenticationService,
              private notificationService: NotificationService,
              private lookupsService: LookupService,
  ) {
  }


  ngOnInit(): void {
    combineLatest([this.route.params, this.route.queryParams]).pipe(map(results => ({
      params: results[0].id,
      qParams: results[1]
    }))).subscribe(results => {
      this.notificationId = +results.params; // (+) converts string 'id' to a number
      if (this.notificationId) {
        this.isLoading = true;
        // load user details
        this.notificationService.findNotificationTemplateById(this.notificationId).subscribe(data => {
          if (data && data.id) {
            this.isLoading = false;
            this.notification = data;
          } else {
            this.toastr.error(this.translate.instant('general.route_item_not_found', {itemId: this.notificationId}),
              this.translate.instant('general.dialog_error_title'));
            this.goBackToList();
          }
        });
      } else {
        this.toastr.error(this.translate.instant('general.route_id_param_not_found'),
          this.translate.instant('general.dialog_error_title'));
        this.goBackToList();
      }
    });
  }

  getNotificationContentForCurrentLanguage(notificationContents: NotificationTemplateContent []) {
    if (notificationContents.length > 0) {
      let contents = notificationContents.filter(value => this.i18nService.language.toLowerCase().startsWith(value.lang.toLowerCase()));
      if (!contents) {
        return notificationContents[0];
      }
      return contents[0];
    } else {
      return null;
    }
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  get canSeeSystemDefinedNotificationDetails(): boolean {

    // return this.authenticationService.hasAuthority(EAuthority.SYSTEM_DEFINED_NOTIFICATION_DETAILS);
    return true;
  }

  goBackToList() {
    this.router.navigate(['/notification/list']);
  }


  loadLookups() {

    this.notificationService.findNotificationCategories().subscribe(result => {
      this.notificationTemplateCategories = result;
    });

    this.notificationService.findNotificationTemplateNames().subscribe(result => {
      this.notificationTemplateNames = result;
    });
  }

  lookupService(): LookupService {
    return this.lookupsService;
  }

}
