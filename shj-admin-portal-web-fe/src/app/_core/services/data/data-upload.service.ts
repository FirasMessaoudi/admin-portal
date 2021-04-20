import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataUploadService {

  constructor(private http: HttpClient) {
  }

  /**
   * Downloads a template for a specific segment
   *
   * @param segmentId data segment Id
   * @return the template for the given segment
   */
  downloadTemplate(segmentId): Observable<any> {
    return this.http.get('/core/api/data/upload/tpl/' + segmentId,{
      responseType: 'blob' as 'json',
      observe: 'response' as 'body'
    });
  }

}
