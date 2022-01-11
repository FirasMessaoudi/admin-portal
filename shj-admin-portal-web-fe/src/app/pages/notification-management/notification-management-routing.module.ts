import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthenticationGuard } from '@core/guards/authentication.guard';
import { NotificationListComponent } from '@pages/notification-management/notification-list/notification-list.component';
import { SystemDefinedNotificationDetailsComponent } from '@pages/notification-management/system-defined-notification-details/system-defined-notification-details.component';
import { UserDefinedNotificationListComponent } from '@pages/notification-management/user-defined-notification-list/user-defined-notification-list.component';
import { UserDefinedNotificationDetailsComponent } from '@pages/notification-management/user-defined-notification-details/user-defined-notification-details.component';
import { UserDefinedNotificationAddComponent } from '@pages/notification-management/user-defined-notification-add/user-defined-notification-add.component';

const routes: Routes = [
  {
    path: 'notification/list',
    component: NotificationListComponent,
    canActivate: [AuthenticationGuard],
  },
  {
    path: 'system-defined-notification/details/:id',
    component: SystemDefinedNotificationDetailsComponent,
    canActivate: [AuthenticationGuard],
  },
  {
    path: 'user-defined-notification/list',
    component: UserDefinedNotificationListComponent,
    canActivate: [AuthenticationGuard],
  },
  {
    path: 'user-defined-notification/details/:id',
    component: UserDefinedNotificationDetailsComponent,
    canActivate: [AuthenticationGuard],
  },
  {
    path: 'user-defined-notification/create',
    component: UserDefinedNotificationAddComponent,
    canActivate: [AuthenticationGuard],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class NotificationManagementRoutingModule {}
