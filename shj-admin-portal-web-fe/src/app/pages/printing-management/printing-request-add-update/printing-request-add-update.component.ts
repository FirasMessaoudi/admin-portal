import {ChangeDetectorRef, Component, OnInit, ViewChild} from '@angular/core';
import {ApplicantCard} from "@model/card.model";
import {PrintRequest} from "@model/print-request.model";
import {StepOneComponent} from "@pages/printing-management/printing-request-add-update/step-one/step-one.component";
import {StepTwoComponent} from "@pages/printing-management/printing-request-add-update/step-two/step-two.component";
import {StepThreeComponent} from "@pages/printing-management/printing-request-add-update/step-three/step-three.component";

@Component({
  selector: 'app-printing-request-add-update',
  templateUrl: './printing-request-add-update.component.html',
  styleUrls: ['./printing-request-add-update.component.scss']
})
export class PrintingRequestAddUpdateComponent implements OnInit {

  addedCards: ApplicantCard[] = [];
  printRequest: PrintRequest;
  selectedBatchTypes: string[] = [];
  isLoading: boolean;
  renderNxtButton = false;
  @ViewChild(StepOneComponent, {static: false})
  stepOneComp: StepOneComponent;

  @ViewChild(StepTwoComponent, {static: false})
  stepTwoComp: StepTwoComponent;

  @ViewChild(StepThreeComponent, {static: false})
  stepThreeComp: StepThreeComponent;

  constructor(private cdr: ChangeDetectorRef) {
  }

  ngOnInit(): void {
  }

  saveStepOne() {
    this.stepOneComp.create();
    this.cdr.detectChanges();
  }

  saveStepTwo() {
    this.stepTwoComp.batch();
    this.cdr.detectChanges();
  }

  confirm() {
    this.stepThreeComp.confirm();
    this.cdr.detectChanges();
  }

  setPrintRequest(printRequest) {
    this.printRequest = printRequest;
  }

  setAddedCards(cards) {
    this.addedCards = cards;
    this.renderNxtButton = cards.length > 0;
  }

  setSelectedBatchTypes(batchTypes) {
    this.selectedBatchTypes = batchTypes;
  }

  setLoading(event) {
    this.isLoading = event;
  }

}
