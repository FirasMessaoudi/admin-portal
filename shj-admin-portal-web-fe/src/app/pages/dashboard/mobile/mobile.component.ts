import {Component, OnInit} from '@angular/core';
import {EAuthority} from "@shared/model";
import {AuthenticationService} from "@core/services";

@Component({
  selector: 'app-mobile',
  templateUrl: './mobile.component.html',
  styleUrls: ['./mobile.component.scss']
})
export class MobileComponent implements OnInit {
  model = 1;
  public appdownloads: number = 1103402;

  constructor(private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
  }

  get canSeeMobileTrackingDashboard(): boolean {
    return this.authenticationService.hasAuthority(EAuthority.MOBILE_TRACKING_DASHBOARD);
  }
}
