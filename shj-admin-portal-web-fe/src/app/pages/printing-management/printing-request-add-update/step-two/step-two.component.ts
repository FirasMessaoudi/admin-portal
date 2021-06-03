import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {PrintService} from "@core/services/print/print.service";
import {I18nService} from "@dcc-commons-ng/services";
import {PrintBatchType} from "@model/print-batch-type.model";
import {PrintRequestStorage} from "@pages/printing-management/printing-request-add-update/print-request-storage";
import {PrintRequest} from "@model/print-request.model";

@Component({
  selector: 'app-step-two',
  templateUrl: './step-two.component.html',
  styleUrls: ['./step-two.component.scss']
})
export class StepTwoComponent implements OnInit {

  @Input()
  printRequest: PrintRequest;

  batchTypes: PrintBatchType[];
  selectedBatchTypes: string[] = [];
  currentPage: number = 1;
  pageSize: number = 10;

  @Output()
  public onSetPrintRequest: EventEmitter<any> = new EventEmitter<any>();

  @Output()
  public onSelectBatchTypes: EventEmitter<any[]> = new EventEmitter<any[]>();

  constructor(private printService: PrintService,
              private i18nService: I18nService,
              private printRequestStorage: PrintRequestStorage) {
  }

  ngOnInit(): void {
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
  }

  batch() {
    this.printService.batch(this.printRequest.id, this.selectedBatchTypes).subscribe(
      result => {
        this.printRequestStorage.storage = result;
        this.onSetPrintRequest.emit(result)
      }
    )
  }

  setCurrentPage(page: number) {
    this.currentPage = page;
  }

  getTotalPages(total, size): number {
    return Math.floor((total + size) / size);
  }
}
