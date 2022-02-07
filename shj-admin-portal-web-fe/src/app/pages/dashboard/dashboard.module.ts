import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import * as fromMyDashboard from './';
import { DashboardRoutingModule } from './dashboard-routing.module';
import {TranslateModule} from '@ngx-translate/core';
import {ChartsModule} from "ng2-charts";
import { SharedModule } from '@app/_shared/shared.module';
import { GoogleMapsModule } from '@angular/google-maps';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    TranslateModule,
    ChartsModule,
    GoogleMapsModule,
    DashboardRoutingModule],
  declarations: [
    ...fromMyDashboard.my_dashboard,
  ]
})
export class DashboardModule { }
