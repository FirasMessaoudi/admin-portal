import {Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse, HttpParams} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {catchError} from "rxjs/internal/operators";

/**
 * Provides a base for printing operations.
 */
@Injectable({
  providedIn: 'root'
})
export class PrintService {

  constructor(private http: HttpClient) {
  }

  list(pageNumber: any): Observable<any> {
    let params = new HttpParams().set('page', pageNumber);
    return this.http.get<any>("/core/api/print/requests/list", {params: params});
  }

  save(applicantsIds: Number[]): Observable<any> {
    return this.http.post<any>("/core/api/print/requests/create", applicantsIds).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.hasOwnProperty('error')) {
          return of(error.error);
        } else {
          console.error('An error happen while resetting user password : ' + error);
          return of(error);
        }
      }));
  }
}
