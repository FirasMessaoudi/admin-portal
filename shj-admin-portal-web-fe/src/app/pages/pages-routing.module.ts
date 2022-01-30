import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MainComponent} from "@pages/dashboard/main/main.component";

const routes: Routes = [
  { path: '/', redirectTo: '/home', pathMatch: 'full' },
  {path: '', component: MainComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PagesRoutingModule {
}
