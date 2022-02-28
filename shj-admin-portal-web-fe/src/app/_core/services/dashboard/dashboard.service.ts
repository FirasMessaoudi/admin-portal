import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DashboardVo } from '@model/dashboard-vo.model';
import { BehaviorSubject, Observable } from 'rxjs';
import { GeneralDashboardVo } from '@model/dashboard-general-numbers-vo.model';
import { CountVo } from '@model/count-vo.model';
import { Lookup } from '@model/lookup.model';
import { DashboardIncidentNumbersVo } from '@model/dashboard-incident-numbers-vo.model';
import { Position } from '@app/_shared/model/marker.model';
import { DashboardMobileNumbersVo } from '@model/dashboard-mobile-numbers-vo.model';
import { ApplicantMobileTracking } from '@model/applicant-mobile-tracking.model';
import { dashboardItem } from '@model/dashboard-item';
import { LocalizedCountVo } from '@model/localized-count-vo.model';
import { DashboardCameraNumbers } from '@model/dashboard-camera-numbers';
import { AreaLayerLookup } from '@app/_shared/model/area-layer-lookup.model';

@Injectable({
  providedIn: 'root',
})
export class DashboardService {
  items: dashboardItem[] = [];
  slideShowInterval: number = 5;
  private intervalSubject = new BehaviorSubject<number>(5);

  constructor(private http: HttpClient) {
    this.items = [
      new dashboardItem('MainComponent', 'dashboard.general.hajj', true),
      new dashboardItem(
        'GeneralNumbersComponent',
        'dashboard.general-numbers.title',
        true
      ),
      new dashboardItem(
        'IncidentsComponent',
        'dashboard.incidents.title',
        true
      ),
      new dashboardItem('CamerasComponent', 'dashboard.cameras.title', true),
      new dashboardItem('MobileComponent', 'dashboard.mobile.title', true),
    ];
  }

  /**
   * Load dashboard data for logged in user agency
   */
  loadData(): Observable<DashboardVo> {
    return this.http.get<DashboardVo>('/core/api/dashboard');
  }

  /**
   * Loads dashboard statistics for a specific period
   *
   * @param periodType     the period type to load
   */
  loadPeriodData(periodType: string): Observable<DashboardVo> {
    return this.http.get<DashboardVo>(
      '/core/api/dashboard/period/' + periodType
    );
  }

  /**
   * Load all ritual seasons
   */
  findRitualSeasonYears(): Observable<any[]> {
    return this.http.get<any>('/core/api/lookup/ritual-seasons-years/list');
  }

  /**
   * Load dashboard general numbers for current season
   */
  loadGeneralNumbersForHijriSeason(
    seasonYear: number
  ): Observable<GeneralDashboardVo> {
    return this.http.get<GeneralDashboardVo>(
      '/core/api/dashboard/general-numbers/current-season/' + seasonYear
    );
  }

  /**
   * Load dashboard general numbers for previous season
   */
  loadGeneralNumbersForPreviousSeason(
    seasonYear: number
  ): Observable<GeneralDashboardVo> {
    return this.http.get<GeneralDashboardVo>(
      '/core/api/dashboard/general-numbers/previous-season/' + seasonYear
    );
  }

  /**
   * Load dashboard general numbers for number of applicant per nationalities
   */
  loadGeneralNumbersForApplicantPerNationalities(
    seasonYear: number
  ): Observable<CountVo[]> {
    return this.http.get<CountVo[]>(
      '/core/api/dashboard/general-numbers/applicant/count-per-nationalities/' +
        seasonYear
    );
  }

  /**
   * Load dashboard applicants numbers for current season
   */
  loadApplicantsCountByAgeHijriSeason(
    seasonYear: number
  ): Observable<CountVo[]> {
    return this.http.get<CountVo[]>(
      '/core/api/dashboard/general-numbers/applicant/count-per-age/' +
        seasonYear
    );
  }

  /**
   * Load dashboard applicants numbers for current season
   */

  loadCamerasNumbers(seasonYear: number): Observable<DashboardCameraNumbers> {
    return this.http.get<DashboardCameraNumbers>(
      '/core/api/dashboard/camera_numbers/' + seasonYear
    );
  }

  findNationalities(): Observable<Lookup[]> {
    return this.http.get<any>('/core/api/lookup/country/list');
  }

  /**
   * Load companies with max applicant count for current season
   */
  loadCompaniesWithMaxApplicantCountForCurrentSeason(
    seasonYear: number
  ): Observable<LocalizedCountVo[]> {
    return this.http.get<LocalizedCountVo[]>(
      '/core/api/dashboard/general-numbers/max-companies/' + seasonYear
    );
  }

  /**
   * Load companies with min applicant count for current season
   */
  loadCompaniesWithMinApplicantCountForHijriSeason(
    seasonYear: number
  ): Observable<LocalizedCountVo[]> {
    return this.http.get<LocalizedCountVo[]>(
      '/core/api/dashboard/general-numbers/min-companies/' + seasonYear
    );
  }

