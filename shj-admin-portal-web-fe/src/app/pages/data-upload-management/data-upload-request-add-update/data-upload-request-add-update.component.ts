import {ChangeDetectorRef, Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-printing-request-add-update',
  templateUrl: './data-upload-request-add-update.component.html',
  styleUrls: ['./data-upload-request-add-update.component.scss']
})
export class DataUploadRequestAddUpdateComponent implements OnInit {

  constructor(private cdr: ChangeDetectorRef) { }

  ngOnInit(): void {
  }

  saveStepOne() {
    this.cdr.detectChanges();
  }

  saveStepTwo() {
    this.cdr.detectChanges();
  }

  confirm() {

  }

}
