import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { ApplicantCard } from '@model/applicant-card.model';
import { catchError } from 'rxjs/internal/operators';
import { Lookup } from '@model/lookup.model';
import { NationalityLookup } from '@model/nationality-lookup.model';
import { ApplicantCardSearchCriteria } from '@model/applicant-card-search-criteria.model';
import { StaffCardSearchCriteria } from '@model/staff-card-search-criteria.model';
import { CompanyLite } from '@model/company-lite.model';
import { CompanyStaffCard } from '@app/_shared/model/staff-card.model';
import { PrintDetails } from '@model/print-details.model';
import { GenerateCardInput } from '@model/generate-card-input.model';
import { GenerateStaffCardInput } from '@model/generate-staff-card-input.model';

@Injectable({
  providedIn: 'root',
})
export class CardService {
  constructor(private http: HttpClient) {}

  searchCardsToPrint(
    uin: any,
    idNumber: number,
    hamlahNumber: any,
    motawefNumber: any,
    passportNumber: any,
    nationality: any,
    excludedCardsIds,
    pageNumber: any
  ): Observable<any> {
    let params = new HttpParams().set('page', pageNumber);
    if (excludedCardsIds.length > 0) {
      params = params.append('excludedCardsIds', excludedCardsIds);
    }
    return this.http.get(
      '/core/api/cards/list/ready-to-print/' +
        (uin ? uin : -1) +
        '/' +
        (idNumber ? idNumber : -1) +
        '/' +
        (hamlahNumber ? hamlahNumber : -1) +
        '/' +
        (motawefNumber ? motawefNumber : -1) +
        '/' +
        (passportNumber ? passportNumber : -1) +
        '/' +
        (nationality ? nationality : -1),
      { params: params }
    );
  }

  list(
    pageNumber: any,
    applicantCardSearchCriteria: ApplicantCardSearchCriteria
  ): Observable<any> {
    let params = new HttpParams().set('page', pageNumber);
    return this.http.post<any>(
      '/core/api/cards/list-applicant-cards',
      applicantCardSearchCriteria,
      {
        params: params,
      }
    );
  }

  searchAllCardsToPrint(
    uin: any,
    idNumber: number,
    hamlahNumber: any,
    motawefNumber: any,
    passportNumber: any,
    nationality: any,
    excludedCardsIds
  ): Observable<any> {
    let params = new HttpParams();
    if (excludedCardsIds.length > 0) {
      params = params.append('excludedCardsIds', excludedCardsIds);
    }
    return this.http.get(
      '/core/api/cards/list/ready-to-print/all/' +
        (uin ? uin : -1) +
        '/' +
        (idNumber ? idNumber : -1) +
        '/' +
        (hamlahNumber ? hamlahNumber : -1) +
        '/' +
        (motawefNumber ? motawefNumber : -1) +
        '/' +
        (passportNumber ? passportNumber : -1) +
        '/' +
        (nationality ? nationality : -1),
      { params: params }
    );
  }

  /**
   * Finds card by its ID from the server.
   *
   * @param cardId the card id
   * @return {Observable<ApplicantCard>} The card identified by cardId.
   */
  find(cardId: number): Observable<ApplicantCard> {
    return this.http.get<any>('/core/api/cards/find/' + cardId).pipe(
      catchError((error: any, caught: Observable<HttpEvent<any>>) => {
        console.error(error);
        return of(null);
      })
    );
  }

  findStaffCard(cardId: number): Observable<CompanyStaffCard> {
    return this.http.get<any>('/core/api/staff-cards/find/' + cardId).pipe(
      catchError((error: any, caught: Observable<HttpEvent<any>>) => {
        console.error(error);
        return of(null);
      })
    );
  }

  findRitualTypes(): Observable<Lookup[]> {
    return this.http.get<any>('/core/api/lookup/ritual-type/list');
  }

  findCardStatuses(): Observable<Lookup[]> {
    return this.http.get<any>('/core/api/lookup/card-status/list');
  }

  findRelativeRelationships(): Observable<Lookup[]> {
    return this.http.get<any>('/core/api/lookup/relative-relationship/list');
  }

  findCountries(): Observable<NationalityLookup[]> {
    return this.http.get<any>('/core/api/lookup/country/list');
  }

  findHealthSpecialNeeds(): Observable<Lookup[]> {
    return this.http.get<any>('/core/api/lookup/health-special-needs/list');
  }

  findMaritalStatuses(): Observable<Lookup[]> {
    return this.http.get<any>('/core/api/lookup/marital-status/list');
  }

  findRitualStepsLabels(): Observable<Lookup[]> {
    return this.http.get<any>('/core/api/lookup/company_ritual_step/list');
  }

