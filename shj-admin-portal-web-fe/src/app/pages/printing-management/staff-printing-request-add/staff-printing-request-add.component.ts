import {ChangeDetectorRef, Component, OnInit, ViewChild} from '@angular/core';
import {PrintRequest} from "@model/print-request.model";
import {StaffStepOneComponent} from "@pages/printing-management/staff-printing-request-add/staff-step-one/staff-step-one.component";
import {StaffStepTwoComponent} from "@pages/printing-management/staff-printing-request-add/staff-step-two/staff-step-two.component";
import {StaffStepThreeComponent} from "@pages/printing-management/staff-printing-request-add/staff-step-three/staff-step-three.component";

@Component({
  selector: 'app-staff-printing-request-add',
  templateUrl: './staff-printing-request-add.component.html',
  styleUrls: ['./staff-printing-request-add.component.scss']
})
export class StaffPrintingRequestAddComponent implements OnInit {
  addedCards: any[] = [];
  printRequest: PrintRequest;
  selectedBatchTypes: string[] = [];
  isLoading: boolean;
  renderNxtButton = false;
  @ViewChild(StaffStepOneComponent, {static: false})
  stepOneComp: StaffStepOneComponent;

  @ViewChild(StaffStepTwoComponent, {static: false})
  stepTwoComp: StaffStepTwoComponent;

  @ViewChild(StaffStepThreeComponent, {static: false})
  stepThreeComp: StaffStepThreeComponent;

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
