import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthenticationGuard} from "@core/guards/authentication.guard";
import {DataRequestListComponent} from "@pages/data-request-management/data-request-list/data-request-list.component";
import {DataRequestDetailsComponent} from "@pages/data-request-management/data-request-details/data-request-details.component";
import {DataRequestAddComponent} from "@pages/data-request-management/data-request-add/data-request-add.component";
import {SuccessComponent} from "@pages/data-request-management/data-request-add/success/success.component";


const routes: Routes = [
  {path: 'data-requests/list', component: DataRequestListComponent, canActivate: [AuthenticationGuard]},
  {path: 'data-requests/details/:id', component: DataRequestDetailsComponent, canActivate: [AuthenticationGuard]},
  {path: 'data-requests/create', component: DataRequestAddComponent, canActivate: [AuthenticationGuard]},
  {path: 'data-requests/success', component: SuccessComponent, canActivate: [AuthenticationGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DataRequestManagementRoutingModule { }
