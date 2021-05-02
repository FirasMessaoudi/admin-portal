import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {DataRequestAddRoutingModule} from './data-request-add-routing.module';
import {StepOneComponent} from './step-one/step-one.component';
import {StepTwoComponent} from './step-two/step-two.component';
import {SharedModule} from "@shared/shared.module";
import {SuccessComponent} from "./success/success.component";
import {DataRequestStorage} from "./data-request-storage";


@NgModule({
  declarations: [StepOneComponent, StepTwoComponent, SuccessComponent],
  exports: [
    StepOneComponent,
    StepTwoComponent,
    SuccessComponent
  ],
  imports: [
    CommonModule,
    DataRequestAddRoutingModule,
    SharedModule
  ],
  providers: [DataRequestStorage]
})
export class DataRequestAddModule {
}
