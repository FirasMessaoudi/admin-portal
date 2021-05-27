import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {PrintService} from "@core/services/print/print.service";
import {I18nService} from "@dcc-commons-ng/services";
import {PrintRequest} from "@model/print-request.model";
import {PrintBatchType} from "@model/print-batch-type.model";

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

  @Output()
  public onSelectBatchTypes: EventEmitter<any[]> = new EventEmitter<any[]>();

  constructor(private printService: PrintService,
              private i18nService: I18nService) {
  }

  ngOnInit(): void {
    this.loadLookups();
  }

  get currentLanguage(): string {
    return this.i18nService.language;
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
}
