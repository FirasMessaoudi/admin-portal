import {Component, OnDestroy, OnInit} from '@angular/core';
import {I18nService} from '@dcc-commons-ng/services';
import {AuthenticationService} from '@core/services';
import {EAuthority, Page} from '@shared/model';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ApplicantCard} from '@model/applicant-card.model';
import {CardService} from '@core/services/card/card.service';
import {Subscription} from 'rxjs';
import {Lookup} from '@model/lookup.model';
import {LookupService} from '@core/utilities/lookup.service';
import {NavigationService} from '@core/utilities/navigation.service';
import {StaffPrintService} from '@core/services/printing/staff-print.service';

@Component({
  selector: 'app-card-list',
  templateUrl: './card-list.component.html',
  styleUrls: ['./card-list.component.scss'],
})
export class CardListComponent implements OnInit, OnDestroy {
  public isSearchbarCollapsed = true;
  cards: Array<ApplicantCard>;
  pageArray: Array<number>;
  page: Page;
  searchForm: FormGroup;
  ritualTypes: Lookup[] = [];
  cardStatuses: Lookup[] = [];
  nationalities: Lookup[] = [];
  ritualSeasons: any[] = [];
  digitalIdStatuses: Lookup[] = [];
  masterSelected: boolean;
  private listSubscription: Subscription;
  private searchSubscription: Subscription;

  constructor(
    private i18nService: I18nService,
    private formBuilder: FormBuilder,
    private navigationService: NavigationService,
    private cardService: CardService,
    private printService: StaffPrintService,
    private lookupsService: LookupService,
    private authenticationService: AuthenticationService
  ) {}

  ngOnInit(): void {
    this.initForm();
    this.loadLookups();
    this.masterSelected = false;
    this.loadPage(0);
  }

  loadLookups() {
    this.cardService.findRitualTypes().subscribe((result) => {
      this.ritualTypes = result;
    });
    this.cardService.findCardStatuses().subscribe((result) => {
      this.cardStatuses = result;
    });
    this.cardService.findCountries().subscribe((result) => {
      this.nationalities = result;
    });
    this.printService.findRitualSeasons().subscribe((result) => {
      this.ritualSeasons = result;
    });
    this.cardService.findDigitalIdStatuses().subscribe((result) => {
      this.digitalIdStatuses = result;
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
      ritualTypeCode: null,
      hamlah: { value: null, disabled: true },
      uin: '',
      idNumber: '',
      cardNumber: '',
      statusCode: null,
      digitalIdStatus: null,
      gender: null,
      nationalityCode: null,
      passportNumber: '',
      tafweejNumber: { value: null, disabled: true },
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
    this.searchSubscription = this.cardService
      .list(0, this.searchForm.value)
      .subscribe((data) => {
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
    this.listSubscription = this.cardService
      .list(page, this.searchForm.value)
      .subscribe((data) => {
        this.page = data;
        if (this.page != null) {
          this.pageArray = Array.from(this.pageCounter(this.page.totalPages));
          this.cards = this.page.content;
        }
      });
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
