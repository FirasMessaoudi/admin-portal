import {Component, OnInit} from '@angular/core';
import {EAuthority, Page} from "@shared/model";
import {AuthenticationService, NotificationService} from "@core/services";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Subscription} from "rxjs";
import {Lookup} from "@model/lookup.model";
import {LookupService} from "@core/utilities/lookup.service";

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
  notificationCategories: Lookup[] = [];
  notificationNames: Lookup[] = [];
  localizedNotificationCategories: Lookup[]= [];
  localizedNotificationNames: Lookup[]= [];

  private listSubscription: Subscription;
  private searchSubscription: Subscription;

  constructor(private authenticationService: AuthenticationService,
              private formBuilder: FormBuilder,
              private lookupsService: LookupService,
              private notificationService: NotificationService) {
  }

  ngOnInit(): void {

    this.initForm();
    this.loadLookups();
    // this.loadPage(0);
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
      notificationCategory: [null],
      notificationName: [null]
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

  }

  loadPage(page: number) {

  }

  lookupService(): LookupService {
    return this.lookupsService;
  }
}
