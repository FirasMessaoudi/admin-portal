import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IncidentListComponent } from './incident-list/incident-list.component';
import { IncidentDetailsComponent } from './incident-details/incident-details.component';
import { ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from '@shared/shared.module';
import { IncidentManagementRoutingModule } from '@pages/incident-management/incident-management-routing.module';
import { TranslateModule } from '@ngx-translate/core';
//import { GoogleMapsModule } from '@angular/google-maps';

@NgModule({
  declarations: [IncidentListComponent, IncidentDetailsComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    SharedModule,
    IncidentManagementRoutingModule,
    TranslateModule,
  ],
})
export class IncidentManagementModule {}
