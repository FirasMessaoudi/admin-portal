import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {PrintRequest} from "@model/print-request.model";
import {PrintService} from "@core/services/printing/print.service";
import {Router} from "@angular/router";
import {ToastService} from "@shared/components/toast";
import {I18nService} from "@dcc-commons-ng/services";
import {TranslateService} from "@ngx-translate/core";
import {PrintRequestStorage} from "@pages/printing-management/printing-request-add-update/print-request-storage";
import {PrintBatchType} from "@model/print-batch-type.model";
import {LookupService} from "@core/utilities/lookup.service";
import {AuthenticationService, CardService} from "@core/services";
import {CountryLookup} from "@model/country-lookup.model";
import {BatchType} from "@model/enum/batch-type.enum";
import {EAuthority} from "@shared/model";

@Component({
  selector: 'app-step-three',
  templateUrl: './step-three.component.html',
  styleUrls: ['./step-three.component.scss']
})
export class StepThreeComponent implements OnInit {

  batchTypes: PrintBatchType[];
  countries: CountryLookup[];
  batchType = BatchType;

  @Input()
  printRequest: PrintRequest;

  @Input()
  isLoading: boolean;

  @Output()
  public onChangeLoading: EventEmitter<any> = new EventEmitter<any>();

  constructor(private printService: PrintService,
              private router: Router,
              private toastr: ToastService,
              private i18nService: I18nService,
              private translate: TranslateService,
              private lookupsService: LookupService,
              private cardService: CardService,
              private printRequestStorage: PrintRequestStorage,
              private authenticationService: AuthenticationService) {

  }

  ngOnInit(): void {
    this.loadLookups();
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
      if (result.hasOwnProperty("errors") && result.errors) {
        this.onChangeLoading.emit(false);
        console.log("Error");
        this.toastr.warning(this.translate.instant("printing-management.dialog_confirm_request_error_text"), this.translate.instant("general.dialog_error_title"));
      } else {
        this.onChangeLoading.emit(false);
        this.printRequestStorage.storage = result;
        this.router.navigate(['/print-requests/success']).then(r => {
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
