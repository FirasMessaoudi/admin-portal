import {Component, OnInit} from '@angular/core';
import {Card} from "@model/card.model";
import {TranslateService} from "@ngx-translate/core";
import {I18nService} from "@dcc-commons-ng/services";

@Component({
  selector: 'app-card-details',
  templateUrl: './card-details.component.html',
  styleUrls: ['./card-details.component.scss']
})
export class CardDetailsComponent implements OnInit {

  card: Card;
  url: any = 'assets/images/default-avatar.png';

  constructor(private translate: TranslateService,
              private i18nService: I18nService) { }

  ngOnInit(): void {
    //TODO: dummy data
    this.card = {"id":1, "applicantRitual":{"id":1, "applicant":{"id":1, "gender":"M", "nationalityCode":{"id":1, "code":null, "label":"الهندية", "lang":null}, "idNumber":2367430861, "idNumberOriginal":"9674215489", "passportNumber": "5O85X8", "dateOfBirthGregorian":1110488400000, "dateOfBirthHijri":14260201, "fullNameAr": "زبير عبد المجيد خان", "fullNameEn": "Zubair Abd Almajid Khan", "fullNameOriginal": "xyz", "maritalStatus":1, "photo": null, "requestId":1, "status": 1}, "hamlahPackage":null, "hijriSeason":1442, "dateStartGregorian":null, "dateEndGregorian":null, "dateStartHijri":null, "dateEndHijri":null, "typeCode":{"id":1, "code":null, "label":"حج داخلي", "lang":null}, "visaNumber": "521458", "permitNumber": "365214", "borderNumber": "321458"},"referenceNumber":452187, "batchId":1, "statusCode":null};
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

}
