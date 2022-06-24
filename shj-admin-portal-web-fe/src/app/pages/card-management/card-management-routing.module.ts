import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthenticationGuard } from '@core/guards/authentication.guard';
import { CardListComponent } from '@pages/card-management/card-list/card-list.component';
import { CardDetailsComponent } from '@pages/card-management/card-details/card-details.component';
import { MainDetailsComponent } from '@pages/card-management/card-details/main-details/main-details.component';
import { StaffCardListComponent } from '@pages/card-management/staff-card-list/staff-card-list.component';
import { StaffCardDetailsComponent } from '@pages/card-management/staff-card-details/staff-card-details.component';
import { PrintCardComponent } from '@pages/card-management/print-card/print-card.component';

const routes: Routes = [
  {
    path: 'cards/list',
    component: CardListComponent,
    canActivate: [AuthenticationGuard],
  },
  {
    path: 'cards/details/:id',
    component: CardDetailsComponent,
    canActivate: [AuthenticationGuard],
  },
  {
    path: 'cards/details/main/:id',
    component: MainDetailsComponent,
    canActivate: [AuthenticationGuard],
  },
  {
    path: 'staff-cards/list',
    component: StaffCardListComponent,
    canActivate: [AuthenticationGuard],
  },
  {
    path: 'staff-cards/details/:id',
    component: StaffCardDetailsComponent,
    canActivate: [AuthenticationGuard],
  },
  {
    path: 'card/print/:id',
    component: PrintCardComponent,
    canActivate: [AuthenticationGuard],
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CardManagementRoutingModule {}