  findGroupLeaderTitleLabels(): Observable<Lookup[]> {
    return this.http.get<any>('/core/api/lookup/company-staff-title/list');
  }

  findHousingCategories(): Observable<Lookup[]> {
    return this.http.get<any>('/core/api/lookup/housing-category/list');
  }

  findHousingTypes(): Observable<Lookup[]> {
    return this.http.get<any>('/core/api/lookup/housing-type/list');
  }

  findPackageTypes(): Observable<Lookup[]> {
    return this.http.get<any>('/core/api/lookup/package-type/list');
  }

  findHousingSites(): Observable<Lookup[]> {
    return this.http.get<any>('/core/api/lookup/housing-site/list');
  }

  findTransportationTypes(): Observable<Lookup[]> {
    return this.http.get<any>('/core/api/lookup/transportation-type/list');
  }

  findDigitalIdStatuses(): Observable<Lookup[]> {
    return this.http.get<any>('/core/api/lookup/digital-id-status/list');
  }

  findHealthImmunizations(): Observable<Lookup[]> {
    return this.http.get<any>('/core/api/lookup/health-immunization/list');
  }

  changeCardStatus(id: number, actionCode: string): Observable<any> {
    return this.http.post(
      '/core/api/cards/change-status/' + id + '/' + actionCode,
      null
    );
  }

  changeStaffCardStatus(id: number, actionCode: string): Observable<any> {
    return this.http.post(
      '/core/api/staff-cards/change-status/' + id + '/' + actionCode,
      null
    );
  }

  findStaffCards(pageNumber): Observable<any> {
    let params = new HttpParams()
      .set('StaffCardSearchCriteria', '')
      .set('page', pageNumber);
    return this.http.get<any>('/core/api/staff-cards/list', { params: params });
  }

  searchStaffCardsToPrint(
    uin: any,
    companyCode: any,
    nationality: any,
    seasonYear: any,
    ritualCode: any,
    excludedCardsIds,
    pageNumber: any
  ): Observable<any> {
    let params = new HttpParams().set('page', pageNumber);
    if (excludedCardsIds.length > 0) {
      params = params.append('excludedCardsIds', excludedCardsIds);
    }
    return this.http.get(
      '/core/api/staff-cards/list/ready-to-print/' +
        (uin ? uin : -1) +
        '/' +
        (companyCode ? companyCode : -1) +
        '/' +
        (nationality ? nationality : -1) +
        '/' +
        (seasonYear ? seasonYear : -1) +
        '/' +
        (ritualCode ? ritualCode : -1),
      { params: params }
    );
  }

  searchAllStaffCardsToPrint(
    uin: any,
    companyCode: any,
    nationality: any,
    seasonYear: any,
    ritualCode: any,
    excludedCardsIds
  ): Observable<any> {
    let params = new HttpParams();
    if (excludedCardsIds.length > 0) {
      params = params.append('excludedCardsIds', excludedCardsIds);
    }
    return this.http.get(
      '/core/api/staff-cards/list/ready-to-print/all/' +
        (uin ? uin : -1) +
        '/' +
        (companyCode ? companyCode : -1) +
        '/' +
        (nationality ? nationality : -1) +
        '/' +
        (seasonYear ? seasonYear : -1) +
        '/' +
        (ritualCode ? ritualCode : -1),
      { params: params }
    );
  }

  findRitualSeasons(): Observable<any[]> {
    return this.http.get<any>('/core/api/lookup/ritual-seasons-years/list');
  }

  staffCardlist(
    pageNumber: any,
    staffCardSearchCriteria: StaffCardSearchCriteria
  ): Observable<any> {
    let params = new HttpParams()
      .set('staffCardSearchCriteria', JSON.stringify(staffCardSearchCriteria))
      .set('page', pageNumber);
    return this.http.get<any>('/core/api/staff-cards/list', { params: params });
  }

  findCompanyNames(): Observable<CompanyLite[]> {
    return this.http.get<any>('/core/api/lookup/company-names/list');
  }

  findCompanyNamesBySeason(seasonYear:number): Observable<CompanyLite[]> {
    return this.http.get<any>('/core/api/lookup/company-names/list/' + seasonYear);
  }

  findStaffCardById(id: number): Observable<any> {
    return this.http.get<any>('/core/api/staff-cards/find/' + id);
  }

  sendPrintRequestToPrinter(body:string,headers:any)
  {
    return this.http.post('http://localhost:5000/printservice/print', body,{'headers':headers})
  }

  generateCard(input:GenerateCardInput): Observable<any> {
    return this.http.post('/core/api/cards/generate-card',input);
  }

  generatStaffCard(input:GenerateStaffCardInput): Observable<any> {
    return this.http.post('/core/api/staff-cards/generate-card',input);
  }
}
