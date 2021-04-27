import {Component, OnInit} from '@angular/core';
import {I18nService} from "@dcc-commons-ng/services";
import {EAuthority} from "@shared/model";
import {AuthenticationService} from "@core/services";

@Component({
  selector: 'app-printing-request-details',
  templateUrl: './data-request-details.component.html',
  styleUrls: ['./data-request-details.component.scss']
})
export class DataRequestDetailsComponent implements OnInit {

  constructor(private i18nService: I18nService,
              private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  get canSeeDataUploadRequestDetails(): boolean {
    //TODO: change it to DATA_UPLOAD_MANAGEMENT
    return this.authenticationService.hasAuthority(EAuthority.USER_MANAGEMENT);
  }

}
