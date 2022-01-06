import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {PrintRequest} from "@model/print-request.model";
import {Router} from "@angular/router";
import {StaffPrintRequestStorage} from "@pages/printing-management/staff-printing-request-add/staff-print-request-storage";

@Component({
  selector: 'app-staff-success',
  templateUrl: './staff-success.component.html',
  styleUrls: ['./staff-success.component.scss']
})
export class StaffSuccessComponent implements OnInit {


  printRequest: PrintRequest;

  constructor(private cdr: ChangeDetectorRef,
              private router: Router,
              private printRequestStorage: StaffPrintRequestStorage) {
  }

  ngOnInit() {
    this.printRequest = this.printRequestStorage.storage;
    this.printRequestStorage.storage = null;
    if (this.printRequest == null) {
      this.router.navigate(['/staff-print-requests/list']).then(r => {
        console.log("printRequest parameter was not found")
      });
    }
  }

  ngAfterViewInit() {
    this.cdr.detectChanges();
  }


}
