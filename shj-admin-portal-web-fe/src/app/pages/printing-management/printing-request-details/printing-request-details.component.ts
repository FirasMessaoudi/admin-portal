import {Component, OnInit} from '@angular/core';
import {I18nService} from "@dcc-commons-ng/services";
import {EAuthority} from "@shared/model";
import {AuthenticationService} from "@core/services";
import {combineLatest} from "rxjs";
import {map} from "rxjs/operators";
import {ActivatedRoute, Router} from "@angular/router";
import {ToastService} from "@shared/components/toast";
import {TranslateService} from "@ngx-translate/core";
import {PrintService} from "@core/services/print/print.service";
import {PrintRequest} from "@model/print-request.model";
import {LookupService} from "@core/utilities/lookup.service";
import {Lookup} from "@model/lookup.model";

@Component({
  selector: 'app-printing-request-details',
  templateUrl: './printing-request-details.component.html',
  styleUrls: ['./printing-request-details.component.scss']
})
export class PrintingRequestDetailsComponent implements OnInit {
  public isCollapsed: boolean[] = [];

  printRequestId: number;
  printRequest: PrintRequest;
  printRequestStatuses: Lookup[];

  constructor(private i18nService: I18nService,
              private route: ActivatedRoute,
              private router: Router,
              private toastr: ToastService,
              private translate: TranslateService,
              private printService: PrintService,
              private lookupsService: LookupService,
              private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    this.loadLookups();
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

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  get canSeePrintRequestDetails(): boolean {
    //TODO: change it to PRINTING_MANAGEMENT
    return this.authenticationService.hasAuthority(EAuthority.USER_MANAGEMENT);
  }

  goToList() {
    this.router.navigate(['/print-requests/list']);
  }

  lookupService(): LookupService {
    return this.lookupsService;
  }

  loadLookups() {
    this.printService.findPrintRequestStatuses().subscribe(result => {
      this.printRequestStatuses = result;
    });
  }

}
