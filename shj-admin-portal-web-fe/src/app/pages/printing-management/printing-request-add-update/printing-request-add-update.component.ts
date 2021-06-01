import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {ApplicantCard} from "@model/card.model";
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

  addedCards: ApplicantCard[] = [];
  printRequest: PrintRequest;
  selectedBatchTypes: string[] = [];

  ngOnInit(): void {
  }

  saveStepOne() {
    this.cdr.detectChanges();
    this.printService.save(this.addedCards.map(card => card.id)).subscribe(
      res => {
        this.printService.find(res.id).subscribe(
          result => this.printRequest = result
        );
      }
    );
  }

  saveStepTwo() {
    this.cdr.detectChanges();
    this.printService.batch(this.printRequest.id, this.selectedBatchTypes).subscribe(
      result => this.printRequest = result
    )
  }

  confirm() {
    console.log("printing request confirm")
    this.printService.confirm(this.printRequest).subscribe(
      result => console.log(result)
    )
  }

  setAddedCards(cards) {
    this.addedCards = cards;
  }

  setSelectedBatchTypes(batchTypes) {
    this.selectedBatchTypes = batchTypes;
  }
}
