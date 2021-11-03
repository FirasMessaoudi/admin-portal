import {Component, OnInit} from '@angular/core';
import {combineLatest} from "rxjs";
import {map} from "rxjs/operators";
import {I18nService} from "@dcc-commons-ng/services";
import {ActivatedRoute, Router} from "@angular/router";
import {ToastService} from "@shared/components/toast";
import {LangChangeEvent, TranslateService} from "@ngx-translate/core";
import {AuthenticationService, NotificationService} from "@core/services";
import {LookupService} from "@core/utilities/lookup.service";
import {NotificationTemplate} from "@model/notification-template.model";
import {Lookup} from "@model/lookup.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {EAuthority} from "@shared/model";
import {noWhitespaceValidator} from "@core/utilities/custom-validators";
import {NotificationTemplateContent} from "@model/notification-template-content.model";

@Component({
  selector: 'app-system-defined-notification-details',
  templateUrl: './system-defined-notification-details.component.html',
  styleUrls: ['./system-defined-notification-details.component.scss']
})
export class SystemDefinedNotificationDetailsComponent implements OnInit {
  notificationTemplateId: number;
  isLoading: boolean;
  notificationTemplate: NotificationTemplate;
  notificationTemplateCategories: Lookup[] = [];
  notificationTemplateNames: Lookup[] = [];
  templateForm: FormGroup;
  editable: boolean;
  selectedLangTemplateContent: NotificationTemplateContent;
  languages: Lookup[] = [];
  translatedLanguages: Lookup[] = [];
  activeId;
  allParamsAreValid = true;
  selectedLang: string;

  constructor(private i18nService: I18nService,
              private route: ActivatedRoute,
              private router: Router,
              private toastr: ToastService,
              private translate: TranslateService,
              private authenticationService: AuthenticationService,
              private notificationService: NotificationService,
              private lookupsService: LookupService,
              private formBuilder: FormBuilder,
  ) {
  }


  ngOnInit(): void {
    combineLatest([this.route.params, this.route.queryParams]).pipe(map(results => ({
      params: results[0].id,
      qParams: results[1]
    }))).subscribe(results => {
      this.notificationTemplateId = +results.params; // (+) converts string 'id' to a number
      if (this.notificationTemplateId) {
        this.isLoading = true;
        // load user details
        this.notificationService.findNotificationTemplateById(this.notificationTemplateId).subscribe(data => {
          if (data && data.id) {
            this.isLoading = false;
            this.notificationTemplate = data;
            this.selectedLang = "ar";

            this.updateForm();
          } else {
            this.toastr.error(this.translate.instant('general.route_item_not_found', {itemId: this.notificationTemplateId}),
              this.translate.instant('general.dialog_error_title'));
            this.goBackToList();
          }
        });
      } else {
        this.toastr.error(this.translate.instant('general.route_id_param_not_found'),
          this.translate.instant('general.dialog_error_title'));
        this.goBackToList();
      }
    });
    this.loadLookups();
    this.initForm();
  }


  private initForm() {
    this.templateForm = this.formBuilder.group({
      body: ['', [Validators.required, noWhitespaceValidator, Validators.maxLength(500), Validators.minLength(3)]],
      title: ['', [Validators.required, noWhitespaceValidator, Validators.maxLength(50), Validators.minLength(10)]],
      actionLabel: ['', [Validators.maxLength(50), noWhitespaceValidator, Validators.minLength(5)]],
      enabled: [{value: '', disabled: true}]
    });
  }

  private updateForm() {
    this.templateForm.controls['enabled'].setValue(this.notificationTemplate.enabled);
    this.templateForm.controls['title'].setValue(this.getNotificationContentForSelectedLang()?.title);
    this.templateForm.controls['body'].setValue(this.getNotificationContentForSelectedLang()?.body);
    this.templateForm.controls['actionLabel'].setValue(this.getNotificationContentForSelectedLang()?.actionLabel);
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
    if (this.notificationTemplate?.notificationTemplateContents.length > 0) {
      let index = this.notificationTemplate.notificationTemplateContents.findIndex((value => this.selectedLang?.toLowerCase().startsWith(value.lang.toLowerCase())));
      return index;
    } else {
      return -1;
    }
  }


  get f() {
    return this.templateForm.controls;
  }


  get currentLanguage(): string {
    return this.i18nService.language;
  }

  get canSeeSystemDefinedNotificationDetails(): boolean {

    return this.authenticationService.hasAuthority(EAuthority.SYSTEM_DEFINED_NOTIFICATION_DETAILS);
  }

  goBackToList() {
    this.router.navigate(['/notification/list']);
  }


