import {Component, OnInit} from '@angular/core';
import {I18nService} from "@dcc-commons-ng/services";
import {AuthenticationService} from "@core/services";
import {EAuthority, Page} from "@shared/model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ApplicantCard} from "@model/card.model";
import {CardService} from "@core/services/card/card.service";
import {Subscription} from "rxjs";
import {Lookup} from "@model/lookup.model";
import {LookupService} from "@core/utilities/lookup.service";
import {ApplicantCardSearchCriteria} from "@model/applicant-card-search-criteria.model";

@Component({
  selector: 'app-card-list',
  templateUrl: './card-list.component.html',
  styleUrls: ['./card-list.component.scss']
})
export class CardListComponent implements OnInit {
  public isSearchbarCollapsed = false;
  cards: Array<ApplicantCard>;
  pageArray: Array<number>;
  page: Page;
  canAddCard: boolean;
  searchForm: FormGroup;
  ritualTypes: Lookup[];
  cardStatuses: Lookup[];

  private listSubscription: Subscription;
  private searchSubscription: Subscription;

  constructor(private i18nService: I18nService,
              private formBuilder: FormBuilder,
              private authenticationService: AuthenticationService,
              private cardService: CardService,
              private lookupsService: LookupService) {
  }

  ngOnInit(): void {
    this.initForm();
    this.loadLookups();

    // TODO: read it from authentication
    this.canAddCard = true;
  }

  loadLookups() {
    this.cardService.findRitualTypes().subscribe(result => {
      this.ritualTypes = result;
    });
    this.cardService.findCardStatuses().subscribe(result => {
      this.cardStatuses = result;
    });
  }

  ngOnDestroy() {
    if (this.listSubscription) {
      this.listSubscription.unsubscribe();
    }
    if (this.searchSubscription) {
      this.searchSubscription.unsubscribe();
    }
  }

  get f() {
    return this.searchForm.controls;
  }

  private initForm(): void {
    this.searchForm = this.formBuilder.group({
      ritualSeason: {value: null, disabled: true},
      ritualType: {value: null, disabled: true},
      hamlah: {value: null, disabled: true},
      motawef: {value: null, disabled: true},
      uin: [null],
      idNumber: [null],
      cardNumber: {value: null, disabled: true},
      cardStatus: {value: null, disabled: true},
      applicantIdStatus: {value: null, disabled: true},
      gender: {value: null, disabled: true},
      nationality: {value: null, disabled: true},
      passportNumber: [null],
      tafweejNumber: {value: null, disabled: true},
      idType: {value: null, disabled: true}
    });
  }

  pageCounter(i: number): Array<number> {
    return new Array(i);
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  lookupService(): LookupService {
    return this.lookupsService;
  }

/*
  this method is used to search applicant cards based upon user entered search criteria
  in applicant card management page
*/
  search(): void {


    this.searchSubscription = this.cardService.list(0, this.searchForm.value).subscribe(data => {
      this.cards = [];
      this.pageArray = [];
      this.page = data;
      if (this.page != null) {
        this.pageArray = Array.from(this.pageCounter(this.page.totalPages));
        this.cards = this.page.content;
      }
    });
  }

  cancelSearch() {
    this.initForm();
  }

  loadPage(page: number) {
    this.listSubscription = this.cardService.list(page, this.searchForm.value).subscribe(data => {
      this.page = data;
      if (this.page != null) {
        this.pageArray = Array.from(this.pageCounter(this.page.totalPages));
        this.cards = this.page.content;
      }
    })
  }

  selectAllCards() {

  }

  get canSeeCardsList(): boolean {
    //TODO: change it to CARD_MANAGEMENT
    return this.authenticationService.hasAuthority(EAuthority.USER_MANAGEMENT);
  }

}
