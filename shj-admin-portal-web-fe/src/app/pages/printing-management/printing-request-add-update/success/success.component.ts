import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {combineLatest} from "rxjs";
import {map} from "rxjs/operators";
import {ToastService} from "@shared/components/toast";
import {TranslateService} from "@ngx-translate/core";
import {PrintService} from "@core/services/print/print.service";
import {PrintRequest} from "@model/print-request.model";

@Component({
  selector: 'app-success',
  templateUrl: './success.component.html',
  styleUrls: ['./success.component.scss']
})
export class SuccessComponent implements OnInit {
  printRequestId: number;
  printRequest: PrintRequest;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private toastr: ToastService,
              private translate: TranslateService,
              private printService: PrintService) {
  }

  ngOnInit() {
    combineLatest([this.route.params, this.route.queryParams]).pipe(map(results => ({
      params: results[0].id,
      qParams: results[1]
    }))).subscribe(results => {
      this.printRequestId = +results.params; // (+) converts string 'id' to a number

      if (this.printRequestId) {
        // load user details
        this.printService.find(this.printRequestId).subscribe(data => {
          if (data && data.id) {
            this.printRequest = data;
          } else {
            this.toastr.error(this.translate.instant('general.route_item_not_found', {itemId: this.printRequestId}),
              this.translate.instant('general.dialog_error_title'));
            this.goToList();
          }
        });
      } else {
        this.toastr.error(this.translate.instant('general.route_id_param_not_found'),
          this.translate.instant('general.dialog_error_title'));
        this.goToList();
      }
    });
  }

  goToList() {
    this.router.navigate(['/print-requests/list']);
  }

}
