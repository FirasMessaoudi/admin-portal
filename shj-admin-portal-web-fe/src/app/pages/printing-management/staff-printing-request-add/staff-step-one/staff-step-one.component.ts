import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Page} from "@shared/model";
import {CountryLookup} from "@model/country-lookup.model";
import {Lookup} from "@model/lookup.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {CardService} from "@core/services";
import {ToastService} from "@shared/components/toast";
import {TranslateService} from "@ngx-translate/core";
import {LookupService} from "@core/utilities/lookup.service";
import {I18nService} from "@dcc-commons-ng/services";
import {NavigationService} from "@core/utilities/navigation.service";
import {CompanyLite} from "@model/company-lite.model";
import {StaffPrintService} from "@core/services/printing/staff-print.service";
import {Card} from "@model/card.model";

@Component({
  selector: 'app-staff-step-one',
  templateUrl: './staff-step-one.component.html',
  styleUrls: ['./staff-step-one.component.scss']
})
export class StaffStepOneComponent implements OnInit {

  closeResult = '';
  cards: Array<any> = [];
  pageArray: Array<number>;
  page: Page;
  selectedCards: Array<any> = [];
  addedCards: Array<any> = [];
  addedCardsCurrentPage: number = 1;
  addedCardsPageSize: number = 10;
  nationalities: CountryLookup[];
  localizedNationalities: Lookup[];
  searchForm: FormGroup;
  isAllSelected: boolean;
  isLoading: boolean;
  isSelectAllClicked: boolean;
  isSelectLoading: boolean;
  companyNames: CompanyLite[];
  seasonYear: number;
  ritualType: string;
  ritualForm: FormGroup;
  cardsToSend: Array<Card> = [];

  @Output()
  public onAddCards: EventEmitter<any[]> = new EventEmitter<any[]>();

  @Output()
  public onSetPrintRequest: EventEmitter<any> = new EventEmitter<any>();

  @Output()
  public onDeleteAllCards: EventEmitter<any> = new EventEmitter<any>();

  @Output()
  public onChangeLoading: EventEmitter<any> = new EventEmitter<any>();

  private listSubscription: Subscription;
  private searchSubscription: Subscription;
  ritualTypes: Lookup[] = [];
  ritualSeasons: any[] = [];
  ritualSeasonYear: number;


  constructor(private modalService: NgbModal,
              private cardService: CardService,
              private toastr: ToastService,
              private translate: TranslateService,
              private printService: StaffPrintService,
              private lookupsService: LookupService,
              private formBuilder: FormBuilder,
              private i18nService: I18nService,
              private navigationService: NavigationService
  ) {
  }

  ngOnInit(): void {
    this.ritualSeasonYear = parseInt(localStorage.getItem('seasonYear'));
    this.initForm();
    this.cardService.findCountries().subscribe(result => {
      this.nationalities = result;
      this.localizedNationalities = this.lookupsService.localizedItems(this.nationalities);
    });

    this.cardService.findCompanyNames().subscribe((result) => {
      this.companyNames = result;
    });

    this.printService.findRitualTypes().subscribe((result) => {
      this.ritualTypes = result;
    });

    this.printService.findRitualSeasons().subscribe((result) => {
      this.ritualSeasons = result;
      this.ritualSeasons = this.ritualSeasons.filter( season => season == this.ritualSeasonYear);
    });
  }

