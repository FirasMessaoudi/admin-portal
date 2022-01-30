import {Component, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {DashboardService} from '@core/services';
import {GeneralDashboardVo} from '@model/dashboard-general-numbers-vo.model';
import {CountVo} from '@app/_shared/model/countVo.model';
import {Lookup} from "@model/lookup.model";
import {LookupService} from "@core/utilities/lookup.service";

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
  totalCounts: number = 0;
  countryList: Lookup[];
  countVoList: CountVo[];
  private currentSeasonSubscription: Subscription;
  private previousSeasonSubscription: Subscription;
  private applicantsPerNationalitiesSubscription: Subscription;
  private previousonSubscription: Subscription;

  constructor(private dashboardService: DashboardService, private lookupService: LookupService) {
  }

  ngOnInit() {
    this.loadLookups();
    this.currentSeasonSubscription = this.dashboardService
      .loadGeneralNumbersForCurrentSeason()
      .subscribe((data) => {
        this.currentSeasonData = data;
        this.currentSeasonPercentage =
          (100 * data.totalNumberOfExternalApplicants) /
          data.totalNumberOfApplicants;
        this.currentSeasonData.totalNumberOfApplicants = this.currentSeasonData.totalNumberOfExternalApplicants + this.currentSeasonData.totalNumberOfInternalApplicants;

      });

      this.dashboardService.loadApplicantsCountByAgeCurrentSeason().subscribe((data)=>{
        this.countVoList = data;
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
        this.applicantsPerNationalities.forEach(element => {
          this.totalCounts += element.count
          element.label = this.lookupService.localizedLabel(this.countryList, element.label)
        })
      })
  }

  loadLookups() {
    this.dashboardService.findNationalities().subscribe(
      data => this.countryList = data
    )
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
