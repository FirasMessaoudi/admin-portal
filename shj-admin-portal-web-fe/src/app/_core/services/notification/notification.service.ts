import {Injectable} from "@angular/core";
import {HttpClient, HttpEvent, HttpParams} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {Lookup} from "@model/lookup.model";
import {NotificationSearchCriteria} from "@model/notification-search-criteria.model";
import {catchError} from "rxjs/internal/operators";

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
    return this.http.post<any>("/core/api/notification/template/system-defined/list", notificationSearchCriteria, {params: params});
  }

  listUserDefined(pageNumber: any, notificationSearchCriteria: NotificationSearchCriteria): Observable<any> {
    let params = new HttpParams().set('page', pageNumber);
    return this.http.post<any>("/core/api/notification/template/user-defined/list", notificationSearchCriteria, {params: params});
  }


  /**
   * Finds notification Template  by his ID from the server.
   *
   *@param templateId the template Id
   * @return {Observable<PrintRequest>} The template identified by its ID.
   */
  findNotificationTemplateById(templateId: number): Observable<any> {
    return this.http.get<any>('/core/api/notification/template/' + templateId).pipe(
      catchError(
        (error: any, caught: Observable<HttpEvent<any>>) => {
          console.error(error);
          return of(null);
        }
      )
    );
  }

}
