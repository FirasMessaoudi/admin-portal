import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {LangChangeEvent, TranslateService} from "@ngx-translate/core";
import {Lookup} from "@model/lookup.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {NotificationService} from "@core/services";
import {LookupService} from "@core/utilities/lookup.service";
import {I18nService} from "@dcc-commons-ng/services";
import {NotificationTemplateContent} from "@model/notification-template-content.model";
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
  creationDate: Date;
  selectedLang: string;
  notificationTemplate: NotificationTemplate;
  selectedLangTemplateContent: NotificationTemplateContent;

  constructor(private notificationService: NotificationService,
              private lookupsService: LookupService,
              private formBuilder: FormBuilder,
              private router: Router,
              private translate: TranslateService,
              private i18nService: I18nService,
              private toastr: ToastService,
  ) {
  }

  ngOnInit(): void {
    this.creationDate = new Date();
    this.selectedLang = "ar";

    this.loadLookups();
    this.initForm();
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
      name: [''],
      category: [null],
      severity: [null],
      title: [''],
      body: ['']
    });
  }

  saveAndSend() {
  }

  saveAsDraft() {
  }

  setSelectedLang(lang: string) {
    let lastTemplateContentIndex = this.getTempContentIndex();
    this.addOrUpdateSelectedLangContent(lastTemplateContentIndex);
    this.selectedLang = lang;
    let templateContentIndex = this.getTempContentIndex();
    if (templateContentIndex == -1) {
      this.resetTemplateForm();
    } else if (templateContentIndex != -1) {
      this.notificationForm.controls['title'].setValue(this.getNotificationContentForSelectedLang()?.title);
      this.notificationForm.controls['body'].setValue(this.getNotificationContentForSelectedLang()?.body);
    }
  }

  addOrUpdateSelectedLangContent(index: number) {
    if (index != -1) {
      this.selectedLangTemplateContent = this.notificationTemplate.notificationTemplateContents[index];
      this.selectedLangTemplateContent.title = this.notificationForm.controls['title'].value;
      this.selectedLangTemplateContent.body = this.notificationForm.controls['body'].value;
      this.notificationTemplate.notificationTemplateContents[index] = this.selectedLangTemplateContent;
    } else {
      this.selectedLangTemplateContent.lang = this.selectedLang.toUpperCase();
      this.selectedLangTemplateContent.title = this.notificationForm.controls['title'].value;
      this.selectedLangTemplateContent.body = this.notificationForm.controls['body'].value;
      if (this.selectedLangTemplateContent.title != '' && this.selectedLangTemplateContent.body != '')
        this.notificationTemplate.notificationTemplateContents.push(this.selectedLangTemplateContent);
    }
  }

  resetTemplateForm() {
    this.notificationForm.controls['title'].setValue('');
    this.notificationForm.controls['body'].setValue('');
    this.notificationForm.controls['title'].setErrors(null);
    this.notificationForm.controls['body'].setErrors(null);
  }

  getNotificationContentForSelectedLang() {
    if (this.notificationTemplate?.notificationTemplateContents.length > 0) {
      let index = this.notificationTemplate.notificationTemplateContents.findIndex((value => this.selectedLang?.toLowerCase().startsWith(value.lang.toLowerCase())));
      if (index == -1) {
        return null;
      }
      return this.notificationTemplate.notificationTemplateContents[index];
    } else {
      return null;
    }
  }

  getTempContentIndex() {
    if (this.notificationTemplate?.notificationTemplateContents?.length > 0) {
      return this.notificationTemplate.notificationTemplateContents.findIndex((value => this.selectedLang?.toLowerCase().startsWith(value.lang.toLowerCase())));
    } else {
      return -1;
    }
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

  submit() {
    Object.keys(this.notificationForm.controls).forEach(field => {
      const control = this.notificationForm.get(field);
      control.markAsTouched({onlySelf: true});
      this.notificationForm.controls['body'].setErrors(null);
    });

    let templateContentIndex = this.getTempContentIndex();
    if (templateContentIndex === -1) {
      this.selectedLangTemplateContent = new NotificationTemplateContent(this.selectedLang.toUpperCase(), '', '', '');
      this.resetTemplateForm();
    } else {
      this.selectedLangTemplateContent = this.notificationTemplate.notificationTemplateContents[templateContentIndex];
    }

    if (this.notificationForm.invalid) {
      return;
    }

    this.notificationTemplate = new NotificationTemplate();

    this.notificationTemplate.nameCode = this.notificationForm.controls['name'].value;
    this.notificationTemplate.categoryCode = this.notificationForm.controls['category'].value;
    this.notificationTemplate.important = this.notificationForm.controls['severity'].value;
    this.notificationTemplate.statusCode = "DRAFT";
    this.notificationTemplate.typeCode = 'USER_DEFINED';
    this.notificationTemplate.notificationTemplateContents = [];

    this.addOrUpdateSelectedLangContent(templateContentIndex);

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
}
