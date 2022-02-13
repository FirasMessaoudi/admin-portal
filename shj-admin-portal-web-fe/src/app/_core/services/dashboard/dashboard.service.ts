import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DashboardVo } from '@model/dashboard-vo.model';
import { Observable } from 'rxjs';
import { GeneralDashboardVo } from '@model/dashboard-general-numbers-vo.model';
import { CountVo } from '@app/_shared/model/countVo.model';
import { Lookup } from '@model/lookup.model';
import {DashboardIncidentNumbersVo} from "@model/dashboardIncidentNumbersVo.model";
import { Position } from '@app/_shared/model/marker.model';
import {DashboardMobileNumbersVo} from "@model/dashboard-mobile-numbers-vo.model";

@Injectable({
  providedIn: 'root',
})
export class DashboardService {
  constructor(private http: HttpClient) {}

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
   * Load dashboard general numbers for current season
   */
  loadGeneralNumbersForCurrentSeason(): Observable<GeneralDashboardVo> {
    return this.http.get<GeneralDashboardVo>(
      '/core/api/dashboard/general-numbers/current-season'
    );
  }

  /**
   * Load dashboard general numbers for previous season
   */
  loadGeneralNumbersForPreviousSeason(): Observable<GeneralDashboardVo> {
    return this.http.get<GeneralDashboardVo>(
      '/core/api/dashboard/general-numbers/previous-season'
    );
  }

  /**
   * Load dashboard general numbers for number of applicant per nationalities
   */
  loadGeneralNumbersForApplicantPerNationalities(): Observable<CountVo[]> {
    return this.http.get<CountVo[]>(
      '/core/api/dashboard/general-numbers/applicant/count-per-nationalities'
    );
  }

  /**
   * Load dashboard applicants numbers for current season
   */
  loadApplicantsCountByAgeCurrentSeason(): Observable<CountVo[]> {
    return this.http.get<CountVo[]>(
      '/core/api/dashboard/general-numbers/applicant/count-per-age'
    );
  }

  findNationalities(): Observable<Lookup[]> {
    return this.http.get<any>('/core/api/lookup/country/list');
  }

  /**
   * Load companies with max applicant count for current season
   */
  loadCompaniesWithMaxApplicantCountForCurrentSeason(): Observable<CountVo[]> {
    return this.http.get<CountVo[]>(
      '/core/api/dashboard/general-numbers/max-companies'
    );
  }

  /**
   * Load companies with min applicant count for current season
   */
  loadCompaniesWithMinApplicantCountForCurrentSeason(): Observable<CountVo[]> {
    return this.http.get<CountVo[]>(
      '/core/api/dashboard/general-numbers/min-companies'
    );
  }

  /**
   * Load camps with max applicant count for current season
   */
  loadCampsWithMaxApplicantCountForCurrentSeason(): Observable<CountVo[]> {
    return this.http.get<CountVo[]>(
      '/core/api/dashboard/general-numbers/max-camps'
    );
  }

  /**
   * Load camps with min applicant count for current season
   */
  loadCampsWithMinApplicantCountForCurrentSeason(): Observable<CountVo[]> {
    return this.http.get<CountVo[]>(
      '/core/api/dashboard/general-numbers/min-camps'
    );
  }

  loadIncidents(): Observable<DashboardIncidentNumbersVo> {
    return this.http.get<DashboardIncidentNumbersVo>(
      '/core/api/dashboard/incident-numbers'
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
     loadIncidentsLocationsForCurrentSeason(): Observable<Position[]> {
      return this.http.get<Position[]>(
        '/core/api/dashboard/incidents-locations'
      );
    }

  /**
   * Load companies with max incident count
   */
  loadCompaniesWithMaxIncidentCount(): Observable<CountVo[]> {
    return this.http.get<CountVo[]>(
      '/core/api/dashboard/incident-numbers/max-companies'
    );
  }

  /**
   * Load companies with min incident count
   */
  loadCompaniesWithMinIncidentCount(): Observable<CountVo[]> {
    return this.http.get<CountVo[]>(
      '/core/api/dashboard/incident-numbers/min-companies'
    );
  }

  /**
   * Load dashboard mobile app downloads numbers
   */
  loadMobileAppDownloadsNumbers(): Observable<DashboardMobileNumbersVo> {
    return this.http.get<DashboardMobileNumbersVo>(
      '/core/api/dashboard/mobile/app-downloads'
    );
  }

  /**
   * Load companies with max applicant registered count
   */
  loadCompaniesWithMaxApplicantsRegisteredCount(): Observable<CountVo[]> {
    return this.http.get<CountVo[]>(
      '/core/api/dashboard/applicant-numbers/max-companies'
    );
  }

  /**
   * Load companies with min applicant registered count
   */
  loadCompaniesWithMinApplicantsRegisteredCount(): Observable<CountVo[]> {
    return this.http.get<CountVo[]>(
      '/core/api/dashboard/applicant-numbers/min-companies'
    );
  }

}
