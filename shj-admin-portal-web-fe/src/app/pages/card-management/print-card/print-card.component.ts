import { Component, Inject, OnInit, Renderer2 } from '@angular/core';
import { DOCUMENT } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { combineLatest } from 'rxjs';
import { TranslateService } from '@ngx-translate/core';
import { ToastService } from '@shared/components/toast';
import { I18nService } from '@dcc-commons-ng/services';
import { Marker } from '@model/marker.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConfirmDialogService } from '@shared/components/confirm-dialog';
import {NavigationService} from "@core/utilities/navigation.service";
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { HttpClient } from '@angular/common/http';
import { PrintDetails } from '@model/print-details.model';
import { ApplicantService } from '@core/services/applicant/applicant.service';
import { PrintInfo } from '@model//print-info.model';
@Component({
  selector: 'app-print-card',
  templateUrl: './print-card.component.html',
  styleUrls: ['./print-card.component.scss'] 
})
export class PrintCardComponent implements OnInit { 

  cardNumber:string;
  printDetails: PrintDetails;
  printInfo:PrintInfo;
  
  constructor(    
    private router: Router,
    private i18nService: I18nService,
    private translate: TranslateService,
    private toastr: ToastService,    
    private route: ActivatedRoute,
    private confirmDialogService: ConfirmDialogService,
    private modalService: NgbModal,
    private navigationService: NavigationService,      
    private formBuilder: FormBuilder,
    @Inject(DOCUMENT) private document: Document,
    private renderer2: Renderer2,
    private http: HttpClient,
    private applicantService: ApplicantService,
  ) {    
  }

  ngOnInit(): void {    
    this.cardNumber = this.route.snapshot.paramMap.get('id');
  }   

  navigateToList() {
    this.router.navigate(['/cards/list']);
  }

  goBack() {
    this.navigationService.back();
  }  

  printFullImage(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {    
    this.applicantService.getApplicantBadge(this.cardNumber).subscribe(result =>
    {           
      const headers = { 'content-type': 'application/json'};  
      const body=JSON.stringify(this.printDetails);
      console.log(body);
      //this.http.post('http://localhost:5000/printservice/print', body,{'headers':headers}).subscribe(data =>{});
    });
    console.log("Starting Print");

    }, (reason) => {
      console.log("B");;
    });
  }
  printImageOnPrintedDesign(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      console.log("C");
    }, (reason) => {
      console.log("D");
    });
  }

  print()
  {
     console.log('Print Command Send');
     

  }
 
}
