import {Component, OnInit} from '@angular/core';
import {DashboardService} from "@core/services";
import {DashboardMobileNumbersVo} from "@model/dashboard-mobile-numbers-vo.model";

@Component({
  selector: 'app-mobile',
  templateUrl: './mobile.component.html',
  styleUrls: ['./mobile.component.scss']
})
export class MobileComponent implements OnInit {
  model = 1;
  mobileAppDownloadsData: DashboardMobileNumbersVo;

  constructor(private dashboardService: DashboardService) {
  }

  ngOnInit() {
    this.dashboardService.loadMobileAppDownloadsNumbers().subscribe(data => this.mobileAppDownloadsData = data);
  }

}
