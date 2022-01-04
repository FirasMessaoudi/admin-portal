import { Component, OnInit } from '@angular/core';
import { EAuthority, Page } from '@shared/model';
import { AuthenticationService } from '@core/services';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Subscription } from 'rxjs';
import { Lookup } from '@model/lookup.model';
import { LookupService } from '@core/utilities/lookup.service';
import { I18nService } from '@dcc-commons-ng/services';
import { PrintRequestLite } from '@model/print-request-card-lite.model';
import { StaffPrintService } from '@core/services/printing/staff-print.service';
import { CompanyLite } from '@model/company-lite.model';

@Component({
  selector: 'app-printing-request-list',
  templateUrl: './staff-printing-request-list.component.html',
  styleUrls: ['./staff-printing-request-list.component.scss'],
})
export class StaffPrintingRequestListComponent implements OnInit {
  public isSearchbarCollapsed = true;
  pageArray: Array<number>;
  page: Page;
  printRequests: Array<PrintRequestLite> = [];
  searchForm: FormGroup;
  printRequestStatuses: Lookup[] = [];
  localizedPrintRequestStatuses: Lookup[] = [];
  ritualTypes: Lookup[] = [];
  ritualSeasons: any[] = [];
  companyNames: CompanyLite[] = [];

  private listSubscription: Subscription;
  private searchSubscription: Subscription;

  constructor(
    private authenticationService: AuthenticationService,
    private staffPrintService: StaffPrintService,
    private lookupsService: LookupService,
    private i18nService: I18nService,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.initForm();
    this.loadLookups();
    this.loadPage(0);
  }

  private initForm(): void {
    this.searchForm = this.formBuilder.group({
      statusCode: [null],
      description: [''],
      season: [null],
      companyCode: [null],
      requestNumber: [''],
      batchNumber: [''],
      cardNumber: [''],
      printingStartDate: [null],
      printingEndDate: [null],
      idNumber: [''],
      ritualTypeCode: [null],
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
    this.staffPrintService.findPrintRequestStatuses().subscribe((result) => {
      this.printRequestStatuses = result;
      this.localizedPrintRequestStatuses = this.lookupsService.localizedItems(
        this.printRequestStatuses
      );
    });

    this.staffPrintService.findRitualTypes().subscribe((result) => {
      this.ritualTypes = result;
    });

    this.staffPrintService.findRitualSeasons().subscribe((result) => {
      this.ritualSeasons = result;
    });

    this.staffPrintService.findCompanyNames().subscribe((result) => {
      this.companyNames = result;
    });
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
    this.localizedPrintRequestStatuses = this.lookupsService.localizedItems(
      this.printRequestStatuses
    );
  }

  get canSeePrintRequestsList(): boolean {
    return this.authenticationService.hasAuthority(
      EAuthority.STAFF_PRINTING_REQUEST_MANAGEMENT
    );
  }

  // get canCreateNewRequest(): boolean {
  //   return this.authenticationService.hasAuthority(EAuthority.ADD_PRINTING_REQUEST);
  // }

  loadPage(page: number) {
    this.searchSubscription = this.staffPrintService
      .listFiltered(page, this.searchForm.value)
      .subscribe((data) => {
        this.fillPageWithData(data);
      });
  }

  pageCounter(i: number): Array<number> {
    return new Array(i);
  }

  search(): void {
    if (!this.searchForm.value.statusCode) {
      this.loadPage(0);
      return;
    }
    this.searchSubscription = this.staffPrintService
      .listFiltered(0, this.searchForm.value)
      .subscribe((data) => {
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
