import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Lookup} from "@model/lookup.model";
import {IncidentSearchCriteria} from "@model/incident-search-criteria.model";

@Injectable({
  providedIn: 'root'
})
export class IncidentService {

  constructor(private http: HttpClient) {
  }

  list(pageNumber: any, incidentSearchCriteria: IncidentSearchCriteria): Observable<any> {
    let params = new HttpParams().set('page', pageNumber);
    return this.http.post<any>("/core/api/incidents/list", incidentSearchCriteria, {params: params});
  }

  findIncidentTypes(): Observable<Lookup[]> {
    return this.http.get<any>('/core/api/lookup/incident-type/list');
  }

  findIncidentStatuses() {
    return this.http.get<any>('/core/api/lookup/incident-status/list');
  }
}
