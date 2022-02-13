import { Component, OnInit } from '@angular/core';
import { EAuthority } from '@shared/model';
import { AuthenticationService, DashboardService } from '@core/services';
import { DashboardMobileNumbersVo } from '@model/dashboard-mobile-numbers-vo.model';
import { ChartsConfig } from '@pages/dashboard/charts.config';
import { ChartDataSets } from 'chart.js';

import * as momentjs from 'moment';
import { I18nService } from '@dcc-commons-ng/services';
import { LangChangeEvent, TranslateService } from '@ngx-translate/core';
import { ActivatedRoute } from '@angular/router';

const moment = momentjs;
const FONTS: string = '"Elm-font", sans-serif';

@Component({
  selector: 'app-mobile',
  templateUrl: './mobile.component.html',
  styleUrls: ['./mobile.component.scss'],
})
export class MobileComponent implements OnInit {
  model = 1;
  mobileAppDownloadsData: DashboardMobileNumbersVo;

  chartsConfig: ChartsConfig = new ChartsConfig();
  weekDays: Array<any> = [];
  datasets: ChartDataSets[];

  minCompanies: boolean;
  companyLabels: Array<any>;
  companyCounts: Array<any>;
  seasonYear: any;

  constructor(
    private authenticationService: AuthenticationService,
    private dashboardService: DashboardService,
    private i18nService: I18nService,
    private translate: TranslateService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.seasonYear = this.route.snapshot.paramMap.get('seasonYear');
    this.chartsConfig.lineChartOptions.legend = false;
    this.dashboardService
      .loadMobileAppDownloadsNumbers(this.seasonYear)
      .subscribe((data) => (this.mobileAppDownloadsData = data));
    this.getWeekDays();

    this.datasets = [
      {
        //TODO Dummy Data
        data: [20, 21, 18, 25, 25, 28, 40],
        fill: false,
        borderColor: '#E5CA81',
        // @ts-ignore
        tension: 0,
      },
      {
        data: [14, 12, 24, 18, 13, 20, 31],
        fill: false,
        borderColor: '#D5D5DD',
        // @ts-ignore
        tension: 0,
      },
    ];

    this.chartsConfig.barChartOptions = {
      ...this.chartsConfig.barChartOptions,
      legend: false,
      scales: {
        ...this.chartsConfig.barChartOptions.scales,
        xAxes: [
          {
            gridLines: {
              color: 'rgba(0, 0, 0, 0)',
            },
          },
        ],
        yAxes: [
          {
            gridLines: {
              borderDash: [8, 6],
              color: '#F3F5F2',
            },
            ticks: {
              fontFamily: FONTS,
              beginAtZero: true,
              callback: function (value) {
                if (value % 1 === 0) {
                  return value;
                }
              },
            },
          },
        ],
      },
    };

    this.translate.onLangChange.subscribe((event: LangChangeEvent) => {
      this.getWeekDays();
    });

    this.loadMaxCompanies();
  }

  loadMinCompanies() {
    this.dashboardService
      .loadCompaniesWithMinApplicantsRegisteredCount(this.seasonYear)
      .subscribe((data) => {
        this.companyCounts = data.map((d) => d.count);
        this.companyLabels = data.map((d) => d.label);
      });
  }

  loadMaxCompanies() {
    this.dashboardService
      .loadCompaniesWithMaxApplicantsRegisteredCount(this.seasonYear)
      .subscribe((data) => {
        this.companyCounts = data.map((d) => d.count);
        this.companyLabels = data.map((d) => d.label);
      });
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  getWeekDays() {
    this.weekDays = [];
    let day = moment();
    for (let i = 1; i < 8; i++) {
      this.weekDays.unshift(
        day
          .locale(this.currentLanguage.toLowerCase().substr(0, 2))
          .format('dddd')
      );
      day = day.clone().subtract(1, 'd');
    }
  }

  get canSeeMobileTrackingDashboard(): boolean {
    return this.authenticationService.hasAuthority(
      EAuthority.MOBILE_TRACKING_DASHBOARD
    );
  }
}
