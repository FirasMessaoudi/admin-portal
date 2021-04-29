import {AfterViewInit, ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {DataRequest} from "@shared/model";
import {DataRequestStorage} from "@pages/data-request-management/data-request-add/data-request-storage";
import {I18nService} from "@dcc-commons-ng/services";
import {DataRequestService} from "@core/services";

@Component({
  selector: 'app-step-two',
  templateUrl: './step-two.component.html',
  styleUrls: ['./step-two.component.scss']
})
export class StepTwoComponent implements OnInit {

  dataRequest: DataRequest;
  disclaimerChecked = false;

  constructor(private i18nService: I18nService,
              private dataRequestService: DataRequestService) {
  }

  ngOnInit(): void {
  }

  confirmRequest() {
    console.log("Confirming data request #" + this.dataRequest.id)
    this.dataRequestService.confirm(this.dataRequest.id)
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

}
