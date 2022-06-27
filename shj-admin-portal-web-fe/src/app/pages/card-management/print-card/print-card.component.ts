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
import { CardService } from '@core/services/card/card.service';
@Component({
  selector: 'app-print-card',
  templateUrl: './print-card.component.html',
  styleUrls: ['./print-card.component.scss'] 
})
export class PrintCardComponent implements OnInit { 

  cardNumber:string;
  printDetails: PrintDetails;
  printInfo:PrintInfo;
  base64ImageFront:string;
  base64ImageBack:string;
  base64ImageFrontPrinter:string;
  base64ImageBackPrinter:string;
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
    private cardService:CardService
  ) {    
  }

  ngOnInit(): void {    
    this.cardNumber = this.route.snapshot.paramMap.get('id');
    this.loadCardImages();
  }   

 loadCardImages()
 {
  this.applicantService.getApplicantBadge(this.cardNumber).subscribe(result =>
    {       

      if(result && result.length>0)
      {
        if(result[0])
        {
          this.base64ImageFront=`data:image/bmp;base64,${result[0].badgeImage}`;
          this.base64ImageFrontPrinter = result[0].badgeImage;
        } 
        if(result[1])
        {
          this.base64ImageBack=`data:image/bmp;base64,${result[1].badgeImage}`;
          this.base64ImageBackPrinter = result[1].badgeImage;
        }
        
      }
      
    });

 }

  navigateToList() {
    this.router.navigate(['/cards/list']);
  }

  goBack() {
    this.navigationService.back();
  }  

  printFullImage(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {   
      this.printCard();
    }, (reason) => {
      console.log("B");;
    });
  }
  printImageOnPrintedDesign(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.applicantService.getApplicantFullBadge(this.cardNumber).subscribe(
        result=>
        {
          if(result && result.length>0 && result.length==4)
          {            
            this.base64ImageFrontPrinter =  result[2].badgeImage;
            this.base64ImageBackPrinter =  result[3].badgeImage;
            this.printCard();
          }

        });
      
    }, (reason) => {
      console.log("D");
    });
  }  

  printCard()
  {
    const headers = { 'content-type': 'application/json'}; 
      this.printDetails = { 
        sessionId:`${this.cardNumber}${Math.random()}${Math.random()}${Math.random()}`,
        imageBase64String:this.base64ImageFrontPrinter,
        backImageBase64String:this.base64ImageBackPrinter,
        isDualSide:true
      };
      const body=JSON.stringify(this.printDetails);      
      this.cardService.sendPrintRequestToPrinter(body,headers).subscribe(data =>{});
  }
 
}
