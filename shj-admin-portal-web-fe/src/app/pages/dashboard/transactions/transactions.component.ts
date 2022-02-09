import {Component, OnInit} from '@angular/core';
import {EAuthority} from "@shared/model";
import {AuthenticationService} from "@core/services";

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.scss']
})
export class TransactionsComponent implements OnInit {

  constructor(private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
  }

  get canSeeStatisticalDashboard(): boolean {
    return this.authenticationService.hasAuthority(EAuthority.STATISTICAL_DASHBOARD);
  }
}
