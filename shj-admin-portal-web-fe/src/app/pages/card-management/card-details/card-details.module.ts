import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CardDetailsRoutingModule } from './card-details-routing.module';
import { MainDetailsComponent } from './main-details/main-details.component';
import { HamlahDetailsComponent } from './hamlah-details/hamlah-details.component';
import { HealthDetailsComponent } from './health-details/health-details.component';
import { TafweejDetailsComponent } from './tafweej-details/tafweej-details.component';
import { CardDetailsComponent } from './card-details.component';


@NgModule({
  declarations: [MainDetailsComponent, HamlahDetailsComponent, HealthDetailsComponent, TafweejDetailsComponent, CardDetailsComponent],
  imports: [
    CommonModule,
    CardDetailsRoutingModule
  ]
})
export class CardDetailsModule { }
