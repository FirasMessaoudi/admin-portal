import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { DashboardService } from '@core/services';
import { GeneralDashboardVo } from '@model/dashboard-general-numbers-vo.model';
import { CountVo } from '@model/count-vo.model';
import { Lookup } from '@model/lookup.model';
import { LookupService } from '@core/utilities/lookup.service';
import { ChartsConfig } from '@pages/dashboard/charts.config';
import { DashboardVo } from '@shared/model';
import { I18nService } from '@dcc-commons-ng/services';
import { ActivatedRoute } from '@angular/router';
import { DashboardComponent } from '@pages/dashboard/slide-show/dashboard.component';
import { LangChangeEvent, TranslateService } from '@ngx-translate/core';
import { LocalizedCountVo } from '@model/localized-count-vo.model';
import * as moment_ from 'moment-hijri';
const momentHijri = moment_;

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

  companyData: LocalizedCountVo[] = [];
  campData: LocalizedCountVo[] = [];

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
    private translate: TranslateService
  ) {}

  ngOnInit() {
    //this.seasonYear = this.route.snapshot.paramMap.get('seasonYear');
    this.seasonYear = parseInt(localStorage.getItem('seasonYear'));
    if (isNaN(this.seasonYear)  ){
      this.seasonYear = momentHijri(new Date()).iYear();
      localStorage.setItem('seasonYear', String(this.seasonYear));
    }
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

    this.translate.onLangChange.subscribe((event: LangChangeEvent) => {
      this.companyLabels = this.currentLanguage.startsWith('ar')
        ? this.companyData.map((d) => d.labelAr)
        : this.companyData.map((d) => d.labelEn);
      this.campLabels = this.currentLanguage.startsWith('ar')
        ? this.campData.map((d) => d.labelAr)
        : this.campData.map((d) => d.labelEn);
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
        this.companyData = data;
        this.companyCounts = data.map((d) => d.count);
        this.companyLabels = this.currentLanguage.startsWith('ar')
          ? data.map((d) => d.labelAr)
          : data.map((d) => d.labelEn);
      });
  }

  loadMaxCompanies() {
    this.minCompanies = false;
    this.dashboardService
      .loadCompaniesWithMaxApplicantCountForCurrentSeason(this.seasonYear)
      .subscribe((data) => {
        this.companyData = data;
        this.companyCounts = data.map((d) => d.count);
        this.companyLabels = this.currentLanguage.startsWith('ar')
          ? data.map((d) => d.labelAr)
          : data.map((d) => d.labelEn);
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
        this.campData = data;
        this.campCounts = data.map((d) => d.count);
        this.campLabels = this.currentLanguage.startsWith('ar')
          ? data.map((d) => d.labelAr)
          : data.map((d) => d.labelEn);
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
        this.campData = data;
        this.campCounts = data.map((d) => d.count);
        this.campLabels = this.currentLanguage.startsWith('ar')
          ? data.map((d) => d.labelAr)
          : data.map((d) => d.labelEn);
      });
  }

  onCampSiteChange() {
    this.minCamps ? this.loadMinCamps() : this.loadMaxCamps();
  }

  getPilgrimLabel() {
    return this.i18nService.language.startsWith('ar') ? 'حاج' : 'Pilgrims';
  }

  isFullScreen: boolean;
}
