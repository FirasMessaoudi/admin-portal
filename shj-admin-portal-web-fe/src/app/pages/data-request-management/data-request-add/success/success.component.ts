import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {DataRequest} from "@shared/model";
import {Router} from "@angular/router";
import {DataRequestStorage} from "@pages/data-request-management/data-request-add/data-request-storage";

@Component({
  selector: 'app-data-request-success',
  templateUrl: './success.component.html',
  styleUrls: ['./success.component.scss']
})
export class SuccessComponent implements OnInit {

  dataRequest: DataRequest;

  constructor(private cdr: ChangeDetectorRef,
              private router: Router,
              private dataRequestStorage: DataRequestStorage) {
  }

  ngOnInit() {
    this.dataRequest = this.dataRequestStorage.storage;
    this.dataRequestStorage.storage = null;
    if (this.dataRequest == null) {
      this.router.navigate(['/data-request/list']).then(r => {
        console.log("dataRequest parameter was not found")
      });
    }
  }

  ngAfterViewInit() {
    this.cdr.detectChanges();
  }

}