  /**
   * Load camps with max applicant count for current season
   */
  loadCampsWithMaxApplicantCountForHijriSeason(
    seasonYear: number,
    site: string
  ): Observable<LocalizedCountVo[]> {
    let params = new HttpParams().set('site', site);
    return this.http.get<LocalizedCountVo[]>(
      '/core/api/dashboard/general-numbers/max-camps/' + seasonYear,
      { params: params }
    );
  }

  /**
   * Load camps with min applicant count for current season
   */
  loadCampsWithMinApplicantCountForHijriSeason(
    seasonYear: number,
    site: string
  ): Observable<LocalizedCountVo[]> {
    let params = new HttpParams().set('site', site);
    return this.http.get<LocalizedCountVo[]>(
      '/core/api/dashboard/general-numbers/min-camps/' + seasonYear,
      { params: params }
    );
  }

  loadIncidents(seasonYear: number): Observable<DashboardIncidentNumbersVo> {
    return this.http.get<DashboardIncidentNumbersVo>(
      '/core/api/dashboard/incident-numbers/' + seasonYear
    );
  }

  findIncidentTypes(): Observable<Lookup[]> {
    return this.http.get<any>('/core/api/lookup/incident-type/list');
  }

  findIncidentStatus(): Observable<Lookup[]> {
    return this.http.get<any>('/core/api/lookup/incident-status/list');
  }

  /**
   * Load incidents location for current season
   */
  loadIncidentsLocationsForHijriSeason(
    seasonYear: number
  ): Observable<Position[]> {
    return this.http.get<Position[]>(
      '/core/api/dashboard/incidents-locations/' + seasonYear
    );
  }

  /**
   * Load companies with max incident count
   */
  loadCompaniesWithMaxIncidentCount(
    seasonYear: number
  ): Observable<LocalizedCountVo[]> {
    return this.http.get<LocalizedCountVo[]>(
      '/core/api/dashboard/incident-numbers/max-companies/' + seasonYear
    );
  }

  /**
   * Load companies with min incident count
   */
  loadCompaniesWithMinIncidentCount(
    seasonYear: number
  ): Observable<LocalizedCountVo[]> {
    return this.http.get<LocalizedCountVo[]>(
      '/core/api/dashboard/incident-numbers/min-companies/' + seasonYear
    );
  }

  /**
   * Load dashboard mobile app downloads numbers
   */
  loadMobileAppDownloadsNumbers(
    seasonYear: number
  ): Observable<DashboardMobileNumbersVo> {
    return this.http.get<DashboardMobileNumbersVo>(
      '/core/api/dashboard/mobile/app-downloads/' + seasonYear
    );
  }

  /**
   * Load companies with max applicant registered count
   */
  loadCompaniesWithMaxApplicantsRegisteredCount(
    seasonYear: number
  ): Observable<CountVo[]> {
    return this.http.get<CountVo[]>(
      '/core/api/dashboard/applicant-numbers/max-companies/' + seasonYear
    );
  }

  /**
   * Load companies with min applicant registered count
   */
  loadCompaniesWithMinApplicantsRegisteredCount(
    seasonYear: number
  ): Observable<CountVo[]> {
    return this.http.get<CountVo[]>(
      '/core/api/dashboard/applicant-numbers/min-companies/' + seasonYear
    );
  }

  /**
   * Load companies with min applicant registered count
   */
  getMobileLoggedInUsers(seasonYear: number): Observable<number[]> {
    return this.http.get<number[]>(
      '/core/api/dashboard/mobile/logged-in-users/' + seasonYear
    );
  }

  /**
   * Load active applicants with locations by season
   */
  findActiveApplicantWithLocationBySeason(
    seasonYear: number
  ): Observable<ApplicantMobileTracking[]> {
    return this.http.get<ApplicantMobileTracking[]>(
      '/core/api/dashboard/mobile/active-applicants-locations/' + seasonYear
    );
  }

  findCampSites(): Observable<Lookup[]> {
    return this.http.get<any>('/core/api/lookup/camp-site/list');
  }

  /**
   * Load mobile app users by age range and hijri season
   */
  loadMobileAppUsersByAgeRangeAndSeason(
    seasonYear: number
  ): Observable<CountVo[]> {
    return this.http.get<CountVo[]>(
      '/core/api/dashboard/mobile/usage-by-age-range/' + seasonYear
    );
  }

  findHousingSites(): Observable<Lookup[]> {
    return this.http.get<any>('/core/api/lookup/housing-site/list');
  }

  getDashboardItems(): dashboardItem[] {
    return this.items;
  }

  findAreaLayers(): Observable<AreaLayerLookup[]> {
    return this.http.get<any>('/core/api/lookup/area_layers/list');
  }

  getSlideShowInterval(): BehaviorSubject<number> {
    return this.intervalSubject;
  }
}
