import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CardManagementRoutingModule } from './card-management-routing.module';
import { CardListComponent } from './card-list/card-list.component';
import { ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from '@shared/shared.module';
import { TranslateModule } from '@ngx-translate/core';
import { CardDetailsModule } from './card-details/card-details.module';
import { StaffCardListComponent } from './staff-card-list/staff-card-list.component';

@NgModule({
  declarations: [CardListComponent, StaffCardListComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    SharedModule,
    CardManagementRoutingModule,
    TranslateModule,
    CardDetailsModule,
  ],
})
export class CardManagementModule {}
