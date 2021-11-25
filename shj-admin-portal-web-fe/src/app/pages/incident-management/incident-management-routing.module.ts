import {RouterModule, Routes} from "@angular/router";
import {AuthenticationGuard} from "@core/guards/authentication.guard";
import {NgModule} from "@angular/core";
import {IncidentListComponent} from "@pages/incident-management/incident-list/incident-list.component";
import {IncidentDetailsComponent} from "@pages/incident-management/incident-details/incident-details.component";

const routes: Routes = [
  {path: 'incidents/list', component: IncidentListComponent, canActivate: [AuthenticationGuard]},
  {path: 'incidents/details/:id', component: IncidentDetailsComponent, canActivate: [AuthenticationGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class IncidentManagementRoutingModule {
}
