import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {LangChangeEvent, TranslateService} from "@ngx-translate/core";
import {Lookup} from "@model/lookup.model";
import {FormArray, FormBuilder, FormGroup, ValidatorFn, Validators} from "@angular/forms";
import {NotificationService} from "@core/services";
import {LookupService} from "@core/utilities/lookup.service";
import {I18nService} from "@dcc-commons-ng/services";
import {NotificationTemplate} from "@model/notification-template.model";
import {ToastService} from "@shared/components/toast";

@Component({
  selector: 'app-user-defined-notification-add',
  templateUrl: './user-defined-notification-add.component.html',
  styleUrls: ['./user-defined-notification-add.component.scss']
})
export class UserDefinedNotificationAddComponent implements OnInit {
  notificationCategories: Lookup[] = [];
  localizedNotificationCategories: Lookup[] = [];
  notificationForm: FormGroup;
  languages: Lookup[] = [];
  translatedLanguages: Lookup[] = [];
  activeId;
  checkedCriteria: number = -1;
  checkedGender: number = -1;
  creationDate: Date = new Date();
  selectedLang: string = 'ar';
  notificationTemplate: NotificationTemplate;
  contentForm: FormGroup;

  constructor(private notificationService: NotificationService,
              private lookupsService: LookupService,
              private formBuilder: FormBuilder,
              private router: Router,
              private translate: TranslateService,
              private i18nService: I18nService,
              private toastr: ToastService) {
  }

  ngOnInit(): void {
    this.loadLookups();
    this.initForm();

    this.contentForm = this.formBuilder.group({
      contents: this.formBuilder.array([])
    });

  }

  addContent(language) {
    const contentForm = this.formBuilder.group({
      lang: language,
      body: ['', [Validators.minLength(3), Validators.maxLength(500),]],
      title: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(50)]],
    });
    this.contents.push(contentForm);
  }

  get contents() {
    return this.contentForm.controls["contents"] as FormArray;
  }

  get f() {
    return this.contentForm.controls;
  }

  loadLookups() {
    this.notificationService.findNotificationCategories().subscribe(result => {
      this.notificationCategories = result;
      this.localizedNotificationCategories = this.lookupsService.localizedItems(this.notificationCategories);
    });
    this.notificationService.findLanguages().subscribe(result => {
      this.languages = result;
      this.translatedLanguages = this.languages
        .filter(c =>
          this.currentLanguage.toLowerCase().substr(0, 2) === c.lang.toLowerCase().substr(0, 2)
        );
      this.activeId = 1;
      this.translatedLanguages.forEach(language => this.addContent(language.code));
    });

    this.translate.onLangChange.subscribe((event: LangChangeEvent) => {
      this.translatedLanguages = this.languages.filter(c =>
        event.lang.toLowerCase().substr(0, 2) === c.lang.toLowerCase().substr(0, 2));
    })
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  initForm() {
    this.notificationForm = this.formBuilder.group({
      sendingDate: {value: null, disabled: true},
      name: ['', [Validators.required, Validators.minLength(3)]],
      category: [null, Validators.required],
      severity: [null, Validators.required],
    });
  }

  setSelectedLang(lang: string) {
    this.selectedLang = lang;
  }

  get canAddUserDefinedNotification(): boolean {
    //TODO Update authorities
    // return this.authenticationService.hasAuthority(EAuthority.ADD_USER) || this.authenticationService.hasAuthority(EAuthority.EDIT_USER);
    return true;
  }

  lookupService(): LookupService {
    return this.lookupsService;
  }

  goBackToList() {
    this.router.navigate(['/user-defined-notification/list']);
  }

  saveAsDraft() {
    this.prepareNotificationTemplate();

    this.notificationService.createNotificationTemplate(this.notificationTemplate).subscribe(res => {
      if (res.hasOwnProperty('errors') && res.errors) {
        this.toastr.warning(this.translate.instant('general.dialog_form_error_text'), this.translate.instant('general.dialog_edit_title'));
        Object.keys(this.notificationForm.controls).forEach(field => {
          console.log('looking for validation errors for : ' + field);
          if (res.errors[field]) {
            const control = this.notificationForm.get(field);
            control.setErrors({invalid: res.errors[field].replace(/\{/, '').replace(/\}/, '')});
            control.markAsTouched({onlySelf: true});
          }
        });
      } else {
        this.toastr.success(this.translate.instant('general.dialog_edit_success_text'), this.translate.instant('general.dialog_edit_title'));
        this.router.navigate(['/user-defined-notification/list']);
      }
    });
  }

  prepareNotificationTemplate() {
    Object.keys(this.notificationForm.controls).forEach(field => {
      const control = this.notificationForm.get(field);
      control.markAsTouched({onlySelf: true});
    });

    if (this.notificationForm.invalid) {
      return;
    }

    this.notificationTemplate = new NotificationTemplate();

    this.notificationTemplate.nameCode = this.notificationForm.controls['name'].value;
    this.notificationTemplate.categoryCode = this.notificationForm.controls['category'].value;
    this.notificationTemplate.important = this.notificationForm.controls['severity'].value;
    this.notificationTemplate.statusCode = "DRAFT";
    this.notificationTemplate.typeCode = 'USER_DEFINED';

    this.notificationTemplate.notificationTemplateContents = this.contentForm.controls['contents'].value.filter(c => c.body !== '' || c.title !== '');
  }

  saveAndSend() {
    this.prepareNotificationTemplate();
    this.notificationService.createNotificationTemplate(this.notificationTemplate).subscribe(res => {
      if (res.hasOwnProperty('errors') && res.errors) {
        this.toastr.warning(this.translate.instant('general.dialog_form_error_text'), this.translate.instant('general.dialog_edit_title'));
        Object.keys(this.notificationForm.controls).forEach(field => {
          console.log('looking for validation errors for : ' + field);
          if (res.errors[field]) {
            const control = this.notificationForm.get(field);
            control.setErrors({invalid: res.errors[field].replace(/\{/, '').replace(/\}/, '')});
            control.markAsTouched({onlySelf: true});
          }
        });
      } else {
        this.notificationService.sendToAll(this.notificationTemplate).subscribe(res => {
          if (res.hasOwnProperty('errors') && res.errors) {
            this.toastr.warning(this.translate.instant('general.dialog_form_error_text'), this.translate.instant('general.dialog_edit_title'));
          } else {
            this.toastr.success(this.translate.instant('general.dialog_edit_success_text'), this.translate.instant('general.dialog_edit_title'));
            this.router.navigate(['/user-defined-notification/list']);
          }
        })
      }
    });
  }

}
