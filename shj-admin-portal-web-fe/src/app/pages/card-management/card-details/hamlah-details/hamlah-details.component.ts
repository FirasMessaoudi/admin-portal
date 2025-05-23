import {Component, Input, OnInit} from '@angular/core';
import {Lookup} from "@model/lookup.model";
import {ApplicantCard} from "@model/applicant-card.model";
import {LookupService} from "@core/utilities/lookup.service";

@Component({
  selector: 'app-hamlah-details',
  templateUrl: './hamlah-details.component.html',
  styleUrls: ['./hamlah-details.component.scss']
})
export class HamlahDetailsComponent implements OnInit {

  @Input()
  housingCategories: Lookup[];
  @Input()
  housingTypes: Lookup[];
  @Input()
  packageTypes: Lookup[];
  @Input()
  housingSites: Lookup[];

  @Input()
  card: ApplicantCard;

  @Input()
  currentLanguage: string;

  @Input()
  transportationTypes: Lookup[];

  constructor(private lookupsService: LookupService) {
  }

  ngOnInit(): void {
  }

  lookupService(): LookupService {
    return this.lookupsService;
  }

}