  loadLookups() {

    this.notificationService.findNotificationCategories().subscribe(result => {
      this.notificationTemplateCategories = result;
    });

    this.notificationService.findNotificationTemplateNames().subscribe(result => {
      this.notificationTemplateNames = result;
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

  lookupService(): LookupService {
    return this.lookupsService;
  }

  back() {
    if (this.editable) {
      this.editable = false;
      if (this.getTempContentIndex() == -1) {
        this.resetTemplateForm();
      } else {
        this.updateForm();
      }
      this.templateForm.controls['enabled'].disable();
    } else {
      this.goBackToList();
    }
  }

  copyParameter(text: any) {
    navigator.clipboard.writeText(text).then(() =>
      this.toastr.success(this.translate.instant('support.text-copied'), '')
    ).catch(e => console.error(e));
  }

  checkForContentParams() {
    let matchedParams = this.selectedLangTemplateContent.body.match(/\<(.*?)\>/g);
    if (matchedParams != null) {
      var userAddedParamters = matchedParams.map(function (v) {
        return v.trim().substring(1, v.trim().length - 1);
      });
      var templateParams = this.notificationTemplate.notificationTemplateParameters.map(function (el) {
        return el.parameterName;
      });
      for (let i = 0; i < userAddedParamters.length; i++) {
        if (templateParams.indexOf(userAddedParamters[i]) === -1) {
          this.allParamsAreValid = false;
          break;
        }
      }
    }
  }


  updateNotificationTemplate() {
    let templateContentIndex = this.getTempContentIndex();
    this.allParamsAreValid = true;
    if (!this.editable) {
      this.editable = true;
      this.templateForm.controls['enabled'].enable();
      if (templateContentIndex == -1) {
        this.selectedLangTemplateContent = new NotificationTemplateContent(this.selectedLang.toUpperCase(), '', '', '');
        this.resetTemplateForm();
      } else {
        this.selectedLangTemplateContent = this.notificationTemplate.notificationTemplateContents[templateContentIndex];
        this.updateForm();
      }
    } else {

      Object.keys(this.templateForm.controls).forEach(field => {
        const control = this.templateForm.get(field);
        control.markAsTouched({onlySelf: true});
        this.templateForm.controls['body'].setErrors(null);
      });

      if (this.templateForm.invalid) {
        return;
      }
      this.notificationTemplate.enabled = this.templateForm.controls['enabled'].value;

      this.addOrUpdateSelectedLangContent(templateContentIndex);
      //compare every matching if it is exist in list of template params
      this.checkForContentParams();
      if (!this.allParamsAreValid) {
        this.templateForm.controls['body'].markAsTouched({onlySelf: true});
        this.templateForm.controls['body'].setErrors({'invalidParams': true});
        return;
      }

      this.notificationService.updateNotificationTemplate(this.notificationTemplate).subscribe(res => {
        if (res.status == 558) {
          this.allParamsAreValid = false;
          this.templateForm.controls['body'].markAsTouched({onlySelf: true});
          this.templateForm.controls['body'].setErrors({'invalidParams': true});
        } else if (res.hasOwnProperty('errors') && res.errors) {
          this.toastr.warning(this.translate.instant('general.dialog_form_error_text'), this.translate.instant('general.dialog_edit_title'));
          Object.keys(this.templateForm.controls).forEach(field => {
            console.log('looking for validation errors for : ' + field);
            if (res.errors[field]) {
              const control = this.templateForm.get(field);
              control.setErrors({invalid: res.errors[field].replace(/\{/, '').replace(/\}/, '')});
              control.markAsTouched({onlySelf: true});
            }
          });
        } else {
          this.toastr.success(this.translate.instant('general.dialog_edit_success_text'), this.translate.instant('general.dialog_edit_title'));
          this.editable = false;
          this.templateForm.controls['enabled'].disable();
        }
      });
    }
  }

  setSelectedLang(lang: string) {
    let lastTemplateContentIndex = this.getTempContentIndex();
    this.addOrUpdateSelectedLangContent(lastTemplateContentIndex);
    this.selectedLang = lang;
    let templateContentIndex = this.getTempContentIndex();
    if (templateContentIndex == -1) {
      this.resetTemplateForm();
    } else if (templateContentIndex != -1) {


      this.templateForm.controls['title'].setValue(this.getNotificationContentForSelectedLang()?.title);
      this.templateForm.controls['body'].setValue(this.getNotificationContentForSelectedLang()?.body);
      this.templateForm.controls['actionLabel'].setValue(this.getNotificationContentForSelectedLang()?.actionLabel);

    }
  }

  addOrUpdateSelectedLangContent(index: number) {

    if (index != -1) {
      this.selectedLangTemplateContent = this.notificationTemplate.notificationTemplateContents[index];
      this.selectedLangTemplateContent.title = this.templateForm.controls['title'].value;
      this.selectedLangTemplateContent.body = this.templateForm.controls['body'].value;
      this.selectedLangTemplateContent.actionLabel = this.templateForm.controls['actionLabel'].value;
      this.notificationTemplate.notificationTemplateContents[index] = this.selectedLangTemplateContent;
    } else {
      this.selectedLangTemplateContent = new NotificationTemplateContent(this.selectedLang.toUpperCase(), '', '', '');
      this.selectedLangTemplateContent.title = this.templateForm.controls['title'].value;
      this.selectedLangTemplateContent.body = this.templateForm.controls['body'].value;
      this.selectedLangTemplateContent.actionLabel = this.templateForm.controls['actionLabel'].value;
      if (this.selectedLangTemplateContent.title != '' && this.selectedLangTemplateContent.actionLabel != '' && this.selectedLangTemplateContent.body != '')
        this.notificationTemplate.notificationTemplateContents.push(this.selectedLangTemplateContent);

    }
  }

  resetTemplateForm() {
    this.templateForm.controls['title'].setValue('');
    this.templateForm.controls['body'].setValue('');
    this.templateForm.controls['actionLabel'].setValue('');
    this.templateForm.controls['body'].setErrors(null);
    this.templateForm.controls['actionLabel'].setErrors(null);
    this.templateForm.controls['title'].setErrors(null);
  }
}
