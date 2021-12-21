import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {EAuthority, Page} from "@shared/model";
import {AuthenticationService} from "@core/services";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ApplicantIncident} from "@model/applicant-incident.model";
import {Subscription} from "rxjs";
import {IncidentService} from "@core/services/incident/incident.service";
import {Lookup} from "@model/lookup.model";
import {LookupService} from "@core/utilities/lookup.service";
import {NgbDateStruct} from "@ng-bootstrap/ng-bootstrap";
import {I18nService} from "@dcc-commons-ng/services";
import {
  HijriGregorianDatepickerComponent
} from "@shared/modules/hijri-gregorian-datepicker/datepicker/hijri-gregorian-datepicker.component";
import {DateType} from "@shared/modules/hijri-gregorian-datepicker/datepicker/consts";
import {DateFormatterService} from "@shared/modules/hijri-gregorian-datepicker/datepicker/date-formatter.service";

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
  selectedFromDate: NgbDateStruct;
  selectedToDate: NgbDateStruct;
  minToDateGregorian: NgbDateStruct;
  minToDateHijri: NgbDateStruct;
  maxFromDateGregorian: NgbDateStruct;
  maxFromDateHijri: NgbDateStruct;
  maxToDateGregorian: NgbDateStruct;
  maxToDateHijri: NgbDateStruct;
  dateString: string;
  selectedDateType: any;
  todayGregorian: NgbDateStruct;
  todayHijri: NgbDateStruct;

  @ViewChild('fromDatePicker') fromDatePicker: HijriGregorianDatepickerComponent;
  @ViewChild('toDatePicker') toDatePicker: HijriGregorianDatepickerComponent;


  constructor(private authenticationService: AuthenticationService,
              private incidentService: IncidentService,
              private formBuilder: FormBuilder,
              private lookupsService: LookupService,
              private i18nService: I18nService,
              private dateFormatterService: DateFormatterService) {
  }

  ngOnInit(): void {
    this.todayGregorian = this.dateFormatterService.todayGregorian();
    this.todayHijri = this.dateFormatterService.todayHijri();
    this.maxFromDateGregorian = this.todayGregorian;
    this.maxFromDateHijri = this.todayHijri;
    this.maxToDateGregorian = this.todayGregorian;
    this.maxToDateHijri = this.todayHijri;
    this.selectedDateType = DateType.Gregorian;
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
      incidentNumber: '',
      applicantId: '',
      applicantName: '',
      incidentType: null,
      status: null,
      fromDateGregorian: '',
      fromDateHijri: '',
      toDateGregorian: '',
      toDateHijri: ''
    });
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.searchForm.controls;
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

  search() {
    const payload = this.searchForm.value;
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

  onFromDateChange(event) {
    if (event) {
      let dateStruct = this.fromDatePicker.selectedDateType == DateType.Gregorian ? this.dateFormatterService.toHijri(event) : this.dateFormatterService.toGregorian(event);
      let dateStructGreg = this.fromDatePicker.selectedDateType == DateType.Gregorian ? event : this.dateFormatterService.toGregorian(event);
      let dateStructHijri = this.fromDatePicker.selectedDateType == DateType.Gregorian ? this.dateFormatterService.toHijri(event) : event;
      this.dateString = this.dateFormatterService.toString(dateStruct);
      this.searchForm.controls.fromDateGregorian.setValue(this.dateFormatterService.toDate(dateStructGreg));
      this.searchForm.controls.fromDateHijri.setValue(this.dateFormatterService.toString(dateStructHijri).split('/').reverse().join(''));
      this.minToDateGregorian = dateStructGreg;
      this.minToDateHijri = dateStructHijri;
    } else {
      this.minToDateGregorian = null;
      this.minToDateHijri = null;
    }
  }

  onToDateChange(event) {
    if (event) {
      let dateStruct = this.toDatePicker.selectedDateType == DateType.Gregorian ? this.dateFormatterService.toHijri(event) : this.dateFormatterService.toGregorian(event);
      let dateStructGreg = this.toDatePicker.selectedDateType == DateType.Gregorian ? event : this.dateFormatterService.toGregorian(event);
      let dateStructHijri = this.toDatePicker.selectedDateType == DateType.Gregorian ? this.dateFormatterService.toHijri(event) : event;
      this.dateString = this.dateFormatterService.toString(dateStruct);
      this.searchForm.controls.toDateGregorian.setValue(this.dateFormatterService.toDate(dateStructGreg));
      this.searchForm.controls.toDateHijri.setValue(this.dateFormatterService.toString(dateStructHijri).split('/').reverse().join(''));
      this.maxFromDateGregorian = dateStructGreg;
      this.maxFromDateHijri = dateStructHijri;
    } else {
      this.maxFromDateGregorian = this.todayGregorian;
      this.maxFromDateHijri = this.todayHijri;
    }
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
