import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Lookup} from "@model/lookup.model";

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

}
