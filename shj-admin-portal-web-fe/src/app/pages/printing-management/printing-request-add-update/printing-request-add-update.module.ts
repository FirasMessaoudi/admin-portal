import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PrintingRequestAddUpdateRoutingModule } from './printing-request-add-update-routing.module';
import { StepOneComponent } from './step-one/step-one.component';
import { StepTwoComponent } from './step-two/step-two.component';
import { StepThreeComponent } from './step-three/step-three.component';


@NgModule({
  declarations: [StepOneComponent, StepTwoComponent, StepThreeComponent],
  imports: [
    CommonModule,
    PrintingRequestAddUpdateRoutingModule
  ]
})
export class PrintingRequestAddUpdateModule { }
