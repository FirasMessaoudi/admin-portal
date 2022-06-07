import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {PrintBatchType} from "@model/print-batch-type.model";
import {NationalityLookup} from "@model/nationality-lookup.model";
import {BatchType} from "@model/enum/batch-type.enum";
import {PrintRequest} from "@model/print-request.model";
import {Router} from "@angular/router";
import {ToastService} from "@shared/components/toast";
import {I18nService} from "@dcc-commons-ng/services";
import {TranslateService} from "@ngx-translate/core";
import {LookupService} from "@core/utilities/lookup.service";
import {AuthenticationService, CardService} from "@core/services";
import {EAuthority} from "@shared/model";
import {StaffPrintRequestStorage} from "@pages/printing-management/staff-printing-request-add/staff-print-request-storage";
import {StaffPrintService} from "@core/services/printing/staff-print.service";

@Component({
  selector: 'app-staff-step-three',
  templateUrl: './staff-step-three.component.html',
  styleUrls: ['./staff-step-three.component.scss']
})
export class StaffStepThreeComponent implements OnInit, OnChanges {

  public isCollapsed: boolean[] = [];
  batchTypes: PrintBatchType[];
  countries: NationalityLookup[];
  batchType = BatchType;
  description = '';

  @Input()
  printRequest: PrintRequest;

  @Input()
  isLoading: boolean;

  @Output()
  public onChangeLoading: EventEmitter<any> = new EventEmitter<any>();

  constructor(private printService: StaffPrintService,
              private router: Router,
              private toastr: ToastService,
              private i18nService: I18nService,
              private translate: TranslateService,
              private lookupsService: LookupService,
              private cardService: CardService,
              private printRequestStorage: StaffPrintRequestStorage,
              private authenticationService: AuthenticationService) {

  }

  ngOnInit(): void {
    this.loadLookups();
  }

  ngOnChanges(changes: SimpleChanges) {
    //TODO: check why it is needed
    // if (changes.printRequest) {
    //   this.printRequest = changes.printRequest.currentValue;
    //   console.log(this.printRequest);
    //   if (this.printRequest && this.printRequest.printRequestBatches.length > 0) {
    //     this.printRequest.printRequestBatches.forEach(element => {
    //       element.printRequestBatchCards.forEach(batch => {
    //         this.cardService.findStaffCardById(batch.cardId).subscribe(
    //           res => batch.staffCard = res
    //         )
    //       })
    //     })
    //   }
    // }
  }

  get canSeeCardDetails(): boolean {
    return this.authenticationService.hasAuthority(EAuthority.VIEW_CARD_DETAILS);
  }

  loadLookups() {
    this.printService.findPrintBatchTypes().subscribe(result => {
      this.batchTypes = result;
    });
    this.cardService.findCountries().subscribe(result => {
      this.countries = result;
    });
  }

  lookupService(): LookupService {
    return this.lookupsService;
  }

  confirm() {
    console.log("confirm batching");
    this.onChangeLoading.emit(true);
    this.printService.confirm(this.printRequest).subscribe(result => {
      this.onChangeLoading.emit(false);
      if (result.hasOwnProperty("errors") && result.errors) {
        console.log("Error");
        this.toastr.warning(this.translate.instant("printing-management.dialog_confirm_request_error_text"), this.translate.instant("general.dialog_error_title"));
      } else {
        this.printRequestStorage.storage = result;
        this.router.navigate(['/staff-print-requests/success']).then(r => {
        });
      }
    });
  }

  getBatchType(batchType) {
    return this.batchTypes.find(b => b.code === batchType);
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

}
