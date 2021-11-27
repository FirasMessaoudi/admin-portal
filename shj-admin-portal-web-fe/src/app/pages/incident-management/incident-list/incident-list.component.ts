import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {EAuthority, Page} from "@shared/model";
import {AuthenticationService} from "@core/services";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ApplicantIncident} from "@model/incident.model";
import {Subscription} from "rxjs";
import {IncidentService} from "@core/services/incident/incident.service";
import {Lookup} from "@model/lookup.model";
import {LookupService} from "@core/utilities/lookup.service";
import {NgbCalendar, NgbDate, NgbDateParserFormatter} from "@ng-bootstrap/ng-bootstrap";
import {DateFormatterService} from "@shared/modules/hijri-gregorian-datepicker/datepicker/date-formatter.service";
import {I18nService} from "@dcc-commons-ng/services";
import * as momentjs from 'moment';

const moment = momentjs;

@Component({
  selector: 'app-incident-list',
  templateUrl: './incident-list.component.html',
  styleUrls: ['./incident-list.component.scss']
})
export class IncidentListComponent implements OnInit, OnDestroy {
  public isSearchbarCollapsed = true;
  pageArray: Array<number>;
  page: Page;
  searchForm: FormGroup;
  incidents: ApplicantIncident[] = [];
  incidentTypes: Lookup[] = [];
  incidentStatuses: Lookup[] = [];
  listSubscription: Subscription;
  searchSubscription: Subscription;
  hoveredDate: NgbDate | null = null;
  fromDate: NgbDate | null;
  toDate: NgbDate | null;
  @ViewChild('datepicker') datePicker: any;
  today: NgbDate;

  constructor(private authenticationService: AuthenticationService,
              private incidentService: IncidentService,
              private formBuilder: FormBuilder,
              private lookupsService: LookupService,
              private calendar: NgbCalendar,
              public formatter: NgbDateParserFormatter,
              private i18nService: I18nService,
              private dateFormatterService: DateFormatterService) {
    this.fromDate = calendar.getToday();
    this.today = calendar.getToday();
    this.toDate = calendar.getNext(calendar.getToday(), 'd', 10);
  }

  ngOnInit(): void {
    this.loadLookups();
    this.initForm();
    this.loadPage(0);
  }

  ngOnDestroy() {
    if (this.listSubscription) {
      this.listSubscription.unsubscribe();
    }
    if (this.searchSubscription) {
      this.searchSubscription.unsubscribe();
    }
  }

  private initForm(): void {
    this.searchForm = this.formBuilder.group({
      applicantId: ['', Validators.required],
      applicantName: ['', Validators.required],
      incidentType: [null],
      status: [null]
    });
  }

  loadLookups() {
    this.incidentService.findIncidentTypes().subscribe(result => {
      this.incidentTypes = result;
    });
    this.incidentService.findIncidentStatuses().subscribe(result => {
      this.incidentStatuses = result;
    });
  }

  lookupService(): LookupService {
    return this.lookupsService;
  }

  get canSeeIncidentList(): boolean {
    //TODO Change this
    return this.authenticationService.hasAuthority(EAuthority.NOTIFICATION_MANAGEMENT);
  }

  loadPage(page: number) {
    this.listSubscription = this.incidentService.list(page, this.searchForm.value).subscribe(data => {
      this.page = data;
      if (this.page != null) {
        this.pageArray = Array.from(this.pageCounter(this.page.totalPages));
        this.incidents = this.page.content;
      }
    })
  }

  pageCounter(i: number): Array<number> {
    return new Array(i);
  }

  getSelectedDate(selectedDate: NgbDate): string {
    if (selectedDate) {
      let formattedDate = this.dateFormatterService.toString(selectedDate);
      return moment(formattedDate, 'DD/MM/YYYY').locale('en').format();
    }
    return null;
  }

  search() {
    const fromDate = this.getSelectedDate(this.fromDate)
    const toDate = this.getSelectedDate(this.toDate);
    const payload = this.searchForm.value;
    payload.creationDateStart = fromDate;
    payload.creationDateEnd = toDate;
    this.searchSubscription = this.incidentService.list(0, payload).subscribe(data => {
      this.incidents = [];
      this.pageArray = [];
      this.page = data;
      if (this.page != null) {
        this.pageArray = Array.from(this.pageCounter(this.page.totalPages));
        this.incidents = this.page.content;
      }
    });
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  onDateSelection(date: NgbDate) {
    if (!this.fromDate && !this.toDate) {
      this.fromDate = date;
    } else if (this.fromDate && !this.toDate && date && date.after(this.fromDate)) {
      this.toDate = date;
      this.datePicker.close();
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

  clearDate() {
    this.fromDate = undefined;
    this.toDate = undefined;
    this.onDateSelection(null);
  }

  /**
   * Returns the css class for the given status
   *
   * @param status the current applicant incident status
   */
  buildStatusClass(status: any): string {
    switch (status) {
      case 'UNDER_PROCESSING':
        return 'ready';
      case 'RESOLVED':
        return 'done';
      case 'CLOSED':
        return 'warning';
      default:
        return 'new';
    }
  }
}
