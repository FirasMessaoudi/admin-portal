import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import * as FileSaver from 'file-saver';
import {Observable} from "rxjs";
import {HttpEventType, HttpResponse} from "@angular/common/http";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {DataRequestService} from "@core/services/data/data-request.service";
import {DataRequest, DataSegment} from "@shared/model";
import {ToastService} from "@shared/components/toast";
import {TranslateService} from "@ngx-translate/core";
import {DataSegmentService} from "@core/services";
import {I18nService} from "@dcc-commons-ng/services";
import {DataRequestStorage} from "@pages/data-request-management/data-request-add/data-request-storage";
import {NavigationService} from "@core/utilities/navigation.service";

@Component({
  selector: 'app-step-one',
  templateUrl: './step-one.component.html',
  styleUrls: ['./step-one.component.scss']
})
export class StepOneComponent implements OnInit {

  dataRequestForm: FormGroup;
  dataSegments: DataSegment[];
  progress = 0;
  errorMessage = '';
  selectedDataSegment: DataSegment;
  createdDataRequest: DataRequest
  fileInfos: Observable<any>;

  constructor(private dataRequestService: DataRequestService,
              private dataSegmentService: DataSegmentService,
              private toastr: ToastService,
              private i18nService: I18nService,
              private translate: TranslateService,
              private formBuilder: FormBuilder,
              private cdr: ChangeDetectorRef,
              private dataRequestStorage: DataRequestStorage,
              private navigationService: NavigationService
  ) {
  }

  ngOnInit(): void {
    this.dataRequestStorage.storage = null;
    this.dataRequestForm = this.formBuilder.group({
      fileData: [null, [Validators.required]],
      dataSegment: [null, [Validators.required]],
    });
    this.dataSegmentService.list().subscribe(data => {
      this.dataSegments = data;
    });
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.dataRequestForm.controls;
  }

  get currentLanguage(): string {
    return this.i18nService.language;
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
        this.toastr.warning(this.translate.instant("data-request-management.dialog_download_error_text"), this.translate.instant("data-request-management.choose_segment"));
      }
    });
  }

  retrieveFileNameFromHttpResponse(httpResponse) {
    let contentDispositionHeader = httpResponse.headers('Content-Disposition');
    let result = contentDispositionHeader.split(';')[1].trim().split('=')[1];
    return result.replace(/"/g, '');
  }

  uploadDataFile() {
    // trigger all validations
    Object.keys(this.dataRequestForm.controls).forEach(field => {
      const control = this.dataRequestForm.get(field);
      control.markAsTouched({onlySelf: true});
    });

    // stop here if form is invalid
    if (this.dataRequestForm.invalid) {
      return;
    }

    this.progress = 0;
    this.errorMessage = null;
    let dataRequest: DataRequest = new DataRequest();
    dataRequest.dataSegment = this.selectedDataSegment;
    this.dataRequestService.create(dataRequest, this.f.fileData.value).subscribe(
      event => {
        if (event.type === HttpEventType.UploadProgress) {
          this.progress = Math.round(100 * event.loaded / event.total);
        } else if (event instanceof HttpResponse) {
          this.dataRequestStorage.storage = event.body;
          this.createdDataRequest = event.body;
          console.log(this.dataRequestStorage.storage);
          this.cdr.detectChanges();
          this.toastr.success(this.translate.instant("general.dialog_add_success_text"), this.translate.instant("data-request-management.choose_segment"));
        }
      },
      err => {
        this.progress = 0;
        this.toastr.warning(this.translate.instant("general.dialog_form_error_text"), this.translate.instant("data-request-management.choose_segment"));
        let responseError = err.error;
        if (responseError && responseError.errors && responseError.hasOwnProperty("errors")) {
          this.errorMessage = (responseError.errors["request"] ? this.translate.instant(responseError.errors["request"]) : responseError.errorMessage);
        }
      });
  }

  goBack() {
    this.navigationService.back();
  }

  onFileChange(event) {
    if (event.target.files && event.target.files.length) {
      const [file] = event.target.files;
      this.f.fileData.setValue(file);
      // need to run CD since file load runs outside of zone
      this.cdr.markForCheck();
    }
  }

  onSegmentChange(event: any) {
    this.selectedDataSegment = event;
  }
}
