import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthenticationGuard} from "@core/guards/authentication.guard";
import {NotificationListComponent} from "@pages/notification-management/notification-list/notification-list.component";
import {SystemDefinedNotificationDetailsComponent} from "@pages/notification-management/system-defined-notification-details/system-defined-notification-details.component";


const routes: Routes = [
  {path: 'notification/list', component: NotificationListComponent, canActivate: [AuthenticationGuard]},
  {
    path: 'system-defined-notification/details/:id',
    component: SystemDefinedNotificationDetailsComponent,
    canActivate: [AuthenticationGuard]
  },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class NotificationManagementRoutingModule {
}
