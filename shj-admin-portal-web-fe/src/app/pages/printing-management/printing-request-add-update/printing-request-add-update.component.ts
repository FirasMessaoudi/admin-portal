import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {Card} from "@model/card.model";
import {PrintService} from "@core/services/print/print.service";
import {PrintRequest} from "@model/print-request.model";

@Component({
  selector: 'app-printing-request-add-update',
  templateUrl: './printing-request-add-update.component.html',
  styleUrls: ['./printing-request-add-update.component.scss']
})
export class PrintingRequestAddUpdateComponent implements OnInit {

  constructor(private cdr: ChangeDetectorRef,
              private printService: PrintService) {
  }

  addedCards: Card[] = [];
  printRequest: PrintRequest;
  selectedBatchTypes: string[] = [];

  ngOnInit(): void {
  }

  saveStepOne() {
    this.cdr.detectChanges();
    this.printService.save(this.addedCards.map(card => card.applicantRitual.applicant.id)).subscribe(
      res => {
        this.printService.find(res.id).subscribe(
          result => this.printRequest = result
        );
      }
    );
  }

  saveStepTwo() {
    this.cdr.detectChanges();
    console.log(this.selectedBatchTypes);
    this.printService.batch(this.printRequest.id, this.selectedBatchTypes).subscribe(
      result => this.printRequest = result
    )
  }

  confirm() {

  }

  setAddedCards(cards) {
    this.addedCards = cards;
  }

  setSelectedBatchTypes(batchTypes) {
    this.selectedBatchTypes = batchTypes;
  }
}
