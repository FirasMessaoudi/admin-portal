import {Component, OnInit} from '@angular/core';
import {EAuthority, Page} from "@shared/model";
import {AuthenticationService} from "@core/services";
import {AbstractControl,FormBuilder, FormGroup} from "@angular/forms";
import {PrintService} from "@core/services/printing/print.service";
import {Subscription} from "rxjs";
import {Lookup} from "@model/lookup.model";
import {LookupService} from "@core/utilities/lookup.service";
import {I18nService} from "@dcc-commons-ng/services";
import {PrintRequestLite} from "@model/print-request-card-lite.model";
import { DateType } from '@app/_shared/modules/hijri-gregorian-datepicker/consts';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-printing-request-list',
  templateUrl: './printing-request-list.component.html',
  styleUrls: ['./printing-request-list.component.scss']
})
export class PrintingRequestListComponent implements OnInit {
  public isSearchbarCollapsed = true;
  pageArray: Array<number>;
  page: Page;
  printRequests: Array<PrintRequestLite> = [];
  searchForm: FormGroup;
  printRequestStatuses: Lookup[];
  localizedPrintRequestStatuses: Lookup[];
  selectedDateType: DateType;
  todayGregorian: NgbDateStruct;
  todayHijri: NgbDateStruct;

  private listSubscription: Subscription;
  private searchSubscription: Subscription;
  constructor(private authenticationService: AuthenticationService,
              private printService: PrintService,
              private lookupsService: LookupService,
              private i18nService: I18nService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {

    this.initForm();
    this.loadLookups();
    this.loadPage(0);
  }

  private initForm(): void {
    this.searchForm = this.formBuilder.group({
      requestNumber: null,
      statusCode: null,
      description: null,
      fromDate: null,
      toDate: null,
      batchNumber: null,
      cardNumber: null,
      idNumber: null,

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

  loadLookups() {
    this.printService.findPrintRequestStatuses().subscribe(result => {
      this.printRequestStatuses = result;
      this.localizedPrintRequestStatuses = this.lookupsService.localizedItems(this.printRequestStatuses);
    });
  }

  setSelectedDateType(event: DateType) {
    this.selectedDateType = event;
  }

  
  get f() {
    return this.searchForm.controls;
  }

  patchValue(event: Date, c: AbstractControl) {
    c.setValue(event);
  }

  lookupService(): LookupService {
    return this.lookupsService;
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  /**
   * Reload print request statuses to accept language change for example.
   */
  reloadPrintRequestStatuses() {
    this.localizedPrintRequestStatuses = this.lookupsService.localizedItems(this.printRequestStatuses);
  }

  get canSeePrintRequestsList(): boolean {
    return this.authenticationService.hasAuthority(EAuthority.APPLICANT_PRINTING_REQUEST_MANAGEMENT);
  }

  get canCreateNewRequest(): boolean {
    return this.authenticationService.hasAuthority(EAuthority.ADD_PRINTING_REQUEST);
  }

  loadPage(page: number) {
    this.searchSubscription = this.printService.listFiltered(page, this.searchForm.value).subscribe(data => {
      this.fillPageWithData(data);
    });

  }

  pageCounter(i: number): Array<number> {
    return new Array(i);
  }

  search(): void {
    console.log(this.searchForm.value);
    if (!this.searchForm.value.statusCode) {
      this.loadPage(0);
      return;
    }
    this.searchSubscription = this.printService.listFiltered(0, this.searchForm.value).subscribe(data => {
      this.printRequests = [];
      this.pageArray = [];
      this.fillPageWithData(data);
    });
  }


  fillPageWithData(pageData: any) {
    this.page = pageData;
    if (this.page != null) {
      this.pageArray = Array.from(this.pageCounter(this.page.totalPages));
      this.printRequests = this.page.content;
    }
  }

}
