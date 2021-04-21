import {Injectable} from '@angular/core';
import {HttpClient, HttpEvent, HttpHeaders, HttpRequest} from '@angular/common/http';
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
  downloadTemplate(segmentId: number): Observable<any> {
    return this.http.get('/core/api/data/upload/tpl/' + segmentId,{
      responseType: 'blob' as 'json',
      observe: 'response' as 'body'
    });
  }

  /**
   * Uploads a file for a specific data segment
   *
   * @param file      the file to upload
   * @param segmentId the data segment id related to the file
   * @return the total number of records detected by the system
   */
  uploadDataFile(file: any, segmentId: number): Observable<HttpEvent<any>> {
    let formData = new FormData();
    formData.append('file', file);
    const req = new HttpRequest('POST', '/core/api/data/upload/' + segmentId, formData, {
      headers: new HttpHeaders({ "Content-Type": "multipart/form-data" }),
      reportProgress: true,
      responseType: 'json'
    });
    return this.http.request(req);
  }

}
