import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {NotificationManagementRoutingModule} from './notification-management-routing.module';
import {ReactiveFormsModule} from "@angular/forms";
import {SharedModule} from "@shared/shared.module";
import {TranslateModule} from "@ngx-translate/core";
import {NotificationListComponent} from './notification-list/notification-list.component';
import {SystemDefinedNotificationDetailsComponent} from './system-defined-notification-details/system-defined-notification-details.component';
import {UserDefinedNotificationListComponent} from "@pages/notification-management/user-defined-notification-list/user-defined-notification-list.component";
import {UserDefinedNotificationDetailsComponent} from "@pages/notification-management/user-defined-notification-details/user-defined-notification-details.component";
import {UserDefinedNotificationAddComponent} from './user-defined-notification-add/user-defined-notification-add.component';

@NgModule({
  declarations:
    [
      NotificationListComponent,
      NotificationListComponent,
      SystemDefinedNotificationDetailsComponent,
      UserDefinedNotificationListComponent,
      UserDefinedNotificationDetailsComponent,
      UserDefinedNotificationAddComponent
    ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    SharedModule,
    NotificationManagementRoutingModule,
    TranslateModule
  ]
})
export class NotificationManagementModule {
}
