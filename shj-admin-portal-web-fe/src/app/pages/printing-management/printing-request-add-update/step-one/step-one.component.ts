import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {Subscription} from "rxjs";
import {Page} from "@shared/model";
import {CardService} from "@core/services";
import {ApplicantCard} from "@model/card.model";
import {CountryLookup} from "@model/country-lookup.model";
import {LookupService} from "@core/utilities/lookup.service";
import {Lookup} from "@model/lookup.model";
import {PrintService} from "@core/services/print/print.service";
import {PrintRequestStorage} from "@pages/printing-management/printing-request-add-update/print-request-storage";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-step-one',
  templateUrl: './step-one.component.html',
  styleUrls: ['./step-one.component.scss']
})
export class StepOneComponent implements OnInit {
  closeResult = '';
  cards: Array<ApplicantCard> = [];
  pageArray: Array<number>;
  page: Page;
  selectedCards: Array<ApplicantCard> = [];
  addedCards: Array<ApplicantCard> = [];
  addedCardsCurrentPage: number = 1;
  addedCardsPageSize: number = 10;
  nationalities: CountryLookup[];
  localizedNationalities: Lookup[];
  searchForm: FormGroup;

  @Output()
  public onAddCards: EventEmitter<any[]> = new EventEmitter<any[]>();

  @Output()
  public onSetPrintRequest: EventEmitter<any> = new EventEmitter<any>();

  private listSubscription: Subscription;
  private searchSubscription: Subscription;

  constructor(private modalService: NgbModal,
              private cardService: CardService,
              private printService: PrintService,
              private lookupsService: LookupService,
              private printRequestStorage: PrintRequestStorage,
              private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.initForm();
    this.printRequestStorage.storage = null;
    this.cardService.findCountries().subscribe(result => {
      this.nationalities = result;
      this.localizedNationalities = this.lookupsService.localizedItems(this.nationalities);
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

  private initForm(): void {
    this.searchForm = this.formBuilder.group({
      idNumber: [null],
      hamlahNumber: [null],
      motawefNumber: [null],
      passportNumber: [null],
      nationality: [null]
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
    this.searchSubscription = this.cardService.searchCardsToPrint(this.addedCards.map(card => card.id), pageNumber,
      this.searchForm.value.idNumber, this.searchForm.value.hamlahNumber, this.searchForm.value.motawefNumber,
      this.searchForm.value.passportNumber, this.searchForm.value.nationality).subscribe(data => {
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
    this.modalService.open(content, {size: 'xl'});
  }

  selectAllCards(event) {
    this.selectedCards = event.target.checked ?
      [...new Set([...this.cards, ...this.selectedCards])] : []
  }

  selectOneCard(event, id) {
    const selectedIndex = this.cards.findIndex(card => card.id === id);

    if (event.target.checked) {
      this.selectedCards.push(this.cards[selectedIndex]);
    } else {
      this.selectedCards.splice(this.selectedCards.findIndex(card => card.id === id), 1);
    }
  }

  save() {
    this.printService.save(this.addedCards.map(card => card.id)).subscribe(
      res => {
        this.printService.find(res.id).subscribe(
          result => {
            this.printRequestStorage.storage = result;
            this.onSetPrintRequest.emit(result);
          }
        );
      }
    );
  }

  addCards() {
    this.addedCards = [...this.addedCards, ...this.selectedCards];
    this.onAddCards.emit(this.addedCards);
    this.cards = [];
    this.selectedCards = [];
    this.modalService.dismissAll();
  }

  lookupService(): LookupService {
    return this.lookupsService;
  }

  undoAddCard(cardId: number) {
    this.addedCards.splice(this.addedCards.findIndex(card => card.id === cardId), 1);
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

  setCurrentPage(page: number) {
    this.addedCardsCurrentPage = page;
  }

  getTotalPages(total, size): number {
    return Math.floor((total + size - 1) / size);
  }
}
