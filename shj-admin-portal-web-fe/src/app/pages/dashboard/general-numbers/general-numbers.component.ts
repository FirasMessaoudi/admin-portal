import {Component, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {DashboardService} from '@core/services';
import {GeneralDashboardVo} from '@model/dashboard-general-numbers-vo.model';
import {CountVo} from '@app/_shared/model/countVo.model';
import {Lookup} from '@model/lookup.model';
import {LookupService} from '@core/utilities/lookup.service';
import {ChartsConfig} from '@pages/dashboard/charts.config';
import {DashboardVo} from '@shared/model';
import {I18nService} from '@dcc-commons-ng/services';
import {ActivatedRoute} from '@angular/router';
import {DashboardComponent} from "@pages/dashboard/slide-show/dashboard.component";

@Component({
  selector: 'app-general-numbers',
  templateUrl: './general-numbers.component.html',
  styleUrls: ['./general-numbers.component.scss'],
})
export class GeneralNumbersComponent implements OnInit, DashboardComponent {
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
  campSites: Lookup[] = [];
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
  seasonYear: any;
  selectedCampSiteCode: any = 'MENA';

  constructor(
    private dashboardService: DashboardService,
    private i18nService: I18nService,
    private route: ActivatedRoute,
    private lookupsService: LookupService,
  ) {}

  ngOnInit() {
    this.seasonYear = this.route.snapshot.paramMap.get('seasonYear');
    this.loadLookups();
    this.chartsConfig.barChartOptions.legend = false;

    this.currentSeasonSubscription = this.dashboardService
      .loadGeneralNumbersForHijriSeason(this.seasonYear)
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
      .loadApplicantsCountByAgeHijriSeason(this.seasonYear)
      .subscribe((data) => {
        this.countVoList = data;
        this.countVoList.forEach((element) => {
          this.totalCountAges += element.count;
        });
      });

    this.previousSeasonSubscription = this.dashboardService
      .loadGeneralNumbersForPreviousSeason(this.seasonYear - 1)
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
      .loadGeneralNumbersForApplicantPerNationalities(this.seasonYear)
      .subscribe((data) => {
        this.applicantsPerNationalities = data;
        this.applicantsPerNationalities.forEach((element) => {
          this.totalCountsNationalities += element.count;
          element.label = this.lookupsService.localizedLabel(
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

    this.dashboardService
      .findCampSites()
      .subscribe((data) => (this.campSites = data));
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  lookupService(): LookupService {
    return this.lookupsService;
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
      .loadCompaniesWithMinApplicantCountForHijriSeason(this.seasonYear)
      .subscribe((data) => {
        this.companyCounts = data.map((d) => d.count);
        this.companyLabels = data.map((d) => d.label);
      });
  }

  loadMaxCompanies() {
    this.minCompanies = false;
    this.dashboardService
      .loadCompaniesWithMaxApplicantCountForCurrentSeason(this.seasonYear)
      .subscribe((data) => {
        this.companyCounts = data.map((d) => d.count);
        this.companyLabels = data.map((d) => d.label);
      });
  }

  loadMinCamps() {
    this.minCamps = true;
    this.dashboardService
      .loadCampsWithMinApplicantCountForHijriSeason(
        this.seasonYear,
        this.selectedCampSiteCode
      )
      .subscribe((data) => {
        this.campCounts = data.map((d) => d.count);
        this.campLabels = data.map((d) => d.label);
      });
  }

  loadMaxCamps() {
    this.minCamps = false;
    this.dashboardService
      .loadCampsWithMaxApplicantCountForHijriSeason(
        this.seasonYear,
        this.selectedCampSiteCode
      )
      .subscribe((data) => {
        this.campCounts = data.map((d) => d.count);
        this.campLabels = data.map((d) => d.label);
      });
  }

  onCampSiteChange() {
    this.minCamps ? this.loadMinCamps() : this.loadMaxCamps();
  }

  getPilgrimLabel() {
    return this.i18nService.language.startsWith('ar') ? 'حاج' : 'Pilgrims'
  }

  isFullScreen: boolean;
}
