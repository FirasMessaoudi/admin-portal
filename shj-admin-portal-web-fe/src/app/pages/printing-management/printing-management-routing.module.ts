import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthenticationGuard} from "@core/guards/authentication.guard";
import {PrintingRequestListComponent} from "@pages/printing-management/printing-request-list/printing-request-list.component";


const routes: Routes = [
  {path: 'printing/list', component: PrintingRequestListComponent, canActivate: [AuthenticationGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PrintingManagementRoutingModule { }
