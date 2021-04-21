import {Component, OnInit} from '@angular/core';
import {Card} from "@model/card.model";
import {TranslateService} from "@ngx-translate/core";
import {I18nService} from "@dcc-commons-ng/services";
import {PackageCatering} from "@model/package-catering.model";

@Component({
  selector: 'app-card-details',
  templateUrl: './card-details.component.html',
  styleUrls: ['./card-details.component.scss']
})
export class CardDetailsComponent implements OnInit {

  card: Card;
  url: any = 'assets/images/default-avatar.svg';

  constructor(private translate: TranslateService,
              private i18nService: I18nService) { }

  ngOnInit(): void {
    //TODO: dummy data
    this.card = {"id":1, "applicantRitual":{"id":1, "applicant":{"id":1, "gender":"M", "nationalityCode":{"id":1, "code":null, "label":"الهندية", "lang":null}, "idNumber":2367430624, "idNumberOriginal":"9674215489", "passportNumber": "5O85X8", "dateOfBirthGregorian":1110488400000, "dateOfBirthHijri":14260201, "fullNameAr": "زبير عبد المجيد خان", "fullNameEn": "Zubair Abd Almajid Khan", "fullNameOriginal": "xyz", "maritalStatus":1, "photo": null, "requestId":1, "status": 1, "relatives":[{"id": 1, "applicant":{"id":11, "gender":"F", "nationalityCode":{"id":1, "code":null, "label":"الهندية", "lang":null}, "idNumber":2367430625, "idNumberOriginal":"9674215495", "passportNumber": "5O85X9", "dateOfBirthGregorian":1110488400000, "dateOfBirthHijri":14380209, "fullNameAr": "فاطمة زبير عبد المجيد خان", "fullNameEn": "Fatima Zubair Abd Almajid Khan", "fullNameOriginal": "xyz", "maritalStatus":2, "photo": null, "requestId":1, "status": 1, "relatives":[], "contacts":[]}, "relationshipCode":{"id":1, "code":null, "label":"ابنة", "lang":null}}], "contacts":[]}, "hamlahPackage":{"id":1, "typeCode":{"id":1, "code":null, "label":"VIP", "lang":null}, "hamlahId":1, "campId":1, "price":6500, "departureCity":"Mombai", "countryId":1, "packageHousings":[{"id":1, "type":"مزدلفة", "code":"25475", "labelAr":"مخيم مشاعل النور", "labelEn":"", "validityStart":"01-04-2021", "validityEnd":"02-04-2021", "addressAr":"مزدلفة بجوار محطة القطار", "addressEn":"", "isDefault":true, "packageCaterings":[{"id":1, "type":"غداء", "meal":"", "descriptionAr":"بوفيه مفتوح", "descriptionEn":""}]}], "packageTransportations":[{"id":1, "type":"باص", "validityStart":"03-04-2021", "validityEnd":"04-04-2021", "locationFrom":"منى", "locationTo":"مزدلفة", "vehicleNumber":"ب ح ج 259"}]}, "hijriSeason":1442, "dateStartGregorian":null, "dateEndGregorian":null, "dateStartHijri":null, "dateEndHijri":null, "typeCode":{"id":1, "code":null, "label":"حج داخلي", "lang":null}, "visaNumber": "521458", "permitNumber": "365214", "insuranceNumber": "215478", "borderNumber": "321458"},"referenceNumber":452187, "batchId":1, "statusCode":null};
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  packageCaterings(): PackageCatering[] {
    let packageCaterings: PackageCatering[] = [];
    this.card.applicantRitual.hamlahPackage.packageHousings.forEach(housing => {
      packageCaterings = packageCaterings.concat(housing.packageCaterings);
    });
    return packageCaterings;
  }

}
