import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {PrintingRequestAddUpdateRoutingModule} from './printing-request-add-update-routing.module';
import {StepOneComponent} from './step-one/step-one.component';
import {StepTwoComponent} from './step-two/step-two.component';
import {StepThreeComponent} from './step-three/step-three.component';
import {SharedModule} from "@shared/shared.module";
import {StepFourComponent} from './step-four/step-four.component';
import {SuccessComponent} from './success/success.component';
import {PrintRequestStorage} from "@pages/printing-management/printing-request-add-update/print-request-storage";
import {NgbCollapseModule, NgbModule} from "@ng-bootstrap/ng-bootstrap";


@NgModule({
  declarations: [StepOneComponent, StepTwoComponent, StepThreeComponent, StepFourComponent, SuccessComponent],
  exports: [
    StepOneComponent,
    StepTwoComponent,
    StepThreeComponent,
    StepFourComponent,
    SuccessComponent
  ],
  imports: [
    CommonModule,
    PrintingRequestAddUpdateRoutingModule,
    SharedModule,
    NgbModule
  ],
  providers: [PrintRequestStorage]
})
export class PrintingRequestAddUpdateModule {
}
