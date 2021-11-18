import {Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse, HttpEvent, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {Lookup} from "@model/lookup.model";
import {NotificationSearchCriteria} from "@model/notification-search-criteria.model";
import {catchError} from "rxjs/internal/operators";
import {NotificationTemplate} from "@model/notification-template.model";
import {CookieService} from "ngx-cookie-service";
import {CategorizedNotificationVo} from "@model/categorized-notification-vo.model";

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

  /**
   * Finds user notification notification Template by its ID from the server.
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
  saveAsDraft(notificationTemplate: NotificationTemplate): Observable<any> {
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
   * Creates user defined notification template and sends it to all applicants having active ritual.
   *
   * @param notificationTemplate the notification template to be saved
   * @return {Observable<notificationTemplate>} created notificationTemplate.
   */
  saveAndSendToAll(notificationTemplate: NotificationTemplate): Observable<any> {
    return this.http.post<any>('/core/api/notification/template/send-to-all', notificationTemplate)
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

  /**
   * Creates user defined notification template and sends it to categorized applicants.
   *
   * @param categorizedNotification
   * @return {Observable<notificationTemplate>} created notificationTemplate.
   */
  saveAndSendToCategorizedApplicants(categorizedNotification: CategorizedNotificationVo): Observable<any> {
    return this.http.post<any>('/core/api/notification/template/send-to-categorized', categorizedNotification)
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

  /**
   * Creates user defined notification template and sends it to categorized applicants.
   *
   * @return {Observable<notificationTemplate>} created notificationTemplate.
   * @param notificationTemplate created notificationTemplate.
   * @param selectedApplicants
   */
  saveAndSendToSelectedApplicants(notificationTemplate: NotificationTemplate, selectedApplicants): Observable<any> {
    let params = new HttpParams();
    if (selectedApplicants.length > 0) {
      params = params.append('selectedApplicants', selectedApplicants);
    }
    return this.http.post<any>('/core/api/notification/template/send-to-selected', notificationTemplate, {params: params})
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

  /**
   * Creates or updates rule details in the server.
   *
   * @param notificationTemplate the notification Template to  update
   * @return {Observable<notificationTemplate>}  updated notificationTemplate.
   */
  updateNotificationTemplate(notificationTemplate: NotificationTemplate): Observable<any> {
    let headers = new HttpHeaders();
    headers = headers.set('X-XSRF-TOKEN', this.cookieService.get("XSRF-TOKEN"));
    return this.http.put<any>('/core/api/notification/template/update', notificationTemplate, {'headers': headers})
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
