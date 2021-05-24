import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {Card} from "@model/card.model";
import {PrintService} from "@core/services/print/print.service";

@Component({
  selector: 'app-printing-request-add-update',
  templateUrl: './printing-request-add-update.component.html',
  styleUrls: ['./printing-request-add-update.component.scss']
})
export class PrintingRequestAddUpdateComponent implements OnInit {

  constructor(private cdr: ChangeDetectorRef,
              private printService: PrintService) { }

  selectedCards: Card[] = [];

  ngOnInit(): void {
  }

  saveStepOne() {
    this.cdr.detectChanges();
    this.printService.save(this.selectedCards.map(card => card.applicantRitual.applicant.id)).subscribe(
      res => console.log(res)
    );
  }

  saveStepTwo() {
    this.cdr.detectChanges();
  }

  confirm() {

  }

  setSelectedCards(cards) {
    this.selectedCards = cards;
  }
}
