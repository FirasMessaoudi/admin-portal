import {Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse, HttpEvent, HttpParams} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {catchError} from "rxjs/internal/operators";
import {Lookup} from "@model/lookup.model";
import {PrintBatchType} from "@model/print-batch-type.model";

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

  listNew(pageNumber: any): Observable<any> {
    let params = new HttpParams().set('page', pageNumber);
    return this.http.get<any>("/core/api/print/requests/list/new", {params: params});
  }

  /**
   * Finds print request by his ID from the server.
   *
   *@param requestId the user id
   * @return {Observable<PrintRequest>} The request identified by its ID.
   */
  find(requestId: number): Observable<any> {
    return this.http.get<any>('/core/api/print/requests/find/' + requestId).pipe(
      catchError(
        (error: any, caught: Observable<HttpEvent<any>>) => {
          console.error(error);
          return of(null);
        }
      )
    );
  }

  save(applicantsIds: Number[]): Observable<any> {
    return this.http.post<any>("/core/api/print/requests/create", applicantsIds).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.hasOwnProperty('error')) {
          return of(error.error);
        } else {
          console.error('An error happen while saving the print request : ' + error);
          return of(error);
        }
      }));
  }

  findPrintRequestStatuses(): Observable<Lookup[]> {
    return this.http.get<any>('/core/api/lookup/print-request-status/list');
  }

  findPrintBatchTypes(): Observable<PrintBatchType[]> {
    return this.http.get<any>('/core/api/lookup/print-batch-type/list');
  }

  batch(requestId: number, printBatchTypes: string[]): Observable<any> {
    return this.http.post<any>("/core/api/print/requests/" + requestId + "/batch", printBatchTypes).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.hasOwnProperty('error')) {
          return of(error.error);
        } else {
          console.error('An error happen while batching the print request : ' + error);
          return of(error);
        }
      }));
  }
}
