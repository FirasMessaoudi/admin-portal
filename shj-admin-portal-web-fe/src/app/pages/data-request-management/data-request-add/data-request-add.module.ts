import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {DataRequestAddRoutingModule} from './data-request-add-routing.module';
import {StepOneComponent} from './step-one/step-one.component';
import {StepTwoComponent} from './step-two/step-two.component';
import {StepThreeComponent} from './step-three/step-three.component';
import {SharedModule} from "@shared/shared.module";
import {StepFourComponent} from './step-four/step-four.component';
import {SuccessComponent} from "./success/success.component";


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
    DataRequestAddRoutingModule,
    SharedModule
  ]
})
export class DataRequestAddModule {
}
