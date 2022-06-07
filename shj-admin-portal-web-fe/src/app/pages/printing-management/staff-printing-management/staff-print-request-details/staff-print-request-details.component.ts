import {Component, OnInit} from '@angular/core';
import {PrintRequest} from "@model/print-request.model";
import {Lookup} from "@model/lookup.model";
import {PrintBatchType} from "@model/print-batch-type.model";
import {NationalityLookup} from "@model/nationality-lookup.model";
import {BatchType} from "@model/enum/batch-type.enum";
import {PrintRequestStatus} from "@model/enum/print-request-status.enum";
import {I18nService} from "@dcc-commons-ng/services";
import {ActivatedRoute, Router} from "@angular/router";
import {ToastService} from "@shared/components/toast";
import {TranslateService} from "@ngx-translate/core";
import {LookupService} from "@core/utilities/lookup.service";
import {AuthenticationService, CardService} from "@core/services";
import {NavigationService} from "@core/utilities/navigation.service";
import {combineLatest} from "rxjs";
import {map} from "rxjs/operators";
import {EAuthority} from "@shared/model";
import {StaffPrintService} from "@core/services/printing/staff-print.service";
import {CompanyStaffCard} from "@model/staff-card.model";

@Component({
  selector: 'app-staff-print-request-details',
  templateUrl: './staff-print-request-details.component.html',
  styleUrls: ['./staff-print-request-details.component.scss']
})
export class StaffPrintRequestDetailsComponent implements OnInit {

  public isCollapsed: boolean[] = [];

  printRequestId: number;
  printRequest: PrintRequest;
  printRequestStatuses: Lookup[];
  batchTypes: PrintBatchType[];
  countries: NationalityLookup[];
  cardStatuses: Lookup[];
  batchType = BatchType;
  printRequestStatus = PrintRequestStatus;
  isLoading: boolean;
  staffCard: CompanyStaffCard;
  ritualTypes: Lookup[];

  constructor(private i18nService: I18nService,
              private route: ActivatedRoute,
              private router: Router,
              private toastr: ToastService,
              private translate: TranslateService,
              private printService: StaffPrintService,
              private lookupsService: LookupService,
              private cardService: CardService,
              private authenticationService: AuthenticationService,
              private navigationService: NavigationService
  ) {
  }

  ngOnInit(): void {
    this.loadLookups();
    combineLatest([this.route.params, this.route.queryParams]).pipe(map(results => ({
      params: results[0].id,
      qParams: results[1]
    }))).subscribe(results => {
        this.printRequestId = +results.params; // (+) converts string 'id' to a number

        if (this.printRequestId) {
          this.isLoading = true;
          // load user details


          this.printService.find(this.printRequestId).subscribe(data => {
            if (data && data.id) {
              this.isLoading = false;
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
      },
      error => {
        this.toastr.error(this.translate.instant('general.route_id_param_not_found'),
          this.translate.instant('general.dialog_error_title'));
        this.goToList();
      }
    );
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
    this.router.navigate(['/staff-print-requests/list']);
  }

  goBack() {
    this.goToList();
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
    this.cardService.findCardStatuses().subscribe(result => {
      this.cardStatuses = result;
    });
    this.printService.findRitualTypes().subscribe((result) => {
      this.ritualTypes = result;
    });

  }

  getBatchType(batchType) {
    return this.batchTypes.find(b => b.code === batchType);
  }

}
