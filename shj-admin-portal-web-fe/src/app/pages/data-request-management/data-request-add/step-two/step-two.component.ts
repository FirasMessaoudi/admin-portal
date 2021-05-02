import {Component, OnInit} from '@angular/core';
import {DataRequest} from "@shared/model";
import {I18nService} from "@dcc-commons-ng/services";
import {DataRequestService} from "@core/services";
import {Router} from "@angular/router";
import {ToastService} from "@shared/components/toast";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-step-two',
  templateUrl: './step-two.component.html',
  styleUrls: ['./step-two.component.scss']
})
export class StepTwoComponent implements OnInit {

  dataRequest: DataRequest;
  disclaimerChecked = false;

  constructor(private router: Router,
              private toastr: ToastService,
              private i18nService: I18nService,
              private translate: TranslateService,
              private dataRequestService: DataRequestService) {
  }

  ngOnInit(): void {
  }

  confirmRequest() {
    console.log("Confirming data request #" + this.dataRequest.id)
    this.dataRequestService.confirm(this.dataRequest.id).subscribe(_ => {
      this.router.navigate(['/data-requests/success']).then(r => {
      });
    }, _ => {
      this.toastr.warning(this.translate.instant("data-request-management.dialog_confirm_request_error_text"), this.translate.instant("data-request-management.choose_segment"));
    });
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

}
