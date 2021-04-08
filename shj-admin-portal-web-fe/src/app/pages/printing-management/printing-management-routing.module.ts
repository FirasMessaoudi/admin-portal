import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthenticationGuard} from "@core/guards/authentication.guard";
import {PrintingRequestListComponent} from "@pages/printing-management/printing-request-list/printing-request-list.component";
import {PrintingRequestDetailsComponent} from "@pages/printing-management/printing-request-details/printing-request-details.component";


const routes: Routes = [
  {path: 'print-requests/list', component: PrintingRequestListComponent, canActivate: [AuthenticationGuard]},
  {path: 'printing-requests/details', component: PrintingRequestDetailsComponent, canActivate: [AuthenticationGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PrintingManagementRoutingModule { }
