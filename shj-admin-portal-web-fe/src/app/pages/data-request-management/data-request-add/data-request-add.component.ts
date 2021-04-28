import {AfterViewInit, ChangeDetectorRef, Component, OnInit, ViewChild} from '@angular/core';
import {StepOneComponent} from "@pages/data-request-management/data-request-add/step-one/step-one.component";
import {StepTwoComponent} from "@pages/data-request-management/data-request-add/step-two/step-two.component";
import {SuccessComponent} from "@pages/data-request-management/data-request-add/success/success.component";
import {Router} from "@angular/router";
import {DataRequestStorage} from "@pages/data-request-management/data-request-add/data-request-storage";

@Component({
  selector: 'app-data-request-add-update',
  templateUrl: './data-request-add.component.html',
  styleUrls: ['./data-request-add.component.scss']
})
export class DataRequestAddComponent implements OnInit {

  @ViewChild(StepOneComponent, {static: false})
  stepOneComp: StepOneComponent;

  @ViewChild(StepTwoComponent, {static: false})
  stepTwoComp: StepTwoComponent;

  constructor(private cdr: ChangeDetectorRef,
              public dataRequestStorage: DataRequestStorage) { }

  ngOnInit(): void {
  }

  saveStepOne() {
    this.stepTwoComp.dataRequest = this.dataRequestStorage.storage;
    this.cdr.detectChanges();
  }

  confirm() {
    this.stepTwoComp.confirmRequest();
    this.cdr.detectChanges();
  }

}
