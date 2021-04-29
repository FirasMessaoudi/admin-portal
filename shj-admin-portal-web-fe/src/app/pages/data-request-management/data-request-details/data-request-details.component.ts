import {Component, OnInit} from '@angular/core';
import {I18nService} from "@dcc-commons-ng/services";
import {DataRequest, EAuthority} from "@shared/model";
import {AuthenticationService, DataRequestService} from "@core/services";
import {ActivatedRoute, Router} from "@angular/router";
import {ToastService} from "@shared/components/toast";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-printing-request-details',
  templateUrl: './data-request-details.component.html',
  styleUrls: ['./data-request-details.component.scss']
})
export class DataRequestDetailsComponent implements OnInit {

  dataRequestId: number;
  dataRequest: DataRequest;

  constructor(private i18nService: I18nService,
              private activeRoute: ActivatedRoute,
              private router: Router,
              private dataRequestService: DataRequestService,
              private toastr: ToastService,
              private translate: TranslateService,
              private authenticationService: AuthenticationService) {
  }

  ngOnInit(): void {
    this.dataRequestId = this.activeRoute.snapshot.params.id;

    if (this.dataRequestId) {
      // load user details
      this.dataRequestService.find(this.dataRequestId).subscribe(data => {
        if (data && data.id) {
          this.dataRequest = data;
        } else {
          this.toastr.error(this.translate.instant('general.route_item_not_found', {itemId: this.dataRequestId}),
            this.translate.instant('general.dialog_error_title'));
          this.goToList();
        }
      });
    } else {
      this.toastr.error(this.translate.instant('general.route_id_param_not_found'),
        this.translate.instant('general.dialog_error_title'));
      this.goToList();
    }
  }

  goToList() {
    this.router.navigate(['/data-requests/list']).then(r => {});
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  get canSeeDataRequestDetails(): boolean {
    //TODO: change it to DATA_REQUEST_MANAGEMENT
    return this.authenticationService.hasAuthority(EAuthority.USER_MANAGEMENT);
  }

}
