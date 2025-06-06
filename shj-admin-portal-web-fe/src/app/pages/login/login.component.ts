import {Component, OnInit, ViewChild, ViewEncapsulation} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {finalize} from 'rxjs/operators';
import {AuthenticationService} from '@app/_core/services/authentication/authentication.service';
import {I18nService} from "@dcc-commons-ng/services";
import {ReCaptcha2Component, ReCaptchaV3Service} from "ngx-captcha";
import {environment} from "@env/environment";
import {DccValidators, IdType} from "@shared/validators";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {LangChangeEvent, TranslateService} from "@ngx-translate/core";
import {Title} from "@angular/platform-browser";

@Component({
  encapsulation: ViewEncapsulation.None,
  templateUrl: 'login.component.html',
  styleUrls: ['login.component.scss']
})
export class LoginComponent implements OnInit {
  returnUrl: string;

  error: any;
  loginForm: FormGroup;
  loading = false;
  showCaptcha = false;
  recaptchaSiteKey: any;

  @ViewChild('reCaptchaEl')
  captchaElem: ReCaptcha2Component;

  constructor(private modalService: NgbModal,
              private formBuilder: FormBuilder,
              private i18nService: I18nService,
              private route: ActivatedRoute,
              private router: Router,
              private reCaptchaV3Service: ReCaptchaV3Service,
              private authenticationService: AuthenticationService,
              private titleService: Title,
              private translate: TranslateService
  ) {
    // redirect to home if already logged in
    if (this.authenticationService.isAuthenticated()) {
      // FIXME the timer is expiring before the token expiry cookie
      // removing it for now to fix redirecting issue
      // this.router.navigate(['/']);
    }
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  setLanguage(language: string) {
    this.i18nService.language = language;
    this.translate.onLangChange.subscribe((event: LangChangeEvent) => {
      this.translate.get('general.app_title').subscribe((res: string) => {
        this.titleService.setTitle(res);
      });
    });
  }

  ngOnInit() {

    this.recaptchaSiteKey = environment.recaptchaSiteKey;
    this.authenticationService.updateSubject(null);
    this.modalService.dismissAll();
    this.createForm();

    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    this.titleService.setTitle(this.translate.instant('general.app_title'));

  }

  // convenience getter for easy access to form fields
  get f() {
    return this.loginForm.controls;
  }

  onSubmit() {

    // trigger all validations
    Object.keys(this.loginForm.controls).forEach(field => {
      const control = this.loginForm.get(field);
      control.markAsTouched({onlySelf: true});
    });
    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;
    this.authenticationService.login(this.loginForm.value.username, this.loginForm.value.password, null)
      .pipe(finalize(() => {

        this.loginForm.markAsPristine();
        this.loading = false;
      }))
      .subscribe(user => {
        console.log(user);
        if (user.passwordExpired) {
          console.log('redirect to change password page');
          this.router.navigate(['/change-password'], {replaceUrl: true});
        } else if (user.otpRequired) {
          console.log('redirect to otp page');
          this.authenticationService.updateOtpSubject(user);
          this.router.navigate(['/otp'], {replaceUrl: true});
        } else {
          console.log('redirect to / page');
          this.router.navigate(['/'], {replaceUrl: true});
        }
      }, error => {
        console.log(error);
        this.showCaptcha = (error.status == 555);
        this.error = error;
      });
  }

  private createForm() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.compose([Validators.required, DccValidators.ninOrIqama(IdType.NIN_OR_IQAMA)])],
      password: ['', Validators.required],
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
}
