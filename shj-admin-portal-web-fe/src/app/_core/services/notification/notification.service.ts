import {Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse, HttpEvent, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {Lookup} from "@model/lookup.model";
import {NotificationSearchCriteria} from "@model/notification-search-criteria.model";
import {catchError} from "rxjs/internal/operators";
import {NotificationTemplate} from "@model/notification-template.model";
import {CookieService} from "ngx-cookie-service";

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(private http: HttpClient, private cookieService: CookieService) {
  }

  findNotificationCategories(): Observable<Lookup[]> {
    return this.http.get<any>('/core/api/lookup/notification-category/list');
  }

  findNotificationTemplateNames(): Observable<Lookup[]> {
    return this.http.get<any>('/core/api/lookup/notification-name/list');
  }

  findNotificationTemplateStatuses(): Observable<Lookup[]> {
    return this.http.get<any>('/core/api/lookup/notification-template-status/list');
  }

  list(pageNumber: any, notificationSearchCriteria: NotificationSearchCriteria): Observable<any> {
    let params = new HttpParams().set('page', pageNumber);
    return this.http.post<any>("/core/api/notification/template/system-defined/list", notificationSearchCriteria, {params: params});
  }

  listUserDefined(pageNumber: any, notificationSearchCriteria: NotificationSearchCriteria): Observable<any> {
    let params = new HttpParams().set('page', pageNumber);
    return this.http.post<any>("/core/api/notification/template/user-defined/list", notificationSearchCriteria, {params: params});
  }

  findLanguages(): Observable<Lookup[]> {
    return this.http.get<any>('/core/api/lookup/language/list');
  }

  /**
   * Finds notification Template by his ID from the server.
   *
   * @param templateId the template ID
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

  /**
   * Finds user notification template by its ID from the server.
   *
   * @param templateId the template ID
   * @return {Observable<PrintRequest>} The template identified by its ID.
   */
  findUserDefinedNotificationTemplateById(templateId: number): Observable<any> {
    return this.http.get<any>('/core/api/notification/template/user-defined/' + templateId).pipe(
      catchError(
        (error: any, caught: Observable<HttpEvent<any>>) => {
          console.error(error);
          return of(null);
        }
      )
    );
  }

  /**
   * Creates user defined notification template and sets its status to draft.
   *
   * @param notificationTemplate the notification template to be saved
   * @return {Observable<notificationTemplate>} created notificationTemplate.
   */
  save(notificationTemplate: NotificationTemplate): Observable<any> {
    return this.http.post<any>('/core/api/notification/template/user-defined/create', notificationTemplate)
      .pipe(catchError((error: HttpErrorResponse) => {
          if (error.hasOwnProperty('error')) {
            return of(error.error);
          } else {
            console.error('An error happened while creating the notification template : ' + error);
            return of(error);
          }
        })
      );
  }

  /**
   * Creates or updates rule details in the server.
   *
   * @param notificationTemplate the notification Template to  update
   * @return {Observable<notificationTemplate>}  updated notificationTemplate.
   */
  updateNotificationTemplate(notificationTemplate: NotificationTemplate): Observable<any> {
    let headers = new HttpHeaders();
    headers = headers.set('X-XSRF-TOKEN', this.cookieService.get("XSRF-TOKEN"));
    return this.http.post<any>('/core/api/notification/template/update', notificationTemplate, {'headers': headers})
      .pipe(catchError((error: HttpErrorResponse) => {
          if (error.status == 558) {
            return of(error);
          }
          if (error.hasOwnProperty('error')) {
            return of(error.error);
          } else {
            console.error('An error happened while updating the notification Template : ' + error);
            return of(error);
          }
        })
      );
  }

  /**
   * Updates user defined notification template details in the server.
   *
   * @param notificationTemplate the notification template to update
   * @return {Observable<notificationTemplate>} updated notificationTemplate.
   */
  updateUserDefined(notificationTemplate: NotificationTemplate): Observable<any> {
    let headers = new HttpHeaders();
    headers = headers.set('X-XSRF-TOKEN', this.cookieService.get("XSRF-TOKEN"));
    return this.http.post<any>('/core/api/notification/template/user-defined/update', notificationTemplate, {'headers': headers})
      .pipe(catchError((error: HttpErrorResponse) => {
          if (error.hasOwnProperty('error')) {
            return of(error.error);
          } else {
            console.error('An error happened while updating the notification template : ' + error);
            return of(error);
          }
        })
      );
  }

  loadCompanies(): Observable<any> {
    return this.http.get<any>('/core/api/notification/template/company/list').pipe(
      catchError(
        (error: any, caught: Observable<HttpEvent<any>>) => {
          console.error(error);
          return of(null);
        }
      )
    );
  }

  loadCamps(): Observable<any> {
    return this.http.get<any>('/core/api/notification/template/camp/list').pipe(
      catchError(
        (error: any, caught: Observable<HttpEvent<any>>) => {
          console.error(error);
          return of(null);
        }
      )
    );
  }
}
