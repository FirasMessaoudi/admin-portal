import {Component, OnInit} from '@angular/core';
import {DataRequest, EAuthority, Page} from "@shared/model";
import {AuthenticationService, DataRequestService} from "@core/services";
import {FormGroup} from "@angular/forms";
import {TranslateService} from "@ngx-translate/core";
import {Subscription} from "rxjs";
import {I18nService} from "@dcc-commons-ng/services";
import {ToastService} from "@shared/components/toast";

@Component({
  selector: 'app-printing-request-list',
  templateUrl: './data-request-list.component.html',
  styleUrls: ['./data-request-list.component.scss']
})
export class DataRequestListComponent implements OnInit {

  dataRequests: Array<DataRequest>;
  pageArray: Array<number>;
  page: Page;
  listSubscription: Subscription;
  searchForm: FormGroup;
  canCreateNewRequest: boolean;

  constructor(private authenticationService: AuthenticationService,
              public dataRequestService: DataRequestService,
              private toastr: ToastService,
              private i18nService: I18nService,
              private translate: TranslateService) {
  }

  ngOnInit(): void {
    this.canCreateNewRequest = true;
    this.loadPage(0);
  }

  private initForm(): void {

  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  loadPage(page: number) {
    // load data requests for param page
    this.listSubscription = this.dataRequestService.list(page).subscribe(data => {
      this.page = data;
      if (this.page != null) {
        this.pageArray = Array.from(this.pageCounter(this.page.totalPages));
        this.dataRequests = this.page.content;
      }
    });
  }

  pageCounter(i: number): Array<number> {
    return new Array(i);
  }
  get canSeeDataRequestsList(): boolean {
    //TODO: change it to DATA_UPLOAD_MANAGEMENT
    return this.authenticationService.hasAuthority(EAuthority.USER_MANAGEMENT);
  }

  cancelSearch() {
    this.initForm();
  }

  downloadOriginalFile(dataRequestId: number) {
    this.dataRequestService.downloadAndSave(dataRequestId, 'O');
  }

  downloadErrorsFile(dataRequestId: number) {
    this.dataRequestService.downloadAndSave(dataRequestId, 'E');
  }

}
