import {Component, Input, OnInit} from '@angular/core';
import {PrintRequest} from "@model/print-request.model";
import {PrintService} from "@core/services/print/print.service";
import {Router} from "@angular/router";
import {ToastService} from "@shared/components/toast";
import {I18nService} from "@dcc-commons-ng/services";
import {TranslateService} from "@ngx-translate/core";
import {PrintRequestStorage} from "@pages/printing-management/printing-request-add-update/print-request-storage";

@Component({
  selector: 'app-step-three',
  templateUrl: './step-three.component.html',
  styleUrls: ['./step-three.component.scss']
})
export class StepThreeComponent implements OnInit {

  @Input()
  printRequest: PrintRequest;

  constructor(private printService: PrintService,
              private router: Router,
              private toastr: ToastService,
              private i18nService: I18nService,
              private translate: TranslateService,
              private printRequestStorage: PrintRequestStorage) {
  }

  ngOnInit(): void {
  }

  confirm() {
    console.log("confirm batching");
    this.printService.confirm(this.printRequest).subscribe(result => {
      this.printRequestStorage.storage = result;
      this.router.navigate(['/print-requests/success']).then(r => {
      });
    }, _ => {
      this.toastr.warning(this.translate.instant("print-request-management.dialog_confirm_request_error_text"), this.translate.instant("general.dialog_error_title"));
    });
  }
}
