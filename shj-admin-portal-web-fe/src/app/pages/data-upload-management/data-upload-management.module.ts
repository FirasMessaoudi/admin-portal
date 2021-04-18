import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {DataUploadManagementRoutingModule} from './data-upload-management-routing.module';
import {DataUploadRequestListComponent} from './data-upload-request-list/data-upload-request-list.component';
import {DataUploadRequestDetailsComponent} from './data-upload-request-details/data-upload-request-details.component';
import {SharedModule} from "@shared/shared.module";
import {DataUploadRequestAddUpdateComponent} from './data-upload-request-add-update/data-upload-request-add-update.component';
import {DataUploadRequestAddUpdateModule} from './data-upload-request-add-update/data-upload-request-add-update.module';


@NgModule({
  declarations: [DataUploadRequestListComponent, DataUploadRequestDetailsComponent, DataUploadRequestAddUpdateComponent],
  imports: [
    CommonModule,
    DataUploadManagementRoutingModule,
    SharedModule,
    DataUploadRequestAddUpdateModule
  ]
})
export class DataUploadManagementModule { }
