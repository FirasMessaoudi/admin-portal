import { Component, OnInit } from '@angular/core';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import {Subscription} from "rxjs";
import {Page} from "@shared/model";
import {ApplicantService} from "@core/services/applicant/applicant.service";
import {Applicant} from "@model/applicant.model";

@Component({
  selector: 'app-step-one',
  templateUrl: './step-one.component.html',
  styleUrls: ['./step-one.component.scss']
})
export class StepOneComponent implements OnInit {
  closeResult = '';
  applicants: Array<Applicant>;
  pageArray: Array<number>;
  page: Page;
  selectedApplicants: Number[] = [];
  addedApplicants: Number[] = [];
  private listSubscription: Subscription;
  private searchSubscription: Subscription;

  constructor(private modalService: NgbModal,
              private applicantService: ApplicantService) { }

  ngOnInit(): void {
    this.loadPage(0);
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
    this.listSubscription = this.applicantService.list(page).subscribe(data => {
      this.page = data;
      if (this.page != null) {
        this.pageArray = Array.from(this.pageCounter(this.page.totalPages));
        this.applicants = this.page.content;
      }
    })
  }

  pageCounter(i: number): Array<number> {
    return new Array(i);
  }

  open(content) {
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  search(): void {
    this.searchSubscription = this.applicantService.list(0).subscribe(data => {
      this.applicants = [];
      this.pageArray = [];
      this.page = data;
      if (this.page != null) {
        this.pageArray = Array.from(this.pageCounter(this.page.totalPages));
        this.applicants = this.page.content;
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
    this.modalService.open(content, { size: 'xl' });
  }

  selectAllApplicants(event) {
    this.selectedApplicants = event.target.checked ? this.applicants.map(applicant => applicant.id) : [];
  }

  selectOneApplicant(event, id) {
    const selectedIndex = this.selectedApplicants.indexOf(id);
    let newSelectedApplicants = [];

    if (selectedIndex === -1) {
      newSelectedApplicants = newSelectedApplicants.concat(this.selectedApplicants, id);
    } else if (selectedIndex === 0) {
      newSelectedApplicants = newSelectedApplicants.concat(this.selectedApplicants.slice(1));
    } else if (selectedIndex === this.selectedApplicants.length - 1) {
      newSelectedApplicants = newSelectedApplicants.concat(this.selectedApplicants.slice(0, -1));
    } else if (selectedIndex > 0) {
      newSelectedApplicants = newSelectedApplicants.concat(
        this.selectedApplicants.slice(0, selectedIndex),
        this.selectedApplicants.slice(selectedIndex + 1)
      );
    }
    this.selectedApplicants = newSelectedApplicants;
  }

  save() {
    this.addedApplicants = this.selectedApplicants;
    this.modalService.dismissAll();
    this.resetSelectedApplicants();
  }

  resetSelectedApplicants() {
    this.selectedApplicants = [];
  }
}
