import {Component, OnInit} from '@angular/core';
import {GeneralDashboardVo} from "@model/dashboard-general-numbers-vo.model";
import {Subscription} from "rxjs";
import {DashboardService} from "@core/services";
import {Router} from "@angular/router";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {


  public cctv: number = 982;
  public appdownloads: number = 1103402;

  currentSeasonData: GeneralDashboardVo;
  previousSeasonData: GeneralDashboardVo;
  currentSeasonPercentage: number;
  previousSeasonPercentage: number;

  private currentSeasonSubscription: Subscription;
  private previousSeasonSubscription: Subscription;

  constructor(private dashboardService: DashboardService,
              private router: Router) {
  }

  ngOnInit() {
    this.currentSeasonSubscription = this.dashboardService
      .loadGeneralNumbersForCurrentSeason()
      .subscribe((data) => {
        this.currentSeasonData = data;
        this.currentSeasonPercentage =
          (100 * data.totalNumberOfExternalApplicants) /
          data.totalNumberOfApplicants;
        this.currentSeasonData.totalNumberOfApplicants = this.currentSeasonData.totalNumberOfExternalApplicants + this.currentSeasonData.totalNumberOfInternalApplicants;

      });

    this.previousSeasonSubscription = this.dashboardService
      .loadGeneralNumbersForPreviousSeason()
      .subscribe((data) => {
        this.previousSeasonData = data;
        this.previousSeasonPercentage =
          (100 * data.totalNumberOfExternalApplicants) /
          data.totalNumberOfApplicants;
        this.previousSeasonData.totalNumberOfApplicants = this.previousSeasonData.totalNumberOfExternalApplicants + this.previousSeasonData.totalNumberOfInternalApplicants;

      });
  }

  ngOnDestroy() {
    if (this.currentSeasonSubscription) {
      this.currentSeasonSubscription.unsubscribe();
    }
    if (this.previousSeasonSubscription) {
      this.previousSeasonSubscription.unsubscribe();
    }
  }

  navigateToGeneralNumbers() {
    this.router.navigate(['/dashboard/general-numbers'], {replaceUrl: true});
  }
}
