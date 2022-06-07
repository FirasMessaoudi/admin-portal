import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {PrintService} from "@core/services/printing/print.service";
import {I18nService} from "@dcc-commons-ng/services";
import {PrintBatchType} from "@model/print-batch-type.model";
import {PrintRequestStorage} from "@pages/printing-management/printing-request-add-update/print-request-storage";
import {PrintRequest} from "@model/print-request.model";
import {ToastService} from "@shared/components/toast";
import {TranslateService} from "@ngx-translate/core";
import {CardService} from "@core/services";
import {NationalityLookup} from "@model/nationality-lookup.model";
import {LookupService} from "@core/utilities/lookup.service";

@Component({
  selector: 'app-step-two',
  templateUrl: './step-two.component.html',
  styleUrls: ['./step-two.component.scss']
})
export class StepTwoComponent implements OnInit {

  @Input()
  printRequest: PrintRequest;

  @Input()
  isLoading: boolean;

  batchTypes: PrintBatchType[];
  countries: NationalityLookup[] = [];
  selectedBatchTypes: string[] = [];
  currentPage: number = 1;
  pageSize: number = 10;

  @Output()
  public onSetPrintRequest: EventEmitter<any> = new EventEmitter<any>();

  @Output()
  public onSelectBatchTypes: EventEmitter<any[]> = new EventEmitter<any[]>();

  @Output()
  public onChangeLoading: EventEmitter<any> = new EventEmitter<any>();

  constructor(private printService: PrintService,
              private i18nService: I18nService,
              private toastr: ToastService,
              private translate: TranslateService,
              private printRequestStorage: PrintRequestStorage,
              private cardService: CardService,
              private lookupsService: LookupService) {
  }

  ngOnInit(): void {
    this.printRequestStorage.storage = null;
    this.loadLookups();
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
