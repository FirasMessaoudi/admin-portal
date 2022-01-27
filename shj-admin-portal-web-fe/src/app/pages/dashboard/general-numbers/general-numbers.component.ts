import {Component, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {DashboardService} from '@core/services';
import {GeneralDashboardVo} from '@model/dashboard-general-numbers-vo.model';
import {CountVo} from "@model/count-vo.model";

@Component({
  selector: 'app-general-numbers',
  templateUrl: './general-numbers.component.html',
  styleUrls: ['./general-numbers.component.scss'],
})
export class GeneralNumbersComponent implements OnInit {
  currentSeasonData: GeneralDashboardVo;
  previousSeasonData: GeneralDashboardVo;
  applicantsPerNationalities: CountVo[];
  currentSeasonPercentage: number;
  previousSeasonPercentage: number;

  private currentSeasonSubscription: Subscription;
  private previousSeasonSubscription: Subscription;
  private applicantsPerNationalitiesSubscription: Subscription;

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

    this.applicantsPerNationalitiesSubscription = this.dashboardService
      .loadGeneralNumbersForApplicantPerNationalities()
      .subscribe(data => {
        this.applicantsPerNationalities = data;
        console.log(this.applicantsPerNationalities);
      })
  }

  ngOnDestroy() {
    if (this.currentSeasonSubscription) {
      this.currentSeasonSubscription.unsubscribe();
    }
    if (this.previousSeasonSubscription) {
      this.previousSeasonSubscription.unsubscribe();
    }
    if (this.applicantsPerNationalitiesSubscription) {
      this.applicantsPerNationalitiesSubscription.unsubscribe();
    }
  }
}
