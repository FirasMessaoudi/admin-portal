import {Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {ApplicantSearchCriteria} from "@model/applicant-search-criteria.model";
import {catchError} from "rxjs/internal/operators";

/**
 * Provides a base for applicant operations.
 */
@Injectable({
  providedIn: 'root'
})
export class ApplicantService {

  constructor(private http: HttpClient) {
  }

  getApplicantBadge(uin:String): Observable<any> { 
    const headers= new HttpHeaders().set('CALLER-TYPE', 'WEB-SERVICE');    
    return this.http.get<any>(`/core/api/applicants/badge/applicant/frontback/${uin}`,{ 'headers': headers });
  }

  getApplicantPreview(uin:String): Observable<any> { 
    const headers= new HttpHeaders().set('CALLER-TYPE', 'WEB-SERVICE');    
    return this.http.get<any>(`/core/api/applicants/applicant/preview/${uin}`,{ 'headers': headers });
  }


  getApplicantPrePrinted(uin:String): Observable<any> { 
    const headers= new HttpHeaders().set('CALLER-TYPE', 'WEB-SERVICE');    
    return this.http.get<any>(`/core/api/applicants/applicant/preprinted/${uin}`,{ 'headers': headers });
  }

  getApplicantAll(uin:String): Observable<any> { 
    const headers= new HttpHeaders().set('CALLER-TYPE', 'WEB-SERVICE');    
    return this.http.get<any>(`/core/api/applicants/applicant/all/${uin}`,{ 'headers': headers });
  }

  findByIds(ids, pageNumber: any): Observable<any> {
    let params = new HttpParams().set('page', pageNumber);
    if (ids.length > 0) {
      params = params.append('ids', ids);
    }
    return this.http.get<any>("/core/api/applicants/find-by-ids", {params: params});
  }

  search(criteria: ApplicantSearchCriteria, excludedIds, pageNumber: any): Observable<any> {
    let params = new HttpParams().set('page', pageNumber);
    if (excludedIds.length > 0) {
      params = params.append('excludedIds', excludedIds);
    }
    return this.http.post<any>('/core/api/applicants/find', criteria, {params: params})
      .pipe(catchError((error: HttpErrorResponse) => {
          if (error.hasOwnProperty('error')) {
            return of(error.error);
          } else {
            console.error('An error happened while sending user notifications : ' + error);
            return of(error);
          }
        })
      );
  }

  countApplicantsHavingCurrentRitual(): Observable<any> {
    return this.http.get<any>("/core/api/applicants/count/having-current-ritual");
  }

  countCategorizedApplicants(criteria: ApplicantSearchCriteria): Observable<any> {
    return this.http.post<any>("/core/api/applicants/count/categorized", criteria)
      .pipe(catchError((error: HttpErrorResponse) => {
          if (error.hasOwnProperty('error')) {
            return of(error.error);
          } else {
            console.error('An error happened while sending user notifications : ' + error);
            return of(error);
          }
        })
      );
  }
}
