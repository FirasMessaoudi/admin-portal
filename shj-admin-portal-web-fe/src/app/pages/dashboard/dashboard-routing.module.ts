import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthenticationGuard} from '@core/guards/authentication.guard';
import { MainComponent, CamerasComponent, GeneralNumbersComponent, IncidentsComponent,
          MobileComponent, RatingComponent, TransactionsComponent } from '.';

const routes: Routes = [
  {path: 'dashboard', component: MainComponent, canActivate: [AuthenticationGuard]},
  {path: 'dashboard/transactions', component: TransactionsComponent, canActivate: [AuthenticationGuard]},
  {path: 'dashboard/rating', component: RatingComponent, canActivate: [AuthenticationGuard]},
  {path: 'dashboard/mobile', component: MobileComponent, canActivate: [AuthenticationGuard]},
  {path: 'dashboard/incidents', component: IncidentsComponent, canActivate: [AuthenticationGuard]},
  {path: 'dashboard/general-numbers', component: GeneralNumbersComponent, canActivate: [AuthenticationGuard]},
  {path: 'dashboard/cameras', component: CamerasComponent, canActivate: [AuthenticationGuard]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
