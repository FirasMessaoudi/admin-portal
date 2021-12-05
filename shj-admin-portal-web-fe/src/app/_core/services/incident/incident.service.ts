import {Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse, HttpEvent, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {Lookup} from "@model/lookup.model";
import {IncidentSearchCriteria} from "@model/incident-search-criteria.model";
import {catchError} from "rxjs/internal/operators";
import {ApplicantIncident} from "@model/applicant-incident.model";
import {ApplicantIncidentVo} from "@model/applicant-incident-vo.model";
import {CookieService} from "ngx-cookie-service";

@Injectable({
  providedIn: 'root'
})
export class IncidentService {

  constructor(private http: HttpClient, private cookieService: CookieService) {
  }

  /**
   * list all incidents.
   */
  list(pageNumber: any, incidentSearchCriteria: IncidentSearchCriteria): Observable<any> {
    let params = new HttpParams().set('page', pageNumber);
    return this.http.post<any>("/core/api/incidents/list", incidentSearchCriteria, {params: params});
  }

  /**
   * load all incident types.
   */
  findIncidentTypes(): Observable<Lookup[]> {
    return this.http.get<any>('/core/api/lookup/incident-type/list');
  }

  /**
   * load all incident statuses.
   */
  findIncidentStatuses() {
    return this.http.get<any>('/core/api/lookup/incident-status/list');
  }

  /**
   * Finds incident by its ID from the server.
   *
   *@param incidentId the incident id
   * @return {Observable<ApplicantIncident>} The incident identified by incidentId.
   */
  find(incidentId: number): Observable<ApplicantIncident> {
    return this.http.get<any>('/core/api/incidents/find/' + incidentId).pipe(
      catchError(
        (error: any, caught: Observable<HttpEvent<any>>) => {
          console.error(error);
          return of(null);
        }
      )
    );
  }

  /**
   * Handles incident by marking it as resolved or closed.
   *
   * @param incidentId the ID of the incident to update
   * @param incidentVo the incident value object containing resolution comment and resolution type
   */
  handle(incidentId: number, incidentVo: ApplicantIncidentVo): Observable<any> {
    let headers = new HttpHeaders();
    headers = headers.set('X-XSRF-TOKEN', this.cookieService.get("XSRF-TOKEN"));
    return this.http.put<any>('/core/api/incidents/handle/' + incidentId, incidentVo, {'headers': headers})
      .pipe(catchError((error: HttpErrorResponse) => {
          if (error.hasOwnProperty('error')) {
            return of(error.error);
          } else {
            console.error('An error happened while updating incident : ' + error);
            return of(error);
          }
        })
      );
  }
}
