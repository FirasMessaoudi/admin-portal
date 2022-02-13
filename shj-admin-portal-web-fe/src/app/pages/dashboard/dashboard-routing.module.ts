import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthenticationGuard } from '@core/guards/authentication.guard';
import {
  CamerasComponent,
  GeneralNumbersComponent,
  IncidentsComponent,
  MainComponent,
  MobileComponent,
  RatingComponent,
  TransactionsComponent,
} from '.';

const routes: Routes = [
  {
    path: 'dashboard',
    component: MainComponent,
    canActivate: [AuthenticationGuard],
  },
  {
    path: 'dashboard/transactions',
    component: TransactionsComponent,
    canActivate: [AuthenticationGuard],
  },
  {
    path: 'dashboard/rating',
    component: RatingComponent,
    canActivate: [AuthenticationGuard],
  },
  {
    path: 'dashboard/mobile/:seasonYear',
    component: MobileComponent,
    canActivate: [AuthenticationGuard],
  },
  {
    path: 'dashboard/incidents/:seasonYear',
    component: IncidentsComponent,
    canActivate: [AuthenticationGuard],
  },
  {
    path: 'dashboard/general-numbers/:seasonYear',
    component: GeneralNumbersComponent,
    canActivate: [AuthenticationGuard],
  },
  {
    path: 'dashboard/cameras',
    component: CamerasComponent,
    canActivate: [AuthenticationGuard],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class DashboardRoutingModule {}
