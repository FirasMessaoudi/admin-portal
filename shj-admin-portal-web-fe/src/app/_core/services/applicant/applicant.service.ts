import {Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse, HttpParams} from "@angular/common/http";
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

  list(pageNumber: any): Observable<any> {
    let params = new HttpParams().set('page', pageNumber);
    return this.http.get<any>("/core/api/applicants/list/all", {params: params});
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
}
