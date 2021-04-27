import {Component, OnInit} from '@angular/core';
import * as FileSaver from 'file-saver';
import {Observable} from "rxjs";
import {HttpEventType, HttpResponse} from "@angular/common/http";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {DataRequestService} from "@core/services/data/data-request.service";
import {DataRequest, DataSegment} from "@shared/model";
import {ToastService} from "@shared/components/toast";
import {TranslateService} from "@ngx-translate/core";

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
  errorMessage = '';
  createdDataRequest: DataRequest;
  fileInfos: Observable<any>;

  constructor(private dataRequestService: DataRequestService,
              private toastr: ToastService,
              private translate: TranslateService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.uploadForm = this.formBuilder.group({
      fileData: ['', [Validators.required]]
    });
  }

  downloadTemplate(dataSegmentId: any) {
    this.dataRequestService.downloadTemplate(dataSegmentId).pipe(
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
    this.errorMessage = null;
    this.currentFile = this.selectedFiles.item(0);
    let dataRequest: DataRequest = new DataRequest();
    let dataSegment: DataSegment = new DataSegment();
    dataSegment.id = 1;
    dataRequest.dataSegment = dataSegment;
    this.dataRequestService.create(dataRequest, this.currentFile).subscribe(
      event => {
        if (event.type === HttpEventType.UploadProgress) {
          this.progress = Math.round(100 * event.loaded / event.total);
        } else if (event instanceof HttpResponse) {
          this.createdDataRequest = event.body;
          console.log(this.createdDataRequest);
          this.toastr.success(this.translate.instant("general.dialog_add_success_text"), this.translate.instant("data-upload-management.choose_segment"));
        }
      },
      err => {
        this.progress = 0;
        this.currentFile = null;
        this.toastr.warning(this.translate.instant("general.dialog_form_error_text"), this.translate.instant("data-upload-management.choose_segment"));
        let responseError = err.error;
        if (responseError.hasOwnProperty("errors") && responseError.errors) {
          this.errorMessage =  (responseError.errors["request"] ? this.translate.instant(responseError.errors["request"]) : responseError.errorMessage);
        }
      });

    this.selectedFiles = null;
  }

}
