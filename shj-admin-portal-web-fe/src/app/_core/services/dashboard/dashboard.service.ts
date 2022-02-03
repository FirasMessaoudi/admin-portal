import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DashboardVo } from '@model/dashboard-vo.model';
import { Observable } from 'rxjs';
import { GeneralDashboardVo } from '@model/dashboard-general-numbers-vo.model';
import { CountVo } from '@app/_shared/model/countVo.model';
import { Lookup } from '@model/lookup.model';
import {DashboardIncidentNumbersVo} from "@model/dashboardIncidentNumbersVo.model";

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

}
