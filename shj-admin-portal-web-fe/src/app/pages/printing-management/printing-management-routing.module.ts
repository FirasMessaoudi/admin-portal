import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthenticationGuard} from "@core/guards/authentication.guard";
import {PrintingRequestListComponent} from "@pages/printing-management/printing-request-list/printing-request-list.component";
import {PrintingRequestDetailsComponent} from "@pages/printing-management/printing-request-details/printing-request-details.component";
import {PrintingRequestAddUpdateComponent} from "@pages/printing-management/printing-request-add-update/printing-request-add-update.component";
import {SuccessComponent} from '@pages/printing-management/printing-request-add-update/success/success.component';
import {StaffPrintingRequestListComponent} from "@pages/printing-management/staff-printing-management/staff-printing-request-list/staff-printing-request-list.component";
import {StaffPrintingDetailsComponent} from './staff-printing-management/staff-printing-details/staff-printing-details.component';
import {StaffPrintingRequestAddComponent} from "@pages/printing-management/staff-printing-request-add/staff-printing-request-add.component";
import {StaffSuccessComponent} from "@pages/printing-management/staff-printing-request-add/staff-success/staff-success.component";
import {StaffPrintRequestDetailsComponent} from "@pages/printing-management/staff-printing-management/staff-print-request-details/staff-print-request-details.component";
import {StaffCardDetailsComponent} from "@pages/card-management/staff-card-details/staff-card-details.component";


const routes: Routes = [
  {path: 'print-requests/list', component: PrintingRequestListComponent, canActivate: [AuthenticationGuard]},
  {path: 'print-requests/details/:id', component: PrintingRequestDetailsComponent, canActivate: [AuthenticationGuard]},
  {
    path: 'print-requests/create',
    component: PrintingRequestAddUpdateComponent,
    canActivate: [AuthenticationGuard]
  },
  {
    path: 'staff-print-requests/create',
    component: StaffPrintingRequestAddComponent,
    canActivate: [AuthenticationGuard]
  },
  {
    path: 'staff-print-requests/details/:id',
    component: StaffPrintRequestDetailsComponent,
    canActivate: [AuthenticationGuard]
  },
  {path: 'print-requests/success', component: SuccessComponent, canActivate: [AuthenticationGuard]},
  {path: 'staff-print-requests/success', component: StaffSuccessComponent, canActivate: [AuthenticationGuard]},
  {path: 'print-requests/update/:id', component: PrintingRequestAddUpdateComponent, canActivate: [AuthenticationGuard]},
  {path: 'staff-print-requests/list', component: StaffPrintingRequestListComponent, canActivate: [AuthenticationGuard]},
  {path: 'staff-print-requests/details', component: StaffPrintingDetailsComponent, canActivate: [AuthenticationGuard]},
  {
    path: 'staff-print-requests/staff-card-details/:id',
    component: StaffCardDetailsComponent,
    canActivate: [AuthenticationGuard]
  },


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PrintingManagementRoutingModule { }
