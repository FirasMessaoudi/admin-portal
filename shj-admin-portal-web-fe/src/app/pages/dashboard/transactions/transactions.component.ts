import {Component, OnInit} from '@angular/core';
import {EAuthority} from "@shared/model";
import {AuthenticationService} from "@core/services";
import {DashboardComponent} from "@pages/dashboard/slide-show/dashboard.component";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.scss']
})
export class TransactionsComponent implements OnInit, DashboardComponent{

  constructor(private authenticationService: AuthenticationService, private translate: TranslateService) {
  }

  ngOnInit() {
  }

  get canSeeStatisticalDashboard(): boolean {
    return this.authenticationService.hasAuthority(EAuthority.STATISTICAL_DASHBOARD);
  }

  isFullScreen: boolean;
}
