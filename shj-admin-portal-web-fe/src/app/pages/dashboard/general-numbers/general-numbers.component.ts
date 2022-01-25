import {Component, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {DashboardService} from "@core/services";

@Component({
  selector: 'app-general-numbers',
  templateUrl: './general-numbers.component.html',
  styleUrls: ['./general-numbers.component.scss']
})
export class GeneralNumbersComponent implements OnInit {


  totalApplicantsCurrentSeason: number;
  externalApplicantsCurrentSeason: number;
  internalApplicantsCurrentSeason: number;

  totalApplicantsPreviousSeason: number;
  externalApplicantsPreviousSeason: number;
  internalApplicantsPreviousSeason: number;


  private loadApplicantsCurrentSeasonSubscription: Subscription;
  private loadApplicantsPreviousSeasonSubscription: Subscription;

  constructor(private dashboardService: DashboardService) {
  }

  ngOnInit() {

    this.loadApplicantsCurrentSeasonSubscription = this.dashboardService.loadApplicantsCountForCurrentSeason().subscribe(data => {
      this.externalApplicantsCurrentSeason = data.totalNumberOfExternalApplicantCountForHijriSeason;
      this.internalApplicantsCurrentSeason = data.totalNumberOfInternalApplicantCountForHijriSeason;
      this.totalApplicantsCurrentSeason = this.externalApplicantsCurrentSeason + this.internalApplicantsCurrentSeason;

    });

    this.loadApplicantsPreviousSeasonSubscription = this.dashboardService.loadApplicantsCountForPreviousSeason().subscribe(data => {
      this.externalApplicantsPreviousSeason = data.totalNumberOfExternalApplicantCountForHijriSeason;
      this.internalApplicantsPreviousSeason = data.totalNumberOfInternalApplicantCountForHijriSeason;
      this.totalApplicantsPreviousSeason = this.externalApplicantsPreviousSeason + this.internalApplicantsPreviousSeason;

    });
  }

  ngOnDestroy() {
    if (this.loadApplicantsCurrentSeasonSubscription) {
      this.loadApplicantsCurrentSeasonSubscription.unsubscribe();
    }
    if (this.loadApplicantsPreviousSeasonSubscription) {
      this.loadApplicantsPreviousSeasonSubscription.unsubscribe();
    }

  }

}
