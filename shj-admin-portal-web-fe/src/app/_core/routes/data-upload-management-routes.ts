import {Routes} from "@angular/router";

export const DATA_UPLOAD_MANAGEMENT_ROUTES: Routes = [
  {
    path: '',
    loadChildren: () => import('@pages/data-request-management/data-request-management.module').then(m => m.DataRequestManagementModule)
  }
];
