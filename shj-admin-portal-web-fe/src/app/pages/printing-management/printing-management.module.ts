import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {PrintingManagementRoutingModule} from './printing-management-routing.module';
import {PrintingRequestListComponent} from './printing-request-list/printing-request-list.component';
import {PrintingRequestDetailsComponent} from './printing-request-details/printing-request-details.component';
import {SharedModule} from "@shared/shared.module";
import {PrintingRequestAddUpdateComponent} from './printing-request-add-update/printing-request-add-update.component';
import {PrintingRequestAddUpdateModule} from './printing-request-add-update/printing-request-add-update.module';
import {StaffPrintingRequestListComponent} from "@pages/printing-management/staff-printing-management/staff-printing-request-list/staff-printing-request-list.component";
import {StaffPrintingRequestAddModule} from "@pages/printing-management/staff-printing-request-add/staff-printing-request-add.module";
import { StaffPrintingDetailsComponent } from './staff-printing-management/staff-printing-details/staff-printing-details.component';
import { ApplicantCardDetailsComponent } from './applicant-card-details/applicant-card-details.component';


@NgModule({
  declarations: [PrintingRequestListComponent, PrintingRequestDetailsComponent, PrintingRequestAddUpdateComponent, StaffPrintingRequestListComponent, StaffPrintingDetailsComponent, ApplicantCardDetailsComponent],
  imports: [
    CommonModule,
    PrintingManagementRoutingModule,
    SharedModule,
    PrintingRequestAddUpdateModule,
    StaffPrintingRequestAddModule
  ]
})
export class PrintingManagementModule { }
