import {Routes} from '@angular/router';

export const APP_ROUTES: Routes = [
  {
    path: '',
    loadChildren: () => import('@pages/pages.module').then(m => m.PagesModule)
  },
  {
    path: '',
    loadChildren: () => import('@pages/my-profile/my-profile.module').then(m => m.MyProfileModule)
  },

  {
    path: '',
    loadChildren: () => import('@pages/dashboard/dashboard.module').then(m => m.DashboardModule)
  }
];
