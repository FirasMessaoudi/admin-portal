import {Component, OnDestroy, OnInit} from '@angular/core';
import {ApplicantCard} from "@model/card.model";
import {EAuthority, Page} from "@shared/model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Lookup} from "@model/lookup.model";
import {Subscription} from "rxjs";
import {I18nService} from "@dcc-commons-ng/services";
import {NavigationService} from "@core/utilities/navigation.service";
import {AuthenticationService, CardService} from "@core/services";
import {LookupService} from "@core/utilities/lookup.service";
import {CompanyStaffCard} from "@model/staff-card.model";
import {CompanyLite} from "@model/company-lite.model";

@Component({
  selector: 'app-staff-card-list',
  templateUrl: './staff-card-list.component.html',
  styleUrls: ['./staff-card-list.component.scss']
})
export class StaffCardListComponent implements OnInit, OnDestroy {
  public isSearchbarCollapsed = true;
  cards: Array<CompanyStaffCard> = [];
  pageArray: Array<number> = [];
  page: Page;
  searchForm: FormGroup;
  ritualTypes: Lookup[] = [];
  cardStatuses: Lookup[] = [];
  companyNames: CompanyLite[] = [];
  ritualSeasons: any[] = [];
  masterSelected: boolean;
  private listSubscription: Subscription;
  private searchSubscription: Subscription;


  constructor(private i18nService: I18nService,
              private formBuilder: FormBuilder,
              private navigationService: NavigationService,
              private cardService: CardService,
              private lookupsService: LookupService,
              private authenticationService: AuthenticationService) {
  }

  ngOnInit(): void {
    this.initForm();
    this.loadLookups();
    this.masterSelected = false;
    this.loadPage(0);
  }

  loadLookups() {
    this.cardService.findRitualTypes().subscribe(result => {
     this.ritualTypes = result;
    });
    this.cardService.findCardStatuses().subscribe(result => {
      this.cardStatuses = result;
    });
    this.cardService.findRitualSeasons().subscribe((result) => {
      this.ritualSeasons = result;
    });

    this.cardService.findCompanyNames().subscribe((result) => {
      this.companyNames = result;
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
      ritualSeason: null,
      ritualType: null,
      companyCode: null,
      batchNumber: null,
      suin: '',
      cardNumber: '',
      cardStatus: null
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
    this.searchSubscription = this.cardService.staffCardlist(0, this.searchForm.value).subscribe(data => {
      this.cards = [];
      this.pageArray = [];
      this.page = data;
      if (this.page != null) {
        this.pageArray = Array.from(this.pageCounter(this.page.totalPages));
        this.cards = this.page.content;
      }
    });
  }

  loadPage(page: number) {
    this.listSubscription = this.cardService.staffCardlist(page, this.searchForm.value).subscribe(data => {
      this.page = data;
      if (this.page != null) {
        this.pageArray = Array.from(this.pageCounter(this.page.totalPages));
        this.cards = this.page.content;
      }
    })
  }

  checkUncheckAllCards() {
    for (var i = 0; i < this.page.content.length; i++) {
      this.page.content[i].isSelected = this.masterSelected;
    }
  }


  get canSeeCardsList(): boolean {
    return this.authenticationService.hasAuthority(EAuthority.CARD_MANAGEMENT);
  }

  get canAddCard(): boolean {
    return this.authenticationService.hasAuthority(EAuthority.ADD_CARD);
  }

  goBack() {
    this.navigationService.back();
  }
}