  /**
   * Reload nationalities to accept language change for example.
   */
  reloadNationalities() {
    this.localizedNationalities = this.lookupsService.localizedItems(this.nationalities);
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  ngOnDestroy() {
    if (this.listSubscription) {
      this.listSubscription.unsubscribe();
    }
    if (this.searchSubscription) {
      this.searchSubscription.unsubscribe();
    }
  }

  private initForm(): void {
    this.searchForm = this.formBuilder.group({
      uin: [null],
      nationality: [null],
      companyCode: [null]

    });
    this.ritualForm = this.formBuilder.group({
      seasonYear: [null, Validators.required],
      ritualCode: [null, Validators.required],
      description: ["", Validators.required]
    });


  }

  pageCounter(i: number): Array<number> {
    return new Array(i);
  }

  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  search(pageNumber: number): void {
    this.isLoading = true;
    this.searchSubscription = this.cardService.searchStaffCardsToPrint(this.searchForm.value.uin,
      this.searchForm.value.companyCode,
      this.searchForm.value.nationality, this.ritualForm.getRawValue().seasonYear, this.ritualForm.getRawValue().ritualCode, this.addedCards.map(card => card.id), pageNumber).subscribe(data => {
      this.isLoading = false;
      this.cards = [];
      this.pageArray = [];
      this.page = data;
      if (this.page != null) {
        this.pageArray = Array.from(this.pageCounter(this.page.totalPages));
        this.cards = this.page.content;
      }
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  openLg(content) {
    this.modalService.open(content, {
      ariaLabelledBy: 'modal-basic-title',
      centered: true,
      size: 'lg'
    }).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
      this.resetModal();
    });
  }

  selectCardsInThePage(event) {
    this.isSelectAllClicked = true;
    if (event.target.checked) {
      this.cards.forEach(card => {
        if (!this.selectedCards.map(c => c.id).includes(card.id)) {
          this.selectedCards.push(card);
        }
      })
    } else {
      this.cards.forEach(card => {
        this.selectedCards.splice(this.selectedCards.findIndex(c => c.id === card.id), 1);
      })
    }

    this.isAllSelected = this.selectedCards.length === this.page.totalElements;
  }

  selectAllCards() {
    this.isSelectLoading = true;
    this.searchSubscription = this.cardService.searchAllStaffCardsToPrint(this.searchForm.value.uin,
      this.searchForm.value.companyCode,
      this.searchForm.value.nationality, this.ritualForm.getRawValue().seasonYear, this.ritualForm.getRawValue().ritualCode, this.addedCards.map(card => card.id)).subscribe(data => {
      this.isSelectLoading = false;
      this.isSelectAllClicked = true;
      this.isAllSelected = true;
      this.selectedCards = data;
    });
  }

  deselectAllCards() {
    this.isAllSelected = false;
    this.selectedCards = [];
  }

  selectOneCard(event, id) {
    const selectedIndex = this.cards.findIndex(card => card.id === id);

    if (event.target.checked) {
      this.selectedCards.push(this.cards[selectedIndex]);
    } else {
      this.selectedCards.splice(this.selectedCards.findIndex(card => card.id === id), 1);
    }

    this.isAllSelected = this.selectedCards.length === this.page.totalElements;
  }


  create() {
    this.onChangeLoading.emit(true);
    this.mapApplicantCardToGeneralCard();
    this.printService.preapre(this.cardsToSend).subscribe(
      result => {
        if (result.hasOwnProperty("errors") && result.errors) {
          console.log("Error");
          this.toastr.warning(this.translate.instant("printing-management.dialog_confirm_request_error_text"), this.translate.instant("general.dialog_error_title"));
        } else {
          this.onChangeLoading.emit(false);
          let arrayOfDesc = this.ritualForm.value.description?.split("\n");
          result.description = arrayOfDesc.join(' ');
          this.onSetPrintRequest.emit(result);
        }
      }
    );
  }

  private mapApplicantCardToGeneralCard() {
    this.cardsToSend = [];
    this.addedCards.map(card => {
      let cardVO: Card = new Card();
      cardVO.id = card.id;
      cardVO.fullNameAr = card?.companyStaffDigitalId?.companyStaff?.fullNameAr;
      cardVO.fullNameEn = card?.companyStaffDigitalId?.companyStaff?.fullNameEn;
      cardVO.digitalId = card?.companyStaffDigitalId?.suin;
      cardVO.idNumber = card?.companyStaffDigitalId?.companyStaff?.idNumber;
      cardVO.passportNumber = card?.companyStaffDigitalId?.companyStaff?.passportNumber;
      cardVO.nationalityCode = card?.companyStaffDigitalId?.companyStaff?.nationalityCode;
      cardVO.referenceNumber = card.referenceNumber;
      cardVO.statusCode = card.statusCode;
      this.cardsToSend.push(cardVO);
    })
  }

  addCards() {
    this.addedCards = [...this.addedCards, ...this.selectedCards];
    this.onAddCards.emit(this.addedCards);
    this.cards = [];
    this.selectedCards = [];
    this.isSelectAllClicked = false;
    this.modalService.dismissAll();
    this.ritualForm.disable();
  }

  lookupService(): LookupService {
    return this.lookupsService;
  }

  undoAddCard(cardId: number) {
    this.addedCards.splice(this.addedCards.findIndex(card => card.id === cardId), 1);
    if (this.addedCardsCurrentPage !== 1 && this.addedCards.length % this.addedCardsPageSize === 0) this.addedCardsCurrentPage--;
    if (this.addedCards.length == 0)
      this.onDeleteAllCards.emit(false);
    this.ritualForm.enable();
  }

  isChecked(card) {
    return this.selectedCards.some(c => c.id === card.id);
  }

  isAllChecked() {
    if (this.cards.length > 0)
      return this.cards.map(c => c.id).every(id => this.selectedCards.map(c => c.id).includes(id));
  }

  numberOfPages() {
    return Math.ceil(this.addedCards.length / this.addedCardsPageSize);
  };

  numberOfSelectedCardsInCurrentPage(): number {
    let counter = 0;
    this.selectedCards.map(c => c.id).forEach(id => {
      if (this.cards.map(c => c.id).includes(id)) counter++
    });
    return counter;
  }

  setCurrentPage(page: number) {
    this.addedCardsCurrentPage = page;
  }

  getTotalPages(total, size): number {
    return Math.floor((total + size - 1) / size);
  }

  goBack() {
    this.navigationService.back();
  }

  splitString(str: string, separator: string): string[] {
    return str.split(separator);
  }

  loading(): boolean {
    return this.isLoading || this.isSelectLoading;
  }

  resetModal() {
    this.searchForm.reset()
    this.cards = [];
    this.selectedCards = [];
    this.cards = [];
    this.isSelectAllClicked = false;
    this.modalService.dismissAll();
  }

}
