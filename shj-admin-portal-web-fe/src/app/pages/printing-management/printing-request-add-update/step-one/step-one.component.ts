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
              private printRequestStorage: PrintRequestStorage) {
  }

  ngOnInit(): void {
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

  loadPage(page: number) {
    this.listSubscription = this.cardService.listReadyToPrint(this.addedCards.map(card => card.id), page).subscribe(data => {
      this.page = data;
      if (this.page != null) {
        this.pageArray = Array.from(this.pageCounter(this.page.totalPages));
        this.cards = this.page.content;
      }
    })
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

  search(): void {
    this.searchSubscription = this.cardService.listReadyToPrint(this.addedCards.map(card => card.id), 0).subscribe(data => {
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
    return this.selectedCards.length === this.cards.length;
  }

  numberOfPages(){
    return Math.ceil(this.addedCards.length / this.addedCardsPageSize);
  };

  setCurrentPage(page: number) {
    this.addedCardsCurrentPage = page;
  }
}
