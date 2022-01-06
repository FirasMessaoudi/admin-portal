import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {StaffPrintingRequestAddRoutingModule} from './staff-printing-request-add-routing.module';
import {StaffPrintingRequestAddComponent} from './staff-printing-request-add.component';
import {StaffStepOneComponent} from './staff-step-one/staff-step-one.component';
import {StaffStepTwoComponent} from './staff-step-two/staff-step-two.component';
import {StaffStepThreeComponent} from './staff-step-three/staff-step-three.component';
import {StaffStepFourComponent} from './staff-step-four/staff-step-four.component';
import {StaffSuccessComponent} from './staff-success/staff-success.component';
import {SharedModule} from "@shared/shared.module";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {StaffPrintRequestStorage} from "@pages/printing-management/staff-printing-request-add/staff-print-request-storage";


@NgModule({
  declarations: [StaffPrintingRequestAddComponent, StaffStepOneComponent, StaffStepTwoComponent, StaffStepThreeComponent, StaffStepFourComponent, StaffSuccessComponent],
  imports: [
    CommonModule,
    StaffPrintingRequestAddRoutingModule,
    SharedModule,
    NgbModule
  ],
  providers: [StaffPrintRequestStorage]
})
export class StaffPrintingRequestAddModule {
}
