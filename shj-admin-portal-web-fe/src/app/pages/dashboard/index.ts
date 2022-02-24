import {MainComponent} from './main/main.component';
import {CamerasComponent} from './cameras/cameras.component';
import {GeneralNumbersComponent} from './general-numbers/general-numbers.component';
import {IncidentsComponent} from './incidents/incidents.component';
import {MobileComponent} from './mobile/mobile.component';
import {RatingComponent} from './rating/rating.component';
import {TransactionsComponent} from './transactions/transactions.component';
import {SlideShowComponent} from "@pages/dashboard/slide-show/slide-show.component";
import {SlideShowDirective} from "@shared/directives/slide-show.directive";

export const my_dashboard: any[] = [MainComponent, TransactionsComponent, RatingComponent, MobileComponent,
  IncidentsComponent, GeneralNumbersComponent, CamerasComponent, SlideShowComponent, SlideShowDirective
];


export * from './main/main.component';
export * from './cameras/cameras.component';
export * from './general-numbers/general-numbers.component';
export * from './incidents/incidents.component';
export * from './mobile/mobile.component';
export * from './rating/rating.component';
export * from './transactions/transactions.component';
export * from './slide-show/slide-show.component';



