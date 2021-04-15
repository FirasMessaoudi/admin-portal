import {Routes} from "@angular/router";

export const DATA_UPLOAD_MANAGEMENT_ROUTES: Routes = [
  {
    path: '',
    loadChildren: () => import('@pages/data-upload-management/data-upload-management.module').then(m => m.DataUploadManagementModule)
  }
];
