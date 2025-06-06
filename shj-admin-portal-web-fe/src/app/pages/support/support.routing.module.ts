import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SupportComponent} from './support.component';

const routes: Routes = [
  {path: 'support', component: SupportComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: []
})
export class SupportRoutingModule {
}
