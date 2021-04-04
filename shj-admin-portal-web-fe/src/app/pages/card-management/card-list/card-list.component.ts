import {Component, OnInit} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import {I18nService} from "@dcc-commons-ng/services";
import {AuthenticationService} from "@core/services";
import {EAuthority, Page} from "@shared/model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Card} from "@model/card.model";

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

  constructor(private translate: TranslateService,
              private i18nService: I18nService,
              private formBuilder: FormBuilder,
              private authenticationService: AuthenticationService) { }

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

  }

  cancelSearch() {
    this.initForm();
  }

  loadPage(page: number) {

  }

  get canSeeCardsList(): boolean {
    //TODO: change it to CARD_MANAGEMENT
    return this.authenticationService.hasAuthority(EAuthority.USER_MANAGEMENT);
  }

}
