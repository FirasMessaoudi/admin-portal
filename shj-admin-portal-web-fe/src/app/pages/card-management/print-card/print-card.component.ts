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
import { PersonType } from '@model/enum/person-type.enum';
import { StaffService } from '@core/services/staff/staff.service';
@Component({
  selector: 'app-print-card',
  templateUrl: './print-card.component.html',
  styleUrls: ['./print-card.component.scss'] 
})
export class PrintCardComponent implements OnInit { 

  public loading: boolean = false;
  uin:string;
  printDetails: PrintDetails;
  printInfo:PrintInfo;
  base64ImageFront:string;
  base64ImageBack:string;
  base64FullImageFrontPrinter:string;
  base64FullImageBackPrinter:string;
  base64PartialImageFrontPrinter:string;
  base64PartialImageBackPrinter:string;
  dualPrintingMode:boolean=true;
  showSpinner:boolean=false;
  personType:string;  
  personTypeCONST : PersonType= PersonType.APPLICANT;
  loadApplicantPerson:boolean=false;

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
    private cardService:CardService,
    private staffService:StaffService
  ) {    
  }

  ngOnInit(): void {    
    this.uin = this.route.snapshot.paramMap.get('id');
    this.personType = this.route.snapshot.paramMap.get('type'); 
    this.showSpinner=true;
    if(this.personType == this.personTypeCONST)
    {
      this.loadApplicantPerson =true;
      this.loadApplicantCardImages();
      
    }
    else 
    {
      this.loadStaffCardImages();
    }
    
  }   

  resolvePrintImages(result:any)
  {
    if(result && result.length>0)
    {
      if(result[0])
      {
        this.base64ImageFront=`data:image/bmp;base64,${result[0].badgeImage}`;
        this.base64FullImageFrontPrinter = result[0].badgeImage;
        this.base64PartialImageFrontPrinter = result[2].badgeImage;
      } 
      if(result[1])
      {
        this.base64ImageBack=`data:image/bmp;base64,${result[1].badgeImage}`;
        this.base64FullImageBackPrinter = result[1].badgeImage;
        this.base64PartialImageBackPrinter = result[3].badgeImage;
      }
      
    }
  }

  loadStaffCardImages()
  {
  this.loading =true;
   this.staffService.getStaffPreviewBadge(this.uin).subscribe(result =>
     {
       this.loading =false;
        if(result && result.length>0) 
       {
        this.base64ImageFront=`data:image/bmp;base64,${result[0].badgeImage}`;   
        this.base64ImageBack=`data:image/bmp;base64,${result[1].badgeImage}`;
       }
       
     });
  }

 loadApplicantCardImages()
 {
  this.loading =true;
  this.applicantService.getApplicantPreview(this.uin).subscribe(result =>
    {
       this.loading=false;
       if(result && result.length>0) 
       {
        this.base64ImageFront=`data:image/bmp;base64,${result[0].badgeImage}`;   
        this.base64ImageBack=`data:image/bmp;base64,${result[1].badgeImage}`;
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
     
      if(this.personType == this.personTypeCONST)
      {
        this.loading =true;
        this.applicantService.getApplicantAll(this.uin).subscribe(result =>
          {
             this.showSpinner=false;
             if(result && result.length>0) 
             { 
              this.loading =false;             
              let printDetails : PrintDetails = { 
                sessionId:`${this.uin}${Math.floor(Math.random() * 11)}`,
                imageBase64String:result[0].badgeImage,
                backImageBase64String:result[1].badgeImage,
                isDualSide:true
              };              
              this.printCard(printDetails);
             }
               
          }); 

      }
      else
      {
        this.loading =true;
        this.staffService.getStaffAllBadge(this.uin).subscribe(result =>
          {
             this.showSpinner=false;
             this.loading =false;
             if(result && result.length>0) 
             {              
              let printDetails : PrintDetails = { 
                sessionId:`${this.uin}${Math.floor(Math.random() * 11)}`,
                imageBase64String:result[0].badgeImage,
                backImageBase64String:result[1].badgeImage,
                isDualSide:true
              };              
              this.printCard(printDetails);
             }
               
          }); 

      }

          
      
    }, (reason) => {
      console.log("B");;
    });
  }
  printImageOnPrintedDesign(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {      
     
      if(this.personType == this.personTypeCONST)
      {
        this.loading =true;
        this.applicantService.getApplicantPrePrinted(this.uin).subscribe(result =>
          {
             this.showSpinner=false;
             this.loading =false;
             if(result && result.length>0) 
             {             
              let printDetails : PrintDetails = { 
                sessionId:`${this.uin}${Math.floor(Math.random() * 11)}`,
                imageBase64String:result[0].badgeImage,
                backImageBase64String:result[1].badgeImage,
                isDualSide:this.dualPrintingMode
              };              
              this.printCard(printDetails);
             }
               
          }); 

      }
      else
      {
        this.loading =true;
        this.staffService.getStaffPrePrintedBadge(this.uin).subscribe(result =>
          {
             this.showSpinner=false;
             this.loading =false;
             if(result && result.length>0) 
             {              
              let printDetails : PrintDetails = { 
                sessionId:`${this.uin}${Math.floor(Math.random() * 11)}`,
                imageBase64String:result[0].badgeImage,
                backImageBase64String:null,
                isDualSide:false
              };              
              this.printCard(printDetails);
             }
               
          }); 


      } 

    }, (reason) => {
      console.log("D");
    });
  }    
  printCard(printRequest:PrintDetails) 
  {    
     const headers = { 'content-type': 'application/json'};       
     const body=JSON.stringify(printRequest);      
     this.cardService.sendPrintRequestToPrinter(body,headers).subscribe(data =>
      {        
        this.toastr.success(this.translate.instant('printing-management.print_request_done_successfully'), this.translate.instant('printing-management.title'));
    });
    
  }
 
}
