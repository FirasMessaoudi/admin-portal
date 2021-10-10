import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Lookup} from "@model/lookup.model";
import {NotificationSearchCriteria} from "@model/notification-search-criteria.model";

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(private http: HttpClient) {
  }

  findNotificationCategories(): Observable<Lookup[]> {
    return this.http.get<any>('/core/api/lookup/notification-category/list');
  }

  findNotificationTemplateNames(): Observable<Lookup[]> {
    return this.http.get<any>('/core/api/lookup/notification-name/list');
  }

  list(pageNumber: any, notificationSearchCriteria: NotificationSearchCriteria): Observable<any> {
    let params = new HttpParams().set('page', pageNumber);
    return this.http.post<any>("/core/api/notification/template/list",notificationSearchCriteria, {params: params});
  }

}
