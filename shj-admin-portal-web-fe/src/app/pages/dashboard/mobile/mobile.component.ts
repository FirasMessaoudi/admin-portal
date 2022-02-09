import {Component, OnInit} from '@angular/core';
import {EAuthority} from "@shared/model";
import {AuthenticationService, DashboardService} from "@core/services";
import {DashboardMobileNumbersVo} from "@model/dashboard-mobile-numbers-vo.model";
import {ChartsConfig} from "@pages/dashboard/charts.config";
import {ChartDataSets} from "chart.js";

@Component({
  selector: 'app-mobile',
  templateUrl: './mobile.component.html',
  styleUrls: ['./mobile.component.scss']
})
export class MobileComponent implements OnInit {
  model = 1;
  mobileAppDownloadsData: DashboardMobileNumbersVo;

  chartsConfig: ChartsConfig = new ChartsConfig();
  weekDays: Array<any>;
  datasets: ChartDataSets[];


  public appdownloads: number = 1103402;

  constructor(private authenticationService: AuthenticationService, private dashboardService: DashboardService) {
  }

  ngOnInit() {
    this.dashboardService.loadMobileAppDownloadsNumbers().subscribe(data => this.mobileAppDownloadsData = data);
    this.weekDays = ['اﻷحد', 'اﻷثنين', 'الثلاثاء', 'اﻷربعاء', 'الخميس', 'الجمعة', 'السبت'];
    this.datasets = [
      {
        //TODO Dummy Data
        data: [20, 21, 18, 25, 25, 28, 40],
        fill: false,
        borderColor: '#E5CA81',
        // @ts-ignore
        tension: 0
      },
      {
        data: [14, 12, 24, 18, 13, 20, 31],
        fill: false,
        borderColor: '#D5D5DD',
        // @ts-ignore
        tension: 0
      }];
    this.chartsConfig.lineChartOptions.legend = false;
  }

  get canSeeMobileTrackingDashboard(): boolean {
    return this.authenticationService.hasAuthority(EAuthority.MOBILE_TRACKING_DASHBOARD);
  }
}
