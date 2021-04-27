import {Component, OnInit} from '@angular/core';
import {EAuthority, Page} from "@shared/model";
import {AuthenticationService} from "@core/services";
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'app-printing-request-list',
  templateUrl: './data-request-list.component.html',
  styleUrls: ['./data-request-list.component.scss']
})
export class DataRequestListComponent implements OnInit {

  pageArray: Array<number>;
  page: Page;
  searchForm: FormGroup;
  canCreateNewRequest: boolean;

  constructor(private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    this.canCreateNewRequest = true;
  }

  private initForm(): void {

  }

  get canSeeDataUploadRequestsList(): boolean {
    //TODO: change it to DATA_UPLOAD_MANAGEMENT
    return this.authenticationService.hasAuthority(EAuthority.USER_MANAGEMENT);
  }

  cancelSearch() {
    this.initForm();
  }

}
