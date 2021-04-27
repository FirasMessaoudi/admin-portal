import {ChangeDetectorRef, Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-data-request-add-update',
  templateUrl: './data-request-add.component.html',
  styleUrls: ['./data-request-add.component.scss']
})
export class DataRequestAddComponent implements OnInit {

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
