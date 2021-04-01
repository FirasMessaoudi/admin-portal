import {Component, OnInit} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import {I18nService} from "@dcc-commons-ng/services";
import {AuthenticationService} from "@core/services";
import {EAuthority} from "@shared/model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-card-list',
  templateUrl: './card-list.component.html',
  styleUrls: ['./card-list.component.scss']
})
export class CardListComponent implements OnInit {

  canAddCard: boolean;
  searchForm: FormGroup;

  constructor(private translate: TranslateService,
              private i18nService: I18nService,
              private formBuilder: FormBuilder,
              private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    this.initForm();
  }

  get f() {
    return this.searchForm.controls;
  }

  private initForm(): void {
    this.searchForm = this.formBuilder.group({
      ritualSeason: [null],
      ritualType: [null],
      hamlah: [null],
      motawef: [null],
      idNumber: [null],
      cardNumber: [null],
      cardStatus: [null],
      applicantIdStatus: [null],
      gender: [null],
      nationality: [null],
      passportNumber: [null]
    });
  }

  search(): void {

  }

  cancelSearch() {
    this.initForm();
  }

  get canSeeCardsList(): boolean {
    //TODO: change it to CARD_MANAGEMENT
    return this.authenticationService.hasAuthority(EAuthority.USER_MANAGEMENT);
  }

}
