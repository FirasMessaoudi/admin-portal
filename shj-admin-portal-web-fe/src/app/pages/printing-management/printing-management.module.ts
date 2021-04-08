import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {PrintingManagementRoutingModule} from './printing-management-routing.module';
import {PrintingRequestListComponent} from './printing-request-list/printing-request-list.component';
import {PrintingRequestDetailsComponent} from './printing-request-details/printing-request-details.component';
import {SharedModule} from "@shared/shared.module";


@NgModule({
  declarations: [PrintingRequestListComponent, PrintingRequestDetailsComponent],
  imports: [
    CommonModule,
    PrintingManagementRoutingModule,
    SharedModule
  ]
})
export class PrintingManagementModule { }
