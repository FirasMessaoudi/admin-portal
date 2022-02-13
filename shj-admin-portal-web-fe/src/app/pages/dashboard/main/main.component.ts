import {Component, OnInit} from '@angular/core';
import {GeneralDashboardVo} from '@model/dashboard-general-numbers-vo.model';
import {Subscription, timer} from 'rxjs';
import {DashboardService} from '@core/services';
import {DashboardIncidentNumbersVo} from "@model/dashboardIncidentNumbersVo.model";
import {Lookup} from "@model/lookup.model";
import {Label, PluginServiceGlobalRegistrationAndOptions} from "ng2-charts";
import {ChartOptions, ChartType} from "chart.js";
import {ChartsConfig} from "@pages/dashboard/charts.config";
import {LookupService} from "@core/utilities/lookup.service";
import {DatePipe} from "@angular/common";
import {DateFormatterService} from "@shared/modules/hijri-gregorian-datepicker/date-formatter.service";
import {I18nService} from "@dcc-commons-ng/services";

import * as moment_ from 'moment-hijri';

import {LangChangeEvent, TranslateService} from "@ngx-translate/core";

const momentHijri = moment_;

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss'],
})
export class MainComponent implements OnInit {
  //TODO Dummy Data
  dashboardCameras = {
    totalCount: 782,
    activeCameras: 609,
    inactiveCameras: 176,
  };

  currentSeasonData: GeneralDashboardVo;
  previousSeasonData: GeneralDashboardVo;
  currentSeasonPercentage: number;
  previousSeasonPercentage: number;

  private currentSeasonSubscription: Subscription;
  private previousSeasonSubscription: Subscription;

  private incidentSubscription: Subscription;
  incidents: DashboardIncidentNumbersVo;
  incidentStatusList: Lookup[];
  public incidentDoughnutChartLabels: Label[];
  public incidentDoughnutChartData: Array<any>;
  public incidentDoughnutChartType: ChartType = 'doughnut';
  chartsConfig: ChartsConfig = new ChartsConfig();
  mostIncidentDate: any;
  mostIncidentsArea: string;
  public incidentDoughnutChartOptions: ChartOptions = {
    responsive: true,
    cutoutPercentage: 70,
    rotation: Math.PI,
    circumference: Math.PI,
  };
  public incidentDoughnutChartPlugins: PluginServiceGlobalRegistrationAndOptions[];

  private refreshSubscription: Subscription
  seasonYear: number;

  constructor(private dashboardService: DashboardService,
              private lookupService: LookupService,
              private dateFormatterService: DateFormatterService,
              private translate: TranslateService,
              private i18nService: I18nService) {
  }

  ngOnInit() {
    this.loadLookups();

    //Get current hijri year
    this.seasonYear = momentHijri(new Date()).iYear();

    this.lookupService.loadDashboardRefreshInterval().subscribe(timeInterval => {
      this.refreshSubscription = timer(0, timeInterval)
        .subscribe(() => {
          this.loadDashboardData();
        });
    });

    //Update chart labels on language change
    this.translate.onLangChange.subscribe((event: LangChangeEvent) => {
      this.incidentDoughnutChartLabels = [
        this.lookupService.localizedLabel(this.incidentStatusList, 'RESOLVED'),
        this.lookupService.localizedLabel(this.incidentStatusList, 'UNDER_PROCESSING')
      ];
      this.setIncidentCenterTitle(this.translate.instant('dashboard.main.total_incidents'), this.incidents.totalNumberOfRegisteredIncidents);
    });

  }

  ngOnDestroy() {
    if (this.currentSeasonSubscription) {
      this.currentSeasonSubscription.unsubscribe();
    }
    if (this.previousSeasonSubscription) {
      this.previousSeasonSubscription.unsubscribe();
    }
    if (this.refreshSubscription) {
      this.refreshSubscription.unsubscribe();
    }
  }

  setIncidentCenterTitle(title: string, countText: number) {
    this.incidentDoughnutChartPlugins = [{
      beforeDraw(chart) {
        var data = chart.data.datasets[0].data;
        var width = chart.width,
          height = (chart.chartArea.top + chart.chartArea.bottom),
          ctx = chart.ctx;
        ctx.restore();
        var fontSize = (height / 15).toFixed(2);
        ctx.font = fontSize + "px Arial";
        ctx.textBaseline = "middle";
        var text = countText + "",
          textX = Math.round((width - ctx.measureText(text).width) / 2),
          textY = height / 2;
        var textZ = height / 2.5;
        ctx.fillText(text, textX, textY);
        ctx.textBaseline = "middle";
        var textLabel = title,
          textLabelX = Math.round((width - ctx.measureText(textLabel).width) / 2),
          textLabelY = height / 1.5;
        var textLabelZ = height / 1.5;
        ctx.fillText(textLabel, textLabelX, textLabelY);
        ctx.save();
      }
    }];
  }

  loadLookups() {
    this.dashboardService.findIncidentStatus().subscribe(
      data => this.incidentStatusList = data
    )
  }

  formatHijriDate(date: Date): string {
    const datePipe = new DatePipe('en-US');
    let hijriDate = this.dateFormatterService.toDate(
      this.dateFormatterService.toHijri(
        this.dateFormatterService.fromDate(date)
      )
    );
    return this.currentLanguage.startsWith('ar')
      ? datePipe.transform(hijriDate, 'yyyy/MM/dd')
      : datePipe.transform(hijriDate, 'dd/MM/yyyy');
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  loadDashboardData() {
    this.currentSeasonSubscription = this.dashboardService
      .loadGeneralNumbersForCurrentSeason()
      .subscribe((data) => {
        this.currentSeasonData = data;
        this.currentSeasonPercentage =
          (100 * data.totalNumberOfExternalApplicants) /
          data.totalNumberOfApplicants;
        this.currentSeasonData.totalNumberOfApplicants =
          this.currentSeasonData.totalNumberOfExternalApplicants +
          this.currentSeasonData.totalNumberOfInternalApplicants;
      });

    this.previousSeasonSubscription = this.dashboardService
      .loadGeneralNumbersForPreviousSeason()
      .subscribe((data) => {
        this.previousSeasonData = data;
        this.previousSeasonPercentage =
          (100 * data.totalNumberOfExternalApplicants) /
          data.totalNumberOfApplicants;
        this.previousSeasonData.totalNumberOfApplicants =
          this.previousSeasonData.totalNumberOfExternalApplicants +
          this.previousSeasonData.totalNumberOfInternalApplicants;
      });

    this.incidentSubscription = this.dashboardService.loadIncidents().subscribe((data) => {
      this.incidents = data;

      this.incidentDoughnutChartLabels = [
        this.lookupService.localizedLabel(this.incidentStatusList, 'RESOLVED'),
        this.lookupService.localizedLabel(this.incidentStatusList, 'UNDER_PROCESSING')
      ];

      this.incidentDoughnutChartData = [{
        data: [this.incidents.totalNumberOfResolvedIncidents, this.incidents.totalNumberOfUnResolvedIncidents],
        steppedLine: 'after'
      }];

      this.setIncidentCenterTitle(this.translate.instant('dashboard.main.total_incidents'), this.incidents.totalNumberOfRegisteredIncidents);
      this.mostIncidentDate = this.formatHijriDate(this.incidents.mostIncidentDate);
      this.mostIncidentsArea = this.incidents.mostIncidentsArea;
    });
  }
}
