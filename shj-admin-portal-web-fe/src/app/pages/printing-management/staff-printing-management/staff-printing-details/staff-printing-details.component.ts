import {Component, OnDestroy, OnInit} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder} from "@angular/forms";
import {Role, User} from '@app/_shared/model';
import {AuthenticationService, UserService} from '@app/_core/services';
import {ToastService} from '@shared/components/toast/toast-service';
import {I18nService} from "@dcc-commons-ng/services";
import {TranslateService} from "@ngx-translate/core";
import {DatePipe} from "@angular/common";
import {RoleService} from "@core/services/role/role.service";
import {EAuthority} from "@model/enum/authority.enum";
import {NavigationService} from "@core/utilities/navigation.service";
import { StaffPrintService } from "@app/_core/services/printing/staff-print.service";
import { Staff } from "@app/_shared/model/staff.model";
@Component({
  selector: 'app-staff-printing-details',
  templateUrl: './staff-printing-details.component.html',
  styleUrls: ['./staff-printing-details.component.scss']
})
export class StaffPrintingDetailsComponent implements OnInit {
staffId: number;
staff: Staff;

  constructor(private activeRoute: ActivatedRoute,
    private router: Router,
    private toastr: ToastService,
    private translate: TranslateService,
    private formBuilder: FormBuilder,
    private i18nService: I18nService,
    private printService: StaffPrintService,
    private roleService: RoleService,
    private authentication: AuthenticationService,
    private navigation: NavigationService,
    public datePipe: DatePipe,
    private authenticationService: AuthenticationService,
    private navigationService: NavigationService) {
}

  ngOnInit(): void {
    //this.staffId = this.activeRoute.snapshot.params.id;
    this.getStaffDetails();
  }

  getStaffDetails(){
    if (true) {
      // load user details
      this.printService.findStaff(3, 1442).subscribe(data => {
        if (data && data.id) {
          this.staff = data;
        } else {
          this.toastr.error(this.translate.instant('general.route_item_not_found', {itemId: 1}),
            this.translate.instant('general.dialog_error_title'));
        }
      });
    } else {
      this.toastr.error(this.translate.instant('general.route_id_param_not_found'),
        this.translate.instant('general.dialog_error_title'));
    }
  }

  goBack() {
    this.navigationService.back();
  }

}
