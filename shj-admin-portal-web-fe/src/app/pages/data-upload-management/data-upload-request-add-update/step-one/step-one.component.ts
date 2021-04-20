import {Component, OnInit} from '@angular/core';
import {DataUploadService} from "../../../../_core/services/data/data-upload.service";
import * as FileSaver from 'file-saver';

@Component({
  selector: 'app-step-one',
  templateUrl: './step-one.component.html',
  styleUrls: ['./step-one.component.scss']
})
export class StepOneComponent implements OnInit {

  constructor(private dataUploadService: DataUploadService) {
  }

  ngOnInit(): void {
  }

  downloadTemplate(dataSegmentId: any) {
    this.dataUploadService.downloadTemplate(dataSegmentId).pipe(
    ).subscribe({
      next: (response: any) => {
        let fileName = 'file';
        const contentDisposition = response.headers.get('Content-Disposition');
        if (contentDisposition) {
          const fileNameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
          const matches = fileNameRegex.exec(contentDisposition);
          if (matches != null && matches[1]) {
            fileName = matches[1].replace(/['"]/g, '');
          }
        }
        const fileContent = response.body;
        FileSaver.saveAs(fileContent, fileName);
      },
      error: (error) => {
        console.log('Error downloading the file.')
      }
    });
  }

  retrieveFileNameFromHttpResponse(httpResponse) {
    let contentDispositionHeader = httpResponse.headers('Content-Disposition');
    var result = contentDispositionHeader.split(';')[1].trim().split('=')[1];
    return result.replace(/"/g, '');
  }

}
