import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {NotificationManagementRoutingModule} from './notification-management-routing.module';
import {ReactiveFormsModule} from "@angular/forms";
import {SharedModule} from "@shared/shared.module";
import {TranslateModule} from "@ngx-translate/core";
import {NotificationListComponent} from './notification-list/notification-list.component';

@NgModule({
  declarations: [NotificationListComponent, NotificationListComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    SharedModule,
    NotificationManagementRoutingModule,
    TranslateModule
  ]
})
export class NotificationManagementModule { }
