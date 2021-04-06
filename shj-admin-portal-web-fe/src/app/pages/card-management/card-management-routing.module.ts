import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthenticationGuard} from "@core/guards/authentication.guard";
import {CardListComponent} from "@pages/card-management/card-list/card-list.component";
import {CardDetailsComponent} from "@pages/card-management/card-details/card-details.component";


const routes: Routes = [
  {path: 'cards/list', component: CardListComponent, canActivate: [AuthenticationGuard]},
  {path: 'cards/details/:id', component: CardDetailsComponent, canActivate: [AuthenticationGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CardManagementRoutingModule { }
