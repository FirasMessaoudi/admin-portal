import {Routes} from "@angular/router";

export const NOTIFICATION_MANAGEMENT_ROUTES: Routes = [
  {
    path: '',
    loadChildren: () => import('@pages/notification-management/notification-management.module').then(m => m.NotificationManagementModule)
  }
];
