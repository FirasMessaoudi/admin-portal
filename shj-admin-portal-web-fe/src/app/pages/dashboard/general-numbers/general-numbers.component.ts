import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { DashboardService } from '@core/services';
import { GeneralDashboardVo } from '@model/dashboard-general-numbers-vo.model';
import { CountVo } from '@app/_shared/model/countVo.model';
import { Lookup } from '@model/lookup.model';
import { LookupService } from '@core/utilities/lookup.service';
import { ChartsConfig } from '@pages/dashboard/charts.config';
import { DashboardVo } from '@shared/model';

const FONTS: string = '"Elm-font", sans-serif';

@Component({
  selector: 'app-general-numbers',
  templateUrl: './general-numbers.component.html',
  styleUrls: ['./general-numbers.component.scss'],
})
export class GeneralNumbersComponent implements OnInit {
  currentSeasonData: GeneralDashboardVo;
  previousSeasonData: GeneralDashboardVo;
  applicantsPerNationalities: CountVo[];
  currentSeasonPercentage: number;
  previousSeasonPercentage: number;
  totalCountsNationalities: number = 0;
  totalCountAges: number = 0;
  countryList: Lookup[];
  countVoList: CountVo[];
  minCompanies: boolean;
  minCamps: boolean;
  private currentSeasonSubscription: Subscription;
  private previousSeasonSubscription: Subscription;
  private applicantsPerNationalitiesSubscription: Subscription;

  companyLabels: Array<any>;
  companyCounts: Array<any>;
  campCounts: Array<any>;
  campLabels: Array<any>;

  data: DashboardVo;

  chartsConfig: ChartsConfig = new ChartsConfig();

  private loadSubscription: Subscription;

  constructor(
    private dashboardService: DashboardService,
    private lookupService: LookupService
  ) {}

  ngOnInit() {
    this.loadLookups();

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

    this.dashboardService
      .loadApplicantsCountByAgeCurrentSeason()
      .subscribe((data) => {
        this.countVoList = data;
        this.countVoList.forEach((element) => {
          this.totalCountAges += element.count;
        });
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

    this.applicantsPerNationalitiesSubscription = this.dashboardService
      .loadGeneralNumbersForApplicantPerNationalities()
      .subscribe((data) => {
        this.applicantsPerNationalities = data;
        this.applicantsPerNationalities.forEach((element) => {
          this.totalCountsNationalities += element.count;
          element.label = this.lookupService.localizedLabel(
            this.countryList,
            element.label
          );
        });
      });

    this.loadMaxCompanies();
    this.loadMaxCamps();
  }

  loadLookups() {
    this.dashboardService
      .findNationalities()
      .subscribe((data) => (this.countryList = data));
  }

  ngOnDestroy() {
    if (this.currentSeasonSubscription) {
      this.currentSeasonSubscription.unsubscribe();
    }
    if (this.previousSeasonSubscription) {
      this.previousSeasonSubscription.unsubscribe();
    }
    if (this.applicantsPerNationalitiesSubscription) {
      this.applicantsPerNationalitiesSubscription.unsubscribe();
    }
    if (this.loadSubscription) {
      this.loadSubscription.unsubscribe();
    }
  }

  loadMinCompanies() {
    this.minCompanies = true;
    this.dashboardService
      .loadCompaniesWithMinApplicantCountForCurrentSeason()
      .subscribe((data) => (this.companyCounts = data.map((i) => i.count)));
    this.dashboardService
      .loadCompaniesWithMinApplicantCountForCurrentSeason()
      .subscribe((data) => (this.companyLabels = data.map((d) => d.label)));
  }

  loadMaxCompanies() {
    this.minCompanies = false;
    this.dashboardService
      .loadCompaniesWithMaxApplicantCountForCurrentSeason()
      .subscribe((data) => (this.companyCounts = data.map((i) => i.count)));
    this.dashboardService
      .loadCompaniesWithMaxApplicantCountForCurrentSeason()
      .subscribe((data) => (this.companyLabels = data.map((d) => d.label)));
  }

  loadMinCamps() {
    this.minCamps = true;
    this.dashboardService
      .loadCampsWithMinApplicantCountForCurrentSeason()
      .subscribe((data) => (this.campCounts = data.map((i) => i.count)));
    this.dashboardService
      .loadCampsWithMinApplicantCountForCurrentSeason()
      .subscribe((data) => (this.campLabels = data.map((d) => d.label)));
  }

  loadMaxCamps() {
    this.minCamps = false;
    this.dashboardService
      .loadCampsWithMaxApplicantCountForCurrentSeason()
      .subscribe((data) => (this.campCounts = data.map((i) => i.count)));
    this.dashboardService
      .loadCampsWithMaxApplicantCountForCurrentSeason()
      .subscribe((data) => (this.campLabels = data.map((d) => d.label)));
  }
}
