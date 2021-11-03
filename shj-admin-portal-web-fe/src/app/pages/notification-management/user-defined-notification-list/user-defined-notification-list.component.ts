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
import {NgbCalendar, NgbDate, NgbDateParserFormatter} from '@ng-bootstrap/ng-bootstrap';
import {DateFormatterService} from "@shared/modules/hijri-gregorian-datepicker/datepicker/date-formatter.service";
import * as momentjs from 'moment';

const moment = momentjs;

@Component({
  selector: 'app-user-defined-notification-list',
  templateUrl: './user-defined-notification-list.component.html',
  styleUrls: ['./user-defined-notification-list.component.scss']
})
export class UserDefinedNotificationListComponent implements OnInit {

  public isSearchbarCollapsed = true;
  pageArray: Array<number>;
  page: Page;
  searchForm: FormGroup;
  notificationTemplates: Array<NotificationTemplate>;
  notificationCategories: Lookup[] = [];
  notificationNames: Lookup[] = [];
  localizedNotificationCategories: Lookup[] = [];
  localizedNotificationNames: Lookup[] = [];

  hoveredDate: NgbDate | null = null;
  fromDate: NgbDate | null;
  toDate: NgbDate | null;

  private listSubscription: Subscription;
  private searchSubscription: Subscription;

  constructor(private authenticationService: AuthenticationService,
              private formBuilder: FormBuilder,
              private lookupsService: LookupService,
              private notificationService: NotificationService,
              private i18nService: I18nService,
              private calendar: NgbCalendar,
              public formatter: NgbDateParserFormatter,
              private dateFormatterService: DateFormatterService) {
    this.fromDate = calendar.getToday();
    this.toDate = calendar.getNext(calendar.getToday(), 'd', 10);
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
      notificationTitle: [''],
      notificationBody: [''],
      notificationCategory: [null],
      severity: [null],
      toDescription: ''
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

  //TODO create notification authority
  get canCreateNewNotification(): boolean {
    return true;
  }

  getSelectedDate(selectedDate: NgbDate): string {
    if (selectedDate) {
      let formattedDate = this.dateFormatterService.toString(selectedDate);
      return moment(formattedDate, 'DD/MM/YYYY').locale('en').format();
    }
    return null;
  }

  search(): void {
    const fromDate = this.getSelectedDate(this.fromDate)
    const toDate = this.getSelectedDate(this.toDate);

    const payload = this.searchForm.value;
    payload.creationDateStart = fromDate;
    payload.creationDateEnd = toDate;

    this.searchSubscription = this.notificationService.listUserDefined(0, payload).subscribe(data => {

      console.log(payload);

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
    console.log(this.searchForm.value);
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

  onDateSelection(date: NgbDate) {
    if (!this.fromDate && !this.toDate) {
      this.fromDate = date;
    } else if (this.fromDate && !this.toDate && date && date.after(this.fromDate)) {
      this.toDate = date;
    } else {
      this.toDate = null;
      this.fromDate = date;
    }

  }

  isHovered(date: NgbDate) {
    return this.fromDate && !this.toDate && this.hoveredDate && date.after(this.fromDate) && date.before(this.hoveredDate);
  }

  isInside(date: NgbDate) {
    return this.toDate && date.after(this.fromDate) && date.before(this.toDate);
  }

  isRange(date: NgbDate) {
    return date.equals(this.fromDate) || (this.toDate && date.equals(this.toDate)) || this.isInside(date) || this.isHovered(date);
  }

  validateInput(currentValue: NgbDate | null, input: string): NgbDate | null {
    const parsed = this.formatter.parse(input);
    return parsed && this.calendar.isValid(NgbDate.from(parsed)) ? NgbDate.from(parsed) : currentValue;
  }

}
