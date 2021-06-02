import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {PrintRequest} from "@model/print-request.model";
import {Router} from "@angular/router";
import {PrintRequestStorage} from "@pages/printing-management/printing-request-add-update/print-request-storage";

@Component({
  selector: 'app-success',
  templateUrl: './success.component.html',
  styleUrls: ['./success.component.scss']
})
export class SuccessComponent implements OnInit {

  printRequest: PrintRequest;

  constructor(private cdr: ChangeDetectorRef,
              private router: Router,
              private printRequestStorage: PrintRequestStorage) {
  }

  ngOnInit() {
    this.printRequest = this.printRequestStorage.storage;
    this.printRequestStorage.storage = null;
    if (this.printRequest == null) {
      this.router.navigate(['/print-requests/list']).then(r => {
        console.log("printRequest parameter was not found")
      });
    }
  }

  ngAfterViewInit() {
    this.cdr.detectChanges();
  }

}
