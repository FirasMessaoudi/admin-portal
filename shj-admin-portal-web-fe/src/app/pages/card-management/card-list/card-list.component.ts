import {Component, OnInit} from '@angular/core';
import {I18nService} from "@dcc-commons-ng/services";
import {AuthenticationService} from "@core/services";
import {EAuthority, Page} from "@shared/model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Card} from "@model/card.model";
import {CardService} from "@core/services/card/card.service";

@Component({
  selector: 'app-card-list',
  templateUrl: './card-list.component.html',
  styleUrls: ['./card-list.component.scss']
})
export class CardListComponent implements OnInit {

  cards: Array<Card>;
  pageArray: Array<number>;
  page: Page;
  canAddCard: boolean;
  searchForm: FormGroup;
  constructor(private i18nService: I18nService,
              private formBuilder: FormBuilder,
              private authenticationService: AuthenticationService,
              private cardService: CardService) { }

  ngOnInit(): void {
    this.initForm();
    // TODO: read it from authentication
    this.canAddCard = true;
  }

  get f() {
    return this.searchForm.controls;
  }

  private initForm(): void {
    this.searchForm = this.formBuilder.group({
      ritualSeason: [null],
      ritualType: [null],
      hamlah: [null],
      motawef: [null],
      idNumber: [null],
      cardNumber: [null],
      cardStatus: [null],
      applicantIdStatus: [null],
      gender: [null],
      nationality: [null],
      passportNumber: [null],
      tafweejNumber: [null],
      idType: [null]
    });
  }

  pageCounter(i: number): Array<number> {
    return new Array(i);
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  search(): void {
    this.cardService.list(0).subscribe(data => {
      console.log('returned cards: ' + JSON.stringify(data));
    });
    // TODO: dummy data
    this.page = new Page();
    this.page.content = [{"id":1, "applicantRitual":{"id":1, "applicant":{"id":1, "gender":"M", "nationalityCode":{"id":1, "code":null, "label":"الهندية", "lang":null}, "idNumber":2367430624, "idNumberOriginal":"9674215489", "passportNumber": "5O85X8", "dateOfBirthGregorian":1110488400000, "dateOfBirthHijri":14260201, "fullNameAr": "زبير عبد المجيد خان", "fullNameEn": "Zubair Abd Almajid Khan", "fullNameOriginal": "xyz", "maritalStatus":1, "photo": null, "requestId":1, "status": 1, "relatives":[{"id": 1, "applicant":{"id":11, "gender":"F", "nationalityCode":{"id":1, "code":null, "label":"الهندية", "lang":null}, "idNumber":2367430625, "idNumberOriginal":"9674215495", "passportNumber": "5O85X9", "dateOfBirthGregorian":1110488400000, "dateOfBirthHijri":14380209, "fullNameAr": "فاطمة زبير عبد المجيد خان", "fullNameEn": "Fatima Zubair Abd Almajid Khan", "fullNameOriginal": "xyz", "maritalStatus":2, "photo": null, "requestId":1, "status": 1, "relatives":[]}, "relationshipCode":{"id":1, "code":null, "label":"ابنة", "lang":null}}]}, "hamlahPackage":{"id":1, "typeCode":{"id":1, "code":null, "label":"VIP", "lang":null}, "hamlahId":1, "campId":1, "price":6500, "departureCity":"Mombai", "countryId":1, "packageHousings":[{"id":1, "type":"مزدلفة", "code":"25475", "labelAr":"مخيم مشاعل النور", "labelEn":"", "validityStart":"01-04-2021", "validityEnd":"02-04-2021", "addressAr":"مزدلفة بجوار محطة القطار", "addressEn":"", "isDefault":true, "packageCaterings":[{"id":1, "type":"غداء", "meal":"", "descriptionAr":"بوفيه مفتوح", "descriptionEn":""}]}], "packageTransportations":[{"id":1, "type":"باص", "validityStart":"03-04-2021", "validityEnd":"04-04-2021", "locationFrom":"منى", "locationTo":"مزدلفة", "vehicleNumber":"ب ح ج 259"}]}, "hijriSeason":1442, "dateStartGregorian":null, "dateEndGregorian":null, "dateStartHijri":null, "dateEndHijri":null, "typeCode":{"id":1, "code":null, "label":"حج داخلي", "lang":null}, "visaNumber": "521458", "permitNumber": "365214", "insuranceNumber": "215478", "borderNumber": "321458"},"referenceNumber":452187, "batchId":1, "statusCode":null}];
    this.cards = this.page.content;
  }

  cancelSearch() {
    this.initForm();
  }

  loadPage(page: number) {

  }

  selectAllCards() {

  }

  get canSeeCardsList(): boolean {
    //TODO: change it to CARD_MANAGEMENT
    return this.authenticationService.hasAuthority(EAuthority.USER_MANAGEMENT);
  }

}
