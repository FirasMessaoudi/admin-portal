import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AppLayoutComponent, LoginLayoutComponent} from '@core/_layout';
import {APP_ROUTES} from '@core/routes/app-routes';
import {LOGIN_ROUTES} from '@core/routes/auth-routes';

import {QuicklinkModule, QuicklinkStrategy} from 'ngx-quicklink';
import {AuthenticationGuard} from "@core/guards/authentication.guard";
import {USER_MANAGEMENT_ROUTES} from '@core/routes/user-management-routes';

import {ROLE_MANAGEMENT_ROUTES} from '@core/routes/role-management-routes';
import {PUBLIC_ROUTES} from "@core/routes/public-routes";
import {CARD_MANAGEMENT_ROUTES} from "@core/routes/card-management-routes";
import {PRINTING_MANAGEMENT_ROUTES} from "@core/routes/printing-management-routes";
import {RULE_MANAGEMENT_ROUTES} from "@core/routes/rule-management-routes";
import {DATA_UPLOAD_MANAGEMENT_ROUTES} from "@core/routes/data-upload-management-routes";

const routes: Routes = [

  {
    path: '',
    component: AppLayoutComponent,
    canActivate: [AuthenticationGuard],
    children: APP_ROUTES
  },
  {
    path: '',
    component: LoginLayoutComponent,
    children: LOGIN_ROUTES
  },
  {
    path: '',
    component: LoginLayoutComponent,
    children: PUBLIC_ROUTES
  },
  {
    path: '',
    component: AppLayoutComponent,
    canActivate: [AuthenticationGuard],
    children: USER_MANAGEMENT_ROUTES
  },
  {
    path: '',
    component: AppLayoutComponent,
    canActivate: [AuthenticationGuard],
    children: ROLE_MANAGEMENT_ROUTES
  },
  {
    path: '',
    component: AppLayoutComponent,
    canActivate: [AuthenticationGuard],
    children: CARD_MANAGEMENT_ROUTES
  },
  {
    path: '',
    component: AppLayoutComponent,
    canActivate: [AuthenticationGuard],
    children: PRINTING_MANAGEMENT_ROUTES
  },
  {
    path: '',
    component: AppLayoutComponent,
    canActivate: [AuthenticationGuard],
    children: DATA_UPLOAD_MANAGEMENT_ROUTES
  },
  {
    path: '',
    component: AppLayoutComponent,
    canActivate: [AuthenticationGuard],
    children: RULE_MANAGEMENT_ROUTES
  },
  {
    path: '',
    component: AppLayoutComponent,
    canActivate: [AuthenticationGuard]
  },
  {
    path: '**',
    redirectTo: '',
  }
];


@NgModule({
  imports: [
    QuicklinkModule,
    RouterModule.forRoot(routes, {preloadingStrategy: QuicklinkStrategy, useHash: true})
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
