import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpEvent, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {Lookup} from '@model/lookup.model';
import {NotificationSearchCriteria} from "@model/notification-search-criteria.model";
import {catchError} from "rxjs/internal/operators";
import {NotificationTemplate} from "@model/notification-template.model";
import {CookieService} from "ngx-cookie-service";
import {ApplicantSearchCriteria} from "@model/applicant-search-criteria.model";

@Injectable({
  providedIn: 'root'
})
export class StaffService {

  constructor(private http: HttpClient, private cookieService: CookieService) {
  }
  loadStaffRoles(): Observable<any> {
    return this.http.get<any>('/core/api/notification/template/staff-title/list').pipe(
      catchError(
        (error: any, caught: Observable<HttpEvent<any>>) => {
          console.error(error);
          return of(null);
        }
      )
    );
  }
  countRegisteredStaff(): Observable<any> {
    return this.http.get<any>('/core/api/company-staff/count/registered-staff');
  }
  search(criteria: ApplicantSearchCriteria, excludedIds, pageNumber: any): Observable<any> {
    let params = new HttpParams().set('page', pageNumber);
    if (excludedIds.length > 0) {
      params = params.append('excludedIds', excludedIds);
    }
    return this.http.post<any>('/core/api/company-staff/find', criteria, {params: params})
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

  getStaffPreviewBadge(uin:String): Observable<any> { 
    const headers= new HttpHeaders().set('CALLER-TYPE', 'WEB-SERVICE');    
    return this.http.get<any>(`/core/api/company-staff/staff/preview/${uin}`,{ 'headers': headers });
  }

  getStaffAllBadge(uin:String): Observable<any> { 
    const headers= new HttpHeaders().set('CALLER-TYPE', 'WEB-SERVICE');    
    return this.http.get<any>(`/core/api/company-staff/staff/all//${uin}`,{ 'headers': headers });
  }

  getStaffPrePrintedBadge(uin:String): Observable<any> { 
    const headers= new HttpHeaders().set('CALLER-TYPE', 'WEB-SERVICE');    
    return this.http.get<any>(`/core/api/company-staff/staff/preprinted//${uin}`,{ 'headers': headers });
  }
  
}
