import {Component, OnInit} from '@angular/core';
import {EAuthority, Page} from "@shared/model";
import {AuthenticationService} from "@core/services";
import {FormGroup} from "@angular/forms";
import {PrintService} from "@core/services/print/print.service";
import {Subscription} from "rxjs";
import {PrintRequest} from "@model/print-request.model";
import {Lookup} from "@model/lookup.model";
import {LookupService} from "@core/utilities/lookup.service";

@Component({
  selector: 'app-printing-request-list',
  templateUrl: './printing-request-list.component.html',
  styleUrls: ['./printing-request-list.component.scss']
})
export class PrintingRequestListComponent implements OnInit {
  public isSearchbarCollapsed= false;
  pageArray: Array<number>;
  page: Page;
  printRequests: Array<PrintRequest>= [];
  searchForm: FormGroup;
  canCreateNewRequest: boolean;
  printRequestStatuses: Lookup[];

  private listSubscription: Subscription;
  private searchSubscription: Subscription;

  constructor(private authenticationService: AuthenticationService,
              private printService: PrintService,
              private lookupsService: LookupService) { }

  ngOnInit(): void {
    this.canCreateNewRequest = true;
    this.loadLookups();
    this.loadPage(0);
  }

  private initForm(): void {

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
    });
  }

  lookupService(): LookupService {
    return this.lookupsService;
  }

  get canSeePrintRequestsList(): boolean {
    //TODO: change it to PRINTING_MANAGEMENT
    return this.authenticationService.hasAuthority(EAuthority.USER_MANAGEMENT);
  }

  loadPage(page: number) {
    this.listSubscription = this.printService.list(page).subscribe(data => {
      this.page = data;
      if (this.page != null) {
        this.pageArray = Array.from(this.pageCounter(this.page.totalPages));
        this.printRequests = this.page.content;
      }
    })
  }

  pageCounter(i: number): Array<number> {
    return new Array(i);
  }

  search(): void {
    this.searchSubscription = this.printService.list(0).subscribe(data => {
      this.printRequests = [];
      this.pageArray = [];
      this.page = data;
      if (this.page != null) {
        this.pageArray = Array.from(this.pageCounter(this.page.totalPages));
        this.printRequests = this.page.content;
      }
    });
  }

  cancelSearch() {
    this.initForm();
  }

}
