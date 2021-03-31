import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthenticationGuard} from "@core/guards/authentication.guard";
import {CardListComponent} from "@pages/card-management/card-list/card-list.component";


const routes: Routes = [
  {path: 'cards/list', component: CardListComponent, canActivate: [AuthenticationGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CardManagementRoutingModule { }
