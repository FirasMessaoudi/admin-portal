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

@Component({
  selector: 'app-print-card',
  templateUrl: './print-card.component.html',
  styleUrls: ['./print-card.component.scss'] 
})
export class PrintCardComponent implements OnInit { 

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
    private http: HttpClient
  ) {    
  }

  ngOnInit(): void {    
    
  }   

  navigateToList() {
    this.router.navigate(['/cards/list']);
  }

  goBack() {
    this.navigationService.back();
  }  

  printFullImage(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      console.log("A");     
      this.http.get<any>('http://localhost:5001/weatherforecast').subscribe((result) =>{
        
      });
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
