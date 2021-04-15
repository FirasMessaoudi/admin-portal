import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthenticationGuard} from "@core/guards/authentication.guard";
import {DataUploadRequestListComponent} from "@pages/data-upload-management/data-upload-request-list/data-upload-request-list.component";
import {DataUploadRequestDetailsComponent} from "@pages/data-upload-management/data-upload-request-details/data-upload-request-details.component";
import {DataUploadRequestAddUpdateComponent} from "@pages/data-upload-management/data-upload-request-add-update/data-upload-request-add-update.component";


const routes: Routes = [
  {path: 'data-upload-requests/list', component: DataUploadRequestListComponent, canActivate: [AuthenticationGuard]},
  {path: 'data-upload-requests/details/:id', component: DataUploadRequestDetailsComponent, canActivate: [AuthenticationGuard]},
  {path: 'data-upload-requests/create', component: DataUploadRequestAddUpdateComponent, canActivate: [AuthenticationGuard]},
  {path: 'data-upload-requests/update/:id', component: DataUploadRequestAddUpdateComponent, canActivate: [AuthenticationGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DataUploadManagementRoutingModule { }
