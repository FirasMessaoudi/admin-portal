import {Component, OnInit} from '@angular/core';
import {I18nService} from "@dcc-commons-ng/services";
import {ActivatedRoute, Router} from "@angular/router";
import {ToastService} from "@shared/components/toast";
import {LangChangeEvent, TranslateService} from "@ngx-translate/core";
import {AuthenticationService, CardService, NotificationService} from "@core/services";
import {LookupService} from "@core/utilities/lookup.service";
import {Lookup} from "@model/lookup.model";
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NotificationTemplate} from "@model/notification-template.model";
import {combineLatest} from "rxjs";
import {map} from "rxjs/operators";
import {NotificationTemplateContent} from "@model/notification-template-content.model";
import {NgbCalendar, NgbDate, NgbDateStruct} from "@ng-bootstrap/ng-bootstrap";
import {CompanyLite} from "@model/company-lite.model";
import {PackageHousing} from "@model/package-housing.model";
import {DateType} from "@shared/modules/hijri-gregorian-datepicker/datepicker/consts";
import {DateFormatterService} from "@shared/modules/hijri-gregorian-datepicker/datepicker/date-formatter.service";
import {validateIsRequired} from "@pages/notification-management/notification-custom-validator";
import {controllers} from "chart.js";
import {ConfirmDialogService} from "@shared/components/confirm-dialog";

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
  notificationTemplateId: number;
  content: NotificationTemplateContent;
  notificationStatuses: Lookup[] = [];
  creationDate: Date = new Date();
  today: NgbDate;
  nationalities: Lookup[] = [];
  companies: CompanyLite[] = [];
  camps: PackageHousing[] = [];
  minSendingDateGregorian: NgbDateStruct;
  minSendingDateHijri: NgbDateStruct;
  selectedDateType: any;

  constructor(private i18nService: I18nService,
              private route: ActivatedRoute,
              private router: Router,
              private toastr: ToastService,
              private translate: TranslateService,
              private authenticationService: AuthenticationService,
              private notificationService: NotificationService,
              private lookupsService: LookupService,
              private formBuilder: FormBuilder,
              private cardService: CardService,
              private calendar: NgbCalendar,
              private dateFormatterService: DateFormatterService,
              private confirmDialogService: ConfirmDialogService,
  ) {
    this.today = calendar.getToday();
  }

  ngOnInit(): void {
    // calendar default;
    let todayGregorian = this.dateFormatterService.todayGregorian();
    let todayHijri = this.dateFormatterService.todayHijri();
    this.minSendingDateGregorian = todayGregorian;
    this.minSendingDateHijri = todayHijri;
    this.selectedDateType = DateType.Gregorian;

    this.initForm();
    combineLatest([this.route.params, this.route.queryParams]).pipe(map(results => ({
      params: results[0].id,
      qParams: results[1]
    }))).subscribe(results => {
      this.notificationTemplateId = +results.params; // (+) converts string 'id' to a number
      if (this.notificationTemplateId) {
        this.notificationService.findUserDefinedNotificationTemplateById(this.notificationTemplateId).subscribe(data => {
          if (data && data.id) {
            this.notificationTemplate = data;
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
      this.translatedLanguages.forEach(lang => this.addTemplateContents(lang.code));
      this.activeId = 1;
    });

    this.translate.onLangChange.subscribe((event: LangChangeEvent) => {
      this.translatedLanguages = this.languages.filter(c =>
        event.lang.toLowerCase().substr(0, 2) === c.lang.toLowerCase().substr(0, 2));
    });

    this.cardService.findCountries().subscribe(res => this.nationalities = res);
    this.notificationService.loadCompanies().subscribe(res => this.companies = res);
    this.notificationService.loadCamps().subscribe(res => this.camps = res);
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
      category: null,
      severity: null,
      enabled: false,
      userSpecific: false,
      forceSending: false,
      description: ['', Validators.required],
      notificationTemplateContents: this.formBuilder.array([])
    });
  }

  addTemplateContents(language: string) {
    const content = this.formBuilder.group({
      lang: language,
      title: '',
      body: ''
    }, {validator: validateIsRequired});
    this.notificationTemplateContents.push(content);
  }

  get f() {
    return this.notificationForm.controls;
  }

  get notificationTemplateContents(): FormArray {
    return <FormArray>this.notificationForm.get('notificationTemplateContents');
  }

  updateForm() {
    this.notificationForm.patchValue({
      creationDate: this.notificationTemplate?.creationDate,
      sendingDate: this.notificationTemplate?.sendingDate,
      category: this.notificationTemplate?.categoryCode,
      severity: this.notificationTemplate?.important,
      enabled: this.notificationTemplate?.enabled,
      userSpecific: this.notificationTemplate?.userSpecific,
      description: this.notificationTemplate?.description
    });
    this.notificationTemplate.notificationTemplateContents.forEach(c => {
      let content = this.notificationForm.controls.notificationTemplateContents['controls']
        .find(field => field['controls'].lang.value === c.lang);
      content.controls.title.setValue(c.title);
      content.controls.body.setValue(c.body);
    });
    this.checkFormValidity();
  }

  get canSeeAddUpdateUserDefinedNotification(): boolean {
    //TODO Update authorities
    // return this.authenticationService.hasAuthority(EAuthority.ADD_USER) || this.authenticationService.hasAuthority(EAuthority.EDIT_USER);
    return true;
  }

  goBackToList() {
    this.router.navigate(['/user-defined-notification/list']);
  }

  enableEditMode() {
    this.editMode = !this.editMode;
    this.updateForm();
  }

  checkContentIsValid(i): boolean {
    const localizedContent = this.notificationTemplate?.notificationTemplateContents[i];
    return !this.editMode && (localizedContent?.title?.length > 0 && localizedContent?.body?.length > 0);
  }

  checkFormValidity() {
    Object.keys(this.notificationForm.controls).forEach(field => {
      const control = this.notificationForm.get(field);
      control.markAsTouched({onlySelf: true});
    });

    Object.keys(this.notificationTemplateContents['controls'][0]['controls']).forEach(field => {
      this.notificationForm.controls.notificationTemplateContents['controls'].forEach(control => control.get(field).markAsTouched({onlySelf: true}))
    });

    if (this.notificationForm.controls.notificationTemplateContents['controls'].find(c => c['errors'])) {
      this.activeId = this.translatedLanguages
        .findIndex(lang => lang.code == this.notificationForm.controls.notificationTemplateContents['controls'].find(c => c['errors'])?.value.lang) + 1;
    }
  }

  buildStatusClass(status: any): string {
    switch (status) {
      case 'DRAFT':
        return 'warning';
      case 'CONFIRMED':
        return 'done';
      default:
        return 'new';
    }
  }

  getCamp(id: number, lang: string): string {
    const camp = this.camps.find(c => id === c.id);
    if (camp) {
      return lang === 'ar' ? camp.locationNameAr : camp.locationNameEn;
    }
    return '---';
  }

  getCompany(id: number, lang: string): string {
    const company = this.companies.find(c => id === c.id);
    if (company) {
      return lang === 'ar' ? company.labelAr : company.labelEn;
    }
    return '---';
  }


  cancel() {
    this.confirmDialogService
      .confirm(this.translate.instant('notification-management.cancel_confirmation_text'),
        this.translate.instant('general.dialog_confirmation_title')).then(confirm => {
      if (confirm) {
        this.goBackToList()
      }
    });
  }

}
