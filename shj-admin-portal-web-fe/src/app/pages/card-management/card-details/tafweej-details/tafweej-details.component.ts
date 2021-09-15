import {Component, Input, OnInit} from '@angular/core';
import {Lookup} from "@model/lookup.model";
import {CompanyRitualMainDataStep} from "@model/company-ritual-step";
import {LookupService} from "@core/utilities/lookup.service";

@Component({
  selector: 'app-tafweej-details',
  templateUrl: './tafweej-details.component.html',
  styleUrls: ['./tafweej-details.component.scss']
})
export class TafweejDetailsComponent implements OnInit {
  @Input()
  ritualStepsLabels: Lookup [];
  @Input()
  companyRitualSteps: CompanyRitualMainDataStep[];
  constructor(private lookupsService: LookupService
  ) { }

  ngOnInit(): void {
  }
  lookupService(): LookupService {
    return this.lookupsService;
  }
}
