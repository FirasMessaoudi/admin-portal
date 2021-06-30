import {Component, OnDestroy, OnInit} from '@angular/core';
import {I18nService} from "@dcc-commons-ng/services";
import {EAuthority} from "@shared/model";
import {AuthenticationService, CardService} from "@core/services";
import {combineLatest} from "rxjs";
import {map} from "rxjs/operators";
import {ActivatedRoute, Router} from "@angular/router";
import {ToastService} from "@shared/components/toast";
import {TranslateService} from "@ngx-translate/core";
import {PrintService} from "@core/services/printing/print.service";
import {PrintRequest} from "@model/print-request.model";
import {LookupService} from "@core/utilities/lookup.service";
import {Lookup} from "@model/lookup.model";
import {PrintBatchType} from "@model/print-batch-type.model";
import {CountryLookup} from "@model/country-lookup.model";
import {BatchType} from "@model/enum/batch-type.enum";
import {PrintRequestStatus} from "@model/enum/print-request-status.enum";
import {NavigationService} from "@core/utilities/navigation.service";

@Component({
  selector: 'app-printing-request-details',
  templateUrl: './printing-request-details.component.html',
  styleUrls: ['./printing-request-details.component.scss']
})
export class PrintingRequestDetailsComponent implements OnInit, OnDestroy {
  public isCollapsed: boolean[] = [];

  printRequestId: number;
  printRequest: PrintRequest;
  printRequestStatuses: Lookup[];
  batchTypes: PrintBatchType[];
  countries: CountryLookup[];
  batchType = BatchType;
  printRequestStatus = PrintRequestStatus;

  constructor(private i18nService: I18nService,
              private route: ActivatedRoute,
              private router: Router,
              private toastr: ToastService,
              private translate: TranslateService,
              private printService: PrintService,
              private lookupsService: LookupService,
              private cardService: CardService,
              private authenticationService: AuthenticationService,
              private navigationService: NavigationService
  ) {
  }

  ngOnInit(): void {
    this.loadLookups();
    this.navigationService.showGoBackLink({renderGoBackLink: true, goBackURL: ''});
    combineLatest([this.route.params, this.route.queryParams]).pipe(map(results => ({
      params: results[0].id,
      qParams: results[1]
    }))).subscribe(results => {
      this.printRequestId = +results.params; // (+) converts string 'id' to a number

      if (this.printRequestId) {
        // load user details
        this.printService.find(this.printRequestId).subscribe(data => {
          if (data && data.id) {
            this.printRequest = data;
          } else {
            this.toastr.error(this.translate.instant('general.route_item_not_found', {itemId: this.printRequestId}),
              this.translate.instant('general.dialog_error_title'));
            this.goToList();
          }
        });
      } else {
        this.toastr.error(this.translate.instant('general.route_id_param_not_found'),
          this.translate.instant('general.dialog_error_title'));
        this.goToList();
      }
    });
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  get canSeePrintRequestDetails(): boolean {

    return this.authenticationService.hasAuthority(EAuthority.VIEW_PRINTING_REQUEST_DETAILS);
  }

  get canSeeCardDetails(): boolean {
    return this.authenticationService.hasAuthority(EAuthority.VIEW_CARD_DETAILS);
  }

  goToList() {
    this.router.navigate(['/print-requests/list']);
  }

  ngOnDestroy() {
    this.navigationService.showGoBackLink({renderGoBackLink: false, goBackURL: ''});
  }

  lookupService(): LookupService {
    return this.lookupsService;
  }

  loadLookups() {
    this.printService.findPrintRequestStatuses().subscribe(result => {
      this.printRequestStatuses = result;
    });
    this.printService.findPrintBatchTypes().subscribe(result => {
      this.batchTypes = result;
    });
    this.cardService.findCountries().subscribe(result => {
      this.countries = result;
    });
  }

  getBatchType(batchType) {
    return this.batchTypes.find(b => b.code === batchType);
  }

}
