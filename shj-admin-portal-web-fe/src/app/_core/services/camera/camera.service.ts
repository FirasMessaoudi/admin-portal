import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpParams} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {catchError} from "rxjs/internal/operators";
@Injectable({
  providedIn: 'root'
})
export class CameraService {

  constructor(private http: HttpClient) {
  }

  CountTotalCamerasByStatus(status:string): Observable<any> {
    return this.http.post<any>("core/api/cameras/count/camera_by_status", status)
      .pipe(catchError((error: HttpErrorResponse) => {
          if (error.hasOwnProperty('error')) {
            return of(error.error);
          } else {
            return of(error);
          }
        })
      );
  }


}
