import {Component, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {DashboardService} from '@core/services';
import {GeneralDashboardVo} from '@model/dashboard-general-numbers-vo.model';

@Component({
  selector: 'app-general-numbers',
  templateUrl: './general-numbers.component.html',
  styleUrls: ['./general-numbers.component.scss'],
})
export class GeneralNumbersComponent implements OnInit {
  currentSeasonData: GeneralDashboardVo;
  previousSeasonData: GeneralDashboardVo;
  currentSeasonPercentage: number;
  previousSeasonPercentage: number;

  private currentSeasonSubscription: Subscription;
  private previousSeasonSubscription: Subscription;

  constructor(private dashboardService: DashboardService) {}

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
}
