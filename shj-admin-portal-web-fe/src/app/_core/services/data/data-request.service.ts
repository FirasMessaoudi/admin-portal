import {Injectable} from '@angular/core';
import {HttpClient, HttpEvent, HttpHeaders, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {DataRequest} from "@shared/model";

@Injectable({
  providedIn: 'root'
})
export class DataRequestService {

  constructor(private http: HttpClient) {
  }

  /**
   * Downloads a template for a specific segment
   *
   * @param segmentId data segment Id
   * @return the template for the given segment
   */
  downloadTemplate(segmentId: number): Observable<any> {
    return this.http.get('/core/api/data/upload/tpl/' + segmentId, {
      responseType: 'blob' as 'json',
      observe: 'response' as 'body'
    });
  }

  /**
   * Creates a new data request
   *
   * @param dataRequest the data request
   * @return the persisted request updated
   */
  create(dataRequest: DataRequest, file: any): Observable<HttpEvent<any>> {
    let formData: FormData = new FormData();
    formData.append("request", new Blob([JSON.stringify(dataRequest)], {
      type: 'application/json'
    }));
    formData.append("file", file);
    const req = new HttpRequest('POST', '/core/api/data/request/create', formData, {
      headers: new HttpHeaders({ "Content-Type": "multipart/form-data" }),
      reportProgress: true,
      responseType: 'json'
    });
    return this.http.request(req);
  }

}
