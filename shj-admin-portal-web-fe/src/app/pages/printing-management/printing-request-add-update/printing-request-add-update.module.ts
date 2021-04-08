import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PrintingRequestAddUpdateRoutingModule } from './printing-request-add-update-routing.module';
import { StepOneComponent } from './step-one/step-one.component';
import { StepTwoComponent } from './step-two/step-two.component';
import { StepThreeComponent } from './step-three/step-three.component';
import {SharedModule} from "@shared/shared.module";


@NgModule({
  declarations: [StepOneComponent, StepTwoComponent, StepThreeComponent],
  exports: [
    StepOneComponent,
    StepTwoComponent,
    StepThreeComponent
  ],
  imports: [
    CommonModule,
    PrintingRequestAddUpdateRoutingModule,
    SharedModule
  ]
})
export class PrintingRequestAddUpdateModule { }
