import {Component, OnInit} from '@angular/core';
import {combineLatest} from "rxjs";
import {map} from "rxjs/operators";
import {I18nService} from "@dcc-commons-ng/services";
import {ActivatedRoute, Router} from "@angular/router";
import {ToastService} from "@shared/components/toast";
import {TranslateService} from "@ngx-translate/core";
import {AuthenticationService, NotificationService} from "@core/services";
import {LookupService} from "@core/utilities/lookup.service";
import {NotificationTemplate} from "@model/notification-template.model";
import {Lookup} from "@model/lookup.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {EAuthority} from "@shared/model";
import {noWhitespaceValidator} from "@core/utilities/custom-validators";

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
    this.templateForm.controls['title'].setValue(this.getnotificationContentForSelectedLang("ar").title);
    this.templateForm.controls['body'].setValue(this.getnotificationContentForSelectedLang("ar").body);
    this.templateForm.controls['actionLabel'].setValue(this.getnotificationContentForSelectedLang("ar").actionLabel);
  }

  getnotificationContentForSelectedLang(lang: string) {
    if (this.notificationTemplate?.notificationTemplateContents.length > 0) {
      let contents = this.notificationTemplate.notificationTemplateContents.filter(value => lang.toLowerCase().startsWith(value.lang.toLowerCase()));
      if (!contents) {
        return this.notificationTemplate.notificationTemplateContents[0];
      }
      return contents[0];
    } else {
      return null;
    }
  }

  getTempContentIndex(lang: string) {
    if (this.notificationTemplate?.notificationTemplateContents.length > 0) {
      let index = this.notificationTemplate.notificationTemplateContents.findIndex((value => lang.toLowerCase().startsWith(value.lang.toLowerCase())));
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
  }

  lookupService(): LookupService {
    return this.lookupsService;
  }

  back() {
    if (this.editable) {
      this.editable = false;
      this.updateForm();
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


  updateNotificationTemplate() {

    if (!this.editable) {
      this.editable = true;
      this.templateForm.controls['enabled'].enable();
    } else {
      Object.keys(this.templateForm.controls).forEach(field => {
        const control = this.templateForm.get(field);
        control.markAsTouched({onlySelf: true});
      });

      if (this.templateForm.invalid) {
        return;
      }
      let index = this.getTempContentIndex("ar");
      this.notificationTemplate.enabled = this.templateForm.controls['enabled'].value;
      this.notificationTemplate.notificationTemplateContents[index].title = this.templateForm.controls['title'].value;
      this.notificationTemplate.notificationTemplateContents[index].body = this.templateForm.controls['body'].value;
      this.notificationTemplate.notificationTemplateContents[index].actionLabel = this.templateForm.controls['actionLabel'].value;
      this.notificationService.updateNotificationTemplate(this.notificationTemplate).subscribe(res => {
        if (res.hasOwnProperty('errors') && res.errors) {
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
        }
      });
    }
  }
}
