import {Injectable} from '@angular/core';
import {HttpClient, HttpEvent, HttpParams} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {ApplicantCard} from "@model/card.model";
import {catchError} from "rxjs/internal/operators";
import {Lookup} from "@model/lookup.model";
import {CountryLookup} from "@model/country-lookup.model";
import {ApplicantCardSearchCriteria} from "@model/applicant-card-search-criteria.model";

@Injectable({
  providedIn: 'root'
})
export class CardService {

  constructor(private http: HttpClient) {
  }



  searchCardsToPrint(uin: any, idNumber: number, hamlahNumber: any, motawefNumber: any, passportNumber: any,
                     nationality: any, excludedCardsIds, pageNumber: any): Observable<any> {
    let params = new HttpParams().set('page', pageNumber);
    if (excludedCardsIds.length > 0) {
      params = params.append('excludedCardsIds', excludedCardsIds);
    }
    return this.http.get('/core/api/cards/list/ready-to-print/' + (uin ? uin : -1) + '/' + (idNumber ? idNumber : -1) + '/' +
      (hamlahNumber ? hamlahNumber : -1) + '/' + (motawefNumber ? motawefNumber : -1) + '/' +
      (passportNumber ? passportNumber : -1) + '/' + (nationality ? nationality : -1), {params: params});
  }

  list(pageNumber: any, applicantCardSearchCriteria: ApplicantCardSearchCriteria): Observable<any> {
    let params = new HttpParams().set('applicantCardSearchCriteria', JSON.stringify(applicantCardSearchCriteria))
      .set('page', pageNumber);
    return this.http.get<any>("/core/api/cards/list-applicant-cards", {params: params});
  }

  searchAllCardsToPrint(uin: any, idNumber: number, hamlahNumber: any, motawefNumber: any, passportNumber: any,
                        nationality: any, excludedCardsIds): Observable<any> {
    let params = new HttpParams();
    if (excludedCardsIds.length > 0) {
      params = params.append('excludedCardsIds', excludedCardsIds);
    }
    return this.http.get('/core/api/cards/list/ready-to-print/all/' + (uin ? uin : -1) + '/' + (idNumber ? idNumber : -1) + '/' +
      (hamlahNumber ? hamlahNumber : -1) + '/' + (motawefNumber ? motawefNumber : -1) + '/' +
      (passportNumber ? passportNumber : -1) + '/' + (nationality ? nationality : -1), {params: params});
  }

  /**
   * Finds card by its ID from the server.
   *
   * @param cardId the card id
   * @return {Observable<ApplicantCard>} The card identified by cardId.
   */
  find(cardId: number): Observable<ApplicantCard> {
    return this.http.get<any>('/core/api/cards/find/' + cardId).pipe(
      catchError(
        (error: any, caught: Observable<HttpEvent<any>>) => {
          console.error(error);
          return of(null);
        }
      )
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

  findCountries(): Observable<CountryLookup[]> {
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
}
