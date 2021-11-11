import {Component, OnInit} from '@angular/core';
import {I18nService} from "@dcc-commons-ng/services";
import {ActivatedRoute, Router} from "@angular/router";
import {ToastService} from "@shared/components/toast";
import {LangChangeEvent, TranslateService} from "@ngx-translate/core";
import {AuthenticationService, NotificationService} from "@core/services";
import {LookupService} from "@core/utilities/lookup.service";
import {Lookup} from "@model/lookup.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {NotificationTemplate} from "@model/notification-template.model";
import {combineLatest} from "rxjs";
import {map} from "rxjs/operators";
import {NotificationTemplateContent} from "@model/notification-template-content.model";

@Component({
  selector: 'app-user-defined-notification-details',
  templateUrl: './user-defined-notification-details.component.html',
  styleUrls: ['./user-defined-notification-details.component.scss']
})
export class UserDefinedNotificationDetailsComponent implements OnInit {
  notificationCategories: Lookup[] = [];
  localizedNotificationCategories: Lookup[] = [];
  notificationForm: FormGroup;
  languages: Lookup[] = [];
  translatedLanguages: Lookup[] = [];
  activeId;
  editMode: boolean;
  notificationTemplate: NotificationTemplate;
  selectedLang: string;
  notificationTemplateId: number;
  content: NotificationTemplateContent;
  notificationStatuses: Lookup[];

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
        // load user details
        this.notificationService.findUserDefinedNotificationTemplateById(this.notificationTemplateId).subscribe(data => {
          if (data && data.id) {
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

  loadLookups() {
    this.notificationService.findNotificationCategories().subscribe(result => {
      this.notificationCategories = result;
      this.localizedNotificationCategories = this.lookupsService.localizedItems(this.notificationCategories);
    });

    this.notificationService.findNotificationTemplateStatuses().subscribe(result => {
      this.notificationStatuses = result;
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

  lookupService(): LookupService {
    return this.lookupsService;
  }

  initForm() {
    this.notificationForm = this.formBuilder.group({
      creationDate: {value: null, disabled: true},
      sendingDate: {value: null, disabled: true},
      name: [''],
      category: [null],
      severity: [null],
      enabled: {value: false},
      userSpecific: {value: false},
      forceSending: {value: false},
      title: [''],
      body: ['']
    });
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

  updateForm() {
    this.notificationForm.controls['creationDate'].setValue(this.notificationTemplate.creationDate);
    this.notificationForm.controls['enabled'].setValue(this.notificationTemplate.enabled);
    this.notificationForm.controls['name'].setValue(this.notificationTemplate.nameCode);
    this.notificationForm.controls['title'].setValue(this.getNotificationContentForSelectedLang()?.title);
    this.notificationForm.controls['body'].setValue(this.getNotificationContentForSelectedLang()?.body);
  }

  get canSeeAddUpdateUserDefinedNotification(): boolean {
    //TODO Update authorities
    // return this.authenticationService.hasAuthority(EAuthority.ADD_USER) || this.authenticationService.hasAuthority(EAuthority.EDIT_USER);
    return true;
  }

  goBackToList() {
    this.router.navigate(['/user-defined-notification/list']);
  }

  backToReadOnlyMode() {
    this.editMode = false;
  }

  enableEditMode() {
    this.editMode = true;
  }

  setSelectedLang(lang: string) {
    let lastTemplateContentIndex = this.getTempContentIndex();
    this.addOrUpdateSelectedLangContent(lastTemplateContentIndex);
    this.selectedLang = lang;
    let templateContentIndex = this.getTempContentIndex();
    if (templateContentIndex == -1) {
      this.resetForm();
    } else if (templateContentIndex != -1) {
      this.notificationForm.controls['title'].setValue(this.getNotificationContentForSelectedLang()?.title);
      this.notificationForm.controls['body'].setValue(this.getNotificationContentForSelectedLang()?.body);
    }
  }

  resetForm() {
    this.notificationForm.controls['title'].setValue('');
    this.notificationForm.controls['body'].setValue('');
    this.notificationForm.controls['title'].setErrors(null);
    this.notificationForm.controls['body'].setErrors(null);
  }

  addOrUpdateSelectedLangContent(index: number) {
    if (index != -1) {
      this.content = this.notificationTemplate.notificationTemplateContents[index];
      this.content.title = this.notificationForm.controls['title'].value;
      this.content.body = this.notificationForm.controls['body'].value;
      this.notificationTemplate.notificationTemplateContents[index] = this.content;
    } else {
      this.content = new NotificationTemplateContent(this.selectedLang.toUpperCase(), '', '', '');
      this.content.title = this.notificationForm.controls['title'].value;
      this.content.body = this.notificationForm.controls['body'].value;
      if (this.content.title != '' && this.content.body != '')
        this.notificationTemplate.notificationTemplateContents.push(this.content);
    }
  }

  getTempContentIndex() {
    if (this.notificationTemplate?.notificationTemplateContents.length > 0) {
      return this.notificationTemplate.notificationTemplateContents.findIndex((value => this.selectedLang?.toLowerCase().startsWith(value.lang.toLowerCase())));
    } else {
      return -1;
    }
  }
}
