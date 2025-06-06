import {Component, OnInit, Optional, Self, ViewChild, ViewEncapsulation} from '@angular/core';
import {Router} from '@angular/router';
import {FormBuilder, FormGroup, NgControl, Validators} from '@angular/forms';
import {ReCaptcha2Component} from "ngx-captcha";
import {TranslateService} from "@ngx-translate/core";
import {I18nService} from "@dcc-commons-ng/services";
import {DEFAULT_MAX_USER_AGE, UserService} from "@core/services";
import {environment} from "@env/environment";
import {ToastService} from "@shared/components/toast/toast-service";
import {NgbDateStruct} from "@ng-bootstrap/ng-bootstrap";
import {DateType} from "@shared/modules/hijri-gregorian-datepicker/consts";
import {DateFormatterService} from "@shared/modules/hijri-gregorian-datepicker/date-formatter.service";
import {HijriGregorianDatepickerComponent} from "@shared/modules/hijri-gregorian-datepicker/datepicker/hijri-gregorian-datepicker.component";
import {DccValidators, IdType} from "@shared/validators";


@Component({
  selector: 'app-reset-password',
  encapsulation: ViewEncapsulation.None,
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss']
})
export class ResetPasswordComponent implements OnInit {

  error: string;
  resetPasswordForm: FormGroup;
  loading = false;
  recaptchaSiteKey: any;
  _minPickerDate:any;
  _maxPickerDate:any;
  @ViewChild('reCaptchaEl')
  captchaElem: ReCaptcha2Component;

  recaptcha: any = null;

  selectedDateOfBirth: NgbDateStruct;
  maxDateOfBirthGregorian: NgbDateStruct;
  maxDateOfBirthHijri: NgbDateStruct;
  dateString: string;
  selectedDateType: any;

  @ViewChild('datePicker') dateOfBirthPicker: HijriGregorianDatepickerComponent;

  constructor(@Self() @Optional() private controlContainer: NgControl,
              private router: Router,
              private formBuilder: FormBuilder,
              private toastr: ToastService,
              private translate: TranslateService,
              private i18nService: I18nService,
              private userService: UserService,
              private dateFormatterService: DateFormatterService) {
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  setLanguage(language: string) {
    this.i18nService.language = language;
  }

  ngOnInit() {
    // calendar default;
    let toDayGregorian = this.dateFormatterService.todayGregorian();
    let toDayHijri = this.dateFormatterService.todayHijri();
    this.maxDateOfBirthGregorian = {
      year: toDayGregorian.year - DEFAULT_MAX_USER_AGE,
      month: toDayGregorian.month,
      day: toDayGregorian.day
    };
    this.maxDateOfBirthHijri = {
      year: toDayHijri.year - DEFAULT_MAX_USER_AGE,
      month: toDayHijri.month,
      day: toDayHijri.day
    };
    this.selectedDateType = DateType.Gregorian;

    this.createForm();
    this.recaptchaSiteKey = environment.recaptchaSiteKey;
    this._minPickerDate = {
      year: new Date().getFullYear() - 100,
      month: new Date().getMonth() + 1,
      day: new Date().getDate()
    };
    this._maxPickerDate = {
      year:  new Date().getFullYear(),
      month: new Date().getMonth() + 1,
      day: new Date().getDate()
    };
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.resetPasswordForm.controls;
  }

  resetPassword() {
    // trigger all validations
    Object.keys(this.resetPasswordForm.controls).forEach(field => {
      const control = this.resetPasswordForm.get(field);
      control.markAsTouched({onlySelf: true});
    });

    // stop here if form is invalid
    if (this.resetPasswordForm.invalid) {
      return;
    }
    let user = {
      idNumber: this.resetPasswordForm.controls.idNumber.value,
      dateOfBirthGregorian: this.resetPasswordForm.controls.dateOfBirthGregorian.value,
      dateOfBirthHijri: this.resetPasswordForm.controls.dateOfBirthHijri.value
    }
    // everything is fine we move to recaptcha check
    // if we get a successful response from recaptcha, then we send the form
    this.userService.resetPassword(user, this.captchaElem.getCurrentResponse()).subscribe(response => {
      if (response) {
        this.toastr.warning(this.translate.instant('general.dialog_form_error_text'), this.translate.instant('reset-password.title'));
        this.captchaElem.reloadCaptcha();
        if (response.hasOwnProperty('errors') && response.errors) {
          Object.keys(this.resetPasswordForm.controls).forEach(field => {
            console.log('looking for validation errors for : ' + field);
            if (response.errors[field]) {
              const control = this.resetPasswordForm.get(field);
              control.setErrors({invalid: response.errors[field].replace(/\{/, '').replace(/\}/, '')});
              control.markAsTouched({onlySelf: true});
            }
          });
        }
      } else {
        this.toastr.success(this.translate.instant('general.dialog_edit_success_text'), this.translate.instant('reset-password.title'));
        this.router.navigate(['/login']);
      }
    });
  }

  private createForm() {
    this.resetPasswordForm = this.formBuilder.group({
      idNumber: ['', Validators.compose([Validators.required, DccValidators.ninOrIqama(IdType.NIN_OR_IQAMA)])],
      dateOfBirthGregorian: ['', Validators.compose([Validators.required])],
      dateOfBirthHijri: ['', Validators.compose([Validators.required])],
      recaptcha: ['']
    });
  }

  onCaptchaLoad() {
    console.log('captcha is loaded');
  }

  onCaptchaReady() {
    console.log('captcha is ready');
  }

  onCaptchaSuccess(captchaResponse: string): void {
    console.log(captchaResponse);
  }

  onDateOfBirthChange(event) {
    if(event) {
      let dateStruct = this.dateOfBirthPicker.selectedDateType == DateType.Gregorian ? this.dateFormatterService.toHijri(event) : this.dateFormatterService.toGregorian(event);
      let dateStructGreg = this.dateOfBirthPicker.selectedDateType == DateType.Gregorian ? event : this.dateFormatterService.toGregorian(event);
      let dateStructHijri = this.dateOfBirthPicker.selectedDateType == DateType.Gregorian ? this.dateFormatterService.toHijri(event) : event;
      this.dateString = this.dateFormatterService.toString(dateStruct);
      this.resetPasswordForm.controls.dateOfBirthGregorian.setValue(this.dateFormatterService.toDate(dateStructGreg));
      this.resetPasswordForm.controls.dateOfBirthHijri.setValue(this.dateFormatterService.toString(dateStructHijri).split('/').reverse().join(''));
    }
  }
}
