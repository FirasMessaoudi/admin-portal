import {Routes} from "@angular/router";

export const INCIDENT_MANAGEMENT_ROUTES: Routes = [
  {
    path: '',
    loadChildren: () => import('@pages/incident-management/incident-management.module').then(m => m.IncidentManagementModule)
  }
];
