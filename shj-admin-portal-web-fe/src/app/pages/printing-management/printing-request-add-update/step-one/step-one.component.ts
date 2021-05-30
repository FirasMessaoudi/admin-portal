import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import {Subscription} from "rxjs";
import {Page} from "@shared/model";
import {CardService} from "@core/services";
import {Card} from "@model/card.model";

@Component({
  selector: 'app-step-one',
  templateUrl: './step-one.component.html',
  styleUrls: ['./step-one.component.scss']
})
export class StepOneComponent implements OnInit {
  closeResult = '';
  cards: Array<Card> = [];
  pageArray: Array<number>;
  page: Page;
  selectedCards: Array<Card> = [];
  addedCards: Array<Card> = [];

  @Output()
  public onAddCards: EventEmitter<any[]> = new EventEmitter<any[]>();

  private listSubscription: Subscription;
  private searchSubscription: Subscription;

  constructor(private modalService: NgbModal,
              private cardService: CardService) {
  }

  ngOnInit(): void {
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
    this.selectedCards = event.target.checked ? [...this.cards] : [];
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
    this.addedCards = this.addedCards.concat(this.selectedCards);
    this.onAddCards.emit(this.addedCards);
    this.cards = [];
    this.selectedCards = [];
    this.modalService.dismissAll();
  }
}
