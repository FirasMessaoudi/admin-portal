import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {PrintRequest} from "@model/print-request.model";
import {PrintBatchType} from "@model/print-batch-type.model";
import {CountryLookup} from "@model/country-lookup.model";
import {I18nService} from "@dcc-commons-ng/services";
import {ToastService} from "@shared/components/toast";
import {TranslateService} from "@ngx-translate/core";
import {CardService} from "@core/services";
import {LookupService} from "@core/utilities/lookup.service";
import {StaffPrintRequestStorage} from "@pages/printing-management/staff-printing-request-add/staff-print-request-storage";
import {StaffPrintService} from "@core/services/printing/staff-print.service";

@Component({
  selector: 'app-staff-step-two',
  templateUrl: './staff-step-two.component.html',
  styleUrls: ['./staff-step-two.component.scss']
})
export class StaffStepTwoComponent implements OnInit, OnChanges {


  @Input()
  printRequest: PrintRequest;

  @Input()
  isLoading: boolean;

  batchTypes: PrintBatchType[];
  countries: CountryLookup[] = [];
  selectedBatchTypes: string[] = [];
  currentPage: number = 1;
  pageSize: number = 10;

  @Output()
  public onSetPrintRequest: EventEmitter<any> = new EventEmitter<any>();

  @Output()
  public onSelectBatchTypes: EventEmitter<any[]> = new EventEmitter<any[]>();

  @Output()
  public onChangeLoading: EventEmitter<any> = new EventEmitter<any>();

  constructor(private printService: StaffPrintService,
              private i18nService: I18nService,
              private toastr: ToastService,
              private translate: TranslateService,
              private printRequestStorage: StaffPrintRequestStorage,
              private cardService: CardService,
              private lookupsService: LookupService) {
  }

  ngOnInit(): void {
    this.printRequestStorage.storage = null;
    this.loadLookups();
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes.printRequest) {
      this.printRequest = changes.printRequest.currentValue;
      if (this.printRequest && this.printRequest.printRequestCards.length > 0) {
        this.printRequest.printRequestCards.forEach(element => {
          this.cardService.findStaffCardById(element.cardId).subscribe(
            res => element.staffCard = res,
          )
        })
      }
    }
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  clearSpaces(s) {
    return s.replace(/\s/g, '');
  }

  selectBatchType(event, code) {
    const selectedIndex = this.selectedBatchTypes.indexOf(code);
    if (event.target.checked) {
      this.selectedBatchTypes.push(code)
    } else {
      this.selectedBatchTypes.splice(selectedIndex, 1);
    }
    this.onSelectBatchTypes.emit(this.selectedBatchTypes);
  }

  loadLookups() {
    this.printService.findPrintBatchTypes().subscribe(result => {
      this.batchTypes = result;
    })
    this.cardService.findCountries().subscribe(result => {
      this.countries = result;
    });
  }

  batch() {
    this.onChangeLoading.emit(true);
    this.printService.batch(this.printRequest, this.selectedBatchTypes).subscribe(
      result => {
        this.onChangeLoading.emit(false);
        if (result.hasOwnProperty("errors") && result.errors) {
          console.log("Error");
          this.toastr.warning(this.translate.instant("printing-management.dialog_confirm_request_error_text"), this.translate.instant("general.dialog_error_title"));
        } else {
          this.onSetPrintRequest.emit(result);
          this.printRequestStorage.storage = result;
        }
      }
    )
  }

  setCurrentPage(page: number) {
    this.currentPage = page;
  }

  getTotalPages(total, size): number {
    return Math.floor((total + size - 1) / size);
  }

  lookupService(): LookupService {
    return this.lookupsService;
  }

}
