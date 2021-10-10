import {Component, OnInit} from '@angular/core';
import {EAuthority, Page} from "@shared/model";
import {AuthenticationService, NotificationService} from "@core/services";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Subscription} from "rxjs";
import {Lookup} from "@model/lookup.model";
import {LookupService} from "@core/utilities/lookup.service";
import {NotificationTemplate} from "@model/notification-template.model";
import {NotificationTemplateContent} from "@model/notification-template-content.model";
import {I18nService} from "@dcc-commons-ng/services";

@Component({
  selector: 'app-notification-list',
  templateUrl: './notification-list.component.html',
  styleUrls: ['./notification-list.component.scss']
})
export class NotificationListComponent implements OnInit {

  public isSearchbarCollapsed = true;
  pageArray: Array<number>;
  page: Page;
  searchForm: FormGroup;
  notificationTemplates: Array<NotificationTemplate>;
  notificationCategories: Lookup[] = [];
  notificationNames: Lookup[] = [];
  localizedNotificationCategories: Lookup[] = [];
  localizedNotificationNames: Lookup[] = [];

  private listSubscription: Subscription;
  private searchSubscription: Subscription;

  constructor(private authenticationService: AuthenticationService,
              private formBuilder: FormBuilder,
              private lookupsService: LookupService,
              private notificationService: NotificationService,
              private i18nService: I18nService) {
  }

  ngOnInit(): void {

    this.initForm();
    this.loadLookups();
    this.loadPage(0);
  }

  loadLookups() {
    this.notificationService.findNotificationCategories().subscribe(result => {
      this.notificationCategories = result;
      this.localizedNotificationCategories = this.lookupsService.localizedItems(this.notificationCategories);

    });
    this.notificationService.findNotificationTemplateNames().subscribe(result => {
      this.notificationNames = result;
      this.localizedNotificationNames = this.lookupsService.localizedItems(this.notificationNames);

    });
  }

  private initForm(): void {
    this.searchForm = this.formBuilder.group({
      notificationBody: [null],
      notificationTitle: [null],
      notificationCategory: [null],
      notificationName: [null],
      severity: [null],
      notificationType: [null]
    });
  }

  ngOnDestroy() {
    if (this.listSubscription) {
      this.listSubscription.unsubscribe();
    }
    if (this.searchSubscription) {
      this.searchSubscription.unsubscribe();
    }
  }

  get canSeeNotificationList(): boolean {
    return this.authenticationService.hasAuthority(EAuthority.NOTIFICATION_MANAGEMENT);
  }

  search(): void {
    this.searchSubscription = this.notificationService.list(0, this.searchForm.value).subscribe(data => {
      this.notificationTemplates = [];
      this.pageArray = [];
      this.page = data;
      if (this.page != null) {
        this.pageArray = Array.from(this.pageCounter(this.page.totalPages));
        this.notificationTemplates = this.page.content;
      }
    });
  }

  loadPage(page: number) {
    this.listSubscription = this.notificationService.list(page, this.searchForm.value).subscribe(data => {
      this.page = data;
      if (this.page != null) {
        this.pageArray = Array.from(this.pageCounter(this.page.totalPages));
        this.notificationTemplates = this.page.content;
      }
    })
  }

  pageCounter(i: number): Array<number> {
    return new Array(i);
  }

  lookupService(): LookupService {
    return this.lookupsService;
  }

  getNotificationContentForCurrentLanguage(notificationContents: NotificationTemplateContent []) {
    if (notificationContents.length > 0) {
      let contents = notificationContents.filter(value => this.i18nService.language.startsWith(value.lang));
      if (!contents) {
        return notificationContents[0];
      }
      return contents[0];
    } else {
      return null;
    }
  }
}
