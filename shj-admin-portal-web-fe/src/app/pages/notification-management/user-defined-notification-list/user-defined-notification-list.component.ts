import {Component, OnInit, ViewChild} from '@angular/core';
import {EAuthority, Page} from "@shared/model";
import {AuthenticationService, NotificationService} from "@core/services";
import {AbstractControl, FormBuilder, FormGroup} from "@angular/forms";
import {Subscription} from "rxjs";
import {Lookup} from "@model/lookup.model";
import {LookupService} from "@core/utilities/lookup.service";
import {NotificationTemplate} from "@model/notification-template.model";
import {NotificationTemplateContent} from "@model/notification-template-content.model";
import {I18nService} from "@dcc-commons-ng/services";
import {DateType} from "@shared/modules/hijri-gregorian-datepicker/consts";
import {DatePipe} from "@angular/common";
import {DateFormatterService} from "@shared/modules/hijri-gregorian-datepicker/date-formatter.service";

@Component({
  selector: 'app-user-defined-notification-list',
  templateUrl: './user-defined-notification-list.component.html',
  styleUrls: ['./user-defined-notification-list.component.scss']
})
export class UserDefinedNotificationListComponent implements OnInit {
  isSearchbarCollapsed = true;
  pageArray: Array<number>;
  page: Page;
  searchForm: FormGroup;
  notificationTemplates: Array<NotificationTemplate>;
  notificationCategories: Lookup[] = [];
  notificationNames: Lookup[] = [];
  notificationTemplateStatuses: Lookup[] = [];
  dateType: DateType;
  selectedDateType: DateType;

  @ViewChild('sendingDatePicker') sendingDatePicker: any;
  @ViewChild('creationDatePicker') creationDatePicker: any;

  private listSubscription: Subscription;
  private searchSubscription: Subscription;

  constructor(private authenticationService: AuthenticationService,
              private formBuilder: FormBuilder,
              private lookupsService: LookupService,
              private notificationService: NotificationService,
              private i18nService: I18nService,
              private dateFormatterService: DateFormatterService,
  ) {
  }

  ngOnInit(): void {
    this.selectedDateType = DateType.Gregorian;
    this.initForm();
    this.loadLookups();
    this.loadPage(0);
  }

  loadLookups() {
    this.notificationService.findNotificationCategories().subscribe(result => {
      this.notificationCategories = result;
    });
    this.notificationService.findNotificationTemplateNames().subscribe(result => {
      this.notificationNames = result;
    });
    this.notificationService.findNotificationTemplateStatuses().subscribe(result => {
      this.notificationTemplateStatuses = result;
    });
  }

  private initForm(): void {
    this.searchForm = this.formBuilder.group({
      notificationTitle: '',
      notificationBody: '',
      notificationCategory: null,
      severity: null,
      description: '',
      creationDateStart: null,
      creationDateEnd: null,
      sendingDateStart: null,
      sendingDateEnd: null
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

  get canSeeAddUpdateUserDefinedNotification(): boolean {
    return this.authenticationService.hasAuthority(EAuthority.USER_DEFINED_NOTIFICATION_MANAGEMENT);
  }

  search(): void {
    const payload = this.searchForm.value;
    //Trim input values and replace all whitespaces characters
    payload.notificationTitle = payload.notificationTitle.replace(/\s/g, " ").trim();
    payload.notificationBody = payload.notificationBody.replace(/\s/g, " ").trim();
    payload.description = payload.description.replace(/\s/g, " ").trim();
    this.searchSubscription = this.notificationService.listUserDefined(0, payload).subscribe(data => {
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
    this.listSubscription = this.notificationService.listUserDefined(page, this.searchForm.value).subscribe(data => {
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

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.searchForm.controls;
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

  patchValue(event: Date, c: AbstractControl) {
    c.setValue(event);
  }

  formatDate(date: Date): string {
    const datePipe = new DatePipe('en-US');
    // Hijri Date Type
    if (this.selectedDateType === 1) {
      let hijriDate = this.dateFormatterService.toDate(this.dateFormatterService.toHijri(this.dateFormatterService.fromDate(date)));
      return this.currentLanguage.startsWith('ar') ? datePipe.transform(hijriDate, 'yyyy/MM/dd') : datePipe.transform(hijriDate, 'dd/MM/yyyy');
    }
    // Gregorian Date Type
    else {
      return this.currentLanguage.startsWith('ar') ? datePipe.transform(date, 'yyyy/MM/dd') : datePipe.transform(date, 'dd/MM/yyyy');
    }
  }

  setCreationDateType(event: DateType) {
    this.selectedDateType = event;
    if (event == DateType.Gregorian) {
      this.sendingDatePicker.fromDatePicker.gregClick();
      this.sendingDatePicker.toDatePicker.gregClick();
    } else {
      this.sendingDatePicker.fromDatePicker.hijriClick();
      this.sendingDatePicker.toDatePicker.hijriClick();
    }
  }

  setSendingDateType(event: DateType) {
    this.selectedDateType = event;
    if (event == DateType.Gregorian) {
      this.creationDatePicker.fromDatePicker.gregClick();
      this.creationDatePicker.toDatePicker.gregClick();
    } else {
      this.creationDatePicker.fromDatePicker.hijriClick();
      this.creationDatePicker.toDatePicker.hijriClick();
    }
  }
}
