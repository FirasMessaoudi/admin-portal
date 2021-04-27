import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {DataRequestManagementRoutingModule} from './data-request-management-routing.module';
import {DataRequestListComponent} from './data-request-list/data-request-list.component';
import {DataRequestDetailsComponent} from './data-request-details/data-request-details.component';
import {SharedModule} from "@shared/shared.module";
import {DataRequestAddComponent} from './data-request-add/data-request-add.component';
import {DataRequestAddModule} from './data-request-add/data-request-add.module';


@NgModule({
  declarations: [DataRequestListComponent, DataRequestDetailsComponent, DataRequestAddComponent],
  imports: [
    CommonModule,
    DataRequestManagementRoutingModule,
    SharedModule,
    DataRequestAddModule
  ]
})
export class DataRequestManagementModule { }
