import {Component, OnInit} from '@angular/core';
import {EAuthority} from "@shared/model";
import {AuthenticationService} from "@core/services";
import {DashboardComponent} from "@pages/dashboard/slide-show/dashboard.component";

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.scss']
})
export class TransactionsComponent implements OnInit, DashboardComponent{

  constructor(private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
  }

  get canSeeStatisticalDashboard(): boolean {
    return this.authenticationService.hasAuthority(EAuthority.STATISTICAL_DASHBOARD);
  }

  isFullScreen: boolean;
}
