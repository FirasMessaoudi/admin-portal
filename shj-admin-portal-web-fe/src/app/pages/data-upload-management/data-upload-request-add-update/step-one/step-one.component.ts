import {Component, OnInit} from '@angular/core';
import {DataUploadService} from "@core/services/data/data-upload.service";
import * as FileSaver from 'file-saver';
import {Observable} from "rxjs";
import {HttpEventType, HttpResponse} from "@angular/common/http";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-step-one',
  templateUrl: './step-one.component.html',
  styleUrls: ['./step-one.component.scss']
})
export class StepOneComponent implements OnInit {

  uploadForm: FormGroup;
  selectedFiles: FileList;
  currentFile: File;
  progress = 0;
  message = '';
  fileInfos: Observable<any>;

  constructor(private dataUploadService: DataUploadService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.uploadForm = this.formBuilder.group({
      fileData: ['', [Validators.required]]
    });
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
    let result = contentDispositionHeader.split(';')[1].trim().split('=')[1];
    return result.replace(/"/g, '');
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  uploadDataFile() {
    this.progress = 0;
    this.currentFile = this.selectedFiles.item(0);
    this.dataUploadService.uploadDataFile(this.currentFile, 1).subscribe(
      event => {
        if (event.type === HttpEventType.UploadProgress) {
          this.progress = Math.round(100 * event.loaded / event.total);
        } else if (event instanceof HttpResponse) {
          this.message = event.body.message;
        }
      },
      err => {
        this.progress = 0;
        this.message = 'Could not upload the file!';
        this.currentFile = undefined;
      });

    this.selectedFiles = undefined;
  }

}
