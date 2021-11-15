import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {LangChangeEvent, TranslateService} from "@ngx-translate/core";
import {Lookup} from "@model/lookup.model";
import {FormArray, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators} from "@angular/forms";
import {AuthenticationService, CardService, NotificationService} from "@core/services";
import {LookupService} from "@core/utilities/lookup.service";
import {I18nService} from "@dcc-commons-ng/services";
import {NotificationTemplate} from "@model/notification-template.model";
import {ToastService} from "@shared/components/toast";
import {NgbCalendar, NgbDate, NgbDateParserFormatter} from "@ng-bootstrap/ng-bootstrap";
import {DateFormatterService} from "@shared/modules/hijri-gregorian-datepicker/datepicker/date-formatter.service";
import {ConfirmDialogService} from "@shared/components/confirm-dialog";
import * as momentjs from 'moment';
import {CompanyLite} from "@model/company-lite.model";
import {PackageHousing} from "@model/package-housing.model";
import {EAuthority} from "@shared/model";
import {CategorizedNotificationVo} from "@model/categorized-notification-vo.model";

const moment = momentjs;

@Component({
  selector: 'app-user-defined-notification-add',
  templateUrl: './user-defined-notification-add.component.html',
  styleUrls: ['./user-defined-notification-add.component.scss']
})
export class UserDefinedNotificationAddComponent implements OnInit {
  USER_DEFINED: string = 'USER_DEFINED';
  DRAFT: string = 'DRAFT';
  CONFIRMED: string = 'CONFIRMED';

  notificationCategories: Lookup[] = [];
  localizedNotificationCategories: Lookup[] = [];
  notificationForm: FormGroup;
  categorizedApplicantsForm: FormGroup;
  contentForm: FormGroup;
  languages: Lookup[] = [];
  translatedLanguages: Lookup[] = [];
  activeId;
  checkedCriteria: number = 0;
  creationDate: Date = new Date();
  notificationTemplate: NotificationTemplate;
  selectedLang: string;
  today: NgbDate;
  notificationDate: NgbDate;
  isLoading: boolean;
  nationalities: Lookup[] = [];
  companies: CompanyLite[] = [];
  camps: PackageHousing[] = [];

  constructor(private notificationService: NotificationService,
              private authenticationService: AuthenticationService,
              private lookupsService: LookupService,
              private formBuilder: FormBuilder,
              private router: Router,
              private translate: TranslateService,
              private i18nService: I18nService,
              private calendar: NgbCalendar,
              private cardService: CardService,
              private dateFormatterService: DateFormatterService,
              private confirmDialogService: ConfirmDialogService,
              private toastr: ToastService,
              public formatter: NgbDateParserFormatter) {
    this.notificationDate = calendar.getToday();
    this.today = calendar.getToday();
  }

  ngOnInit(): void {
    this.loadLookups();
    this.initForm();
    this.initCategorizedApplicantsForm();

    this.translate.onLangChange.subscribe((event: LangChangeEvent) => {
      this.translatedLanguages = this.languages.filter(c =>
        event.lang.toLowerCase().substr(0, 2) === c.lang.toLowerCase().substr(0, 2));
    })

    this.notificationService.loadCompanies().subscribe(res => this.companies = res);

    this.notificationService.loadCamps().subscribe(res => this.camps = res);

    this.contentForm = this.formBuilder.group({
      contents: this.formBuilder.array([], this.atLeastOne(Validators.required))
    });
  }

  initForm() {
    this.notificationForm = this.formBuilder.group({
      description: ['', [Validators.minLength(3), Validators.maxLength(250),]],
      categoryCode: [null, Validators.required],
      important: [false, Validators.required],
      userSpecific: [false, Validators.required],
      forceSending: [false, Validators.required],
      enabled: [true, Validators.required]
    });
  }

  addContent(language) {
    const contentForm = this.formBuilder.group({
      lang: language,
      body: ['', [Validators.maxLength(500)]],
      title: ['', [Validators.maxLength(50)]],
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

    this.cardService.findCountries().subscribe(result => {
      this.nationalities = result;
    });
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  initCategorizedApplicantsForm() {
    this.categorizedApplicantsForm = this.formBuilder.group({
      camp: [null, Validators.required],
      company: [null, Validators.required],
      nationality: [null, Validators.required],
      minAge: [0, Validators.required],
      maxAge: [120, Validators.required],
      gender: [null, Validators.required]
    });
  }

  setSelectedLang(lang: string) {
    this.selectedLang = lang;
  }

  get canSeeAddUpdateUserDefinedNotification(): boolean {
    return this.authenticationService.hasAuthority(EAuthority.USER_DEFINED_NOTIFICATION_MANAGEMENT);
  }

  lookupService(): LookupService {
    return this.lookupsService;
  }

  goBackToList() {
    this.router.navigate(['/user-defined-notification/list']);
  }

  saveAsDraft() {
    const notificationTemplate = this.createNotificationTemplate();

    this.notificationService.saveAsDraft(notificationTemplate).subscribe(res => {
      if (res.hasOwnProperty('errors') && res.errors) {
        this.toastr.warning(this.translate.instant('general.dialog_form_error_text'), this.translate.instant("general.dialog_add_title"));
        Object.keys(this.notificationForm.controls).forEach(field => {
          console.log('looking for validation errors for : ' + field);
          if (res.errors[field]) {
            const control = this.notificationForm.get(field);
            control.setErrors({invalid: res.errors[field].replace(/\{/, '').replace(/\}/, '')});
            control.markAsTouched({onlySelf: true});
          }
        });
      } else {
        this.toastr.success(this.translate.instant("notification-management.saved_successfully"), this.translate.instant("general.dialog_add_title"));
        this.router.navigate(['/user-defined-notification/list']);
      }
    });
  }

  saveAndSendToAll() {
    this.isLoading = true;
    let notificationTemplate = this.createNotificationTemplate();
    notificationTemplate.statusCode = this.CONFIRMED;
    this.notificationService.saveAndSendToAll(notificationTemplate).subscribe(res => {
      this.isLoading = false;
      if (res.hasOwnProperty('errors') && res.errors) {
        this.toastr.warning(this.translate.instant('general.dialog_form_error_text'), this.translate.instant("general.dialog_add_title"));
        Object.keys(this.notificationForm.controls).forEach(field => {
          console.log('looking for validation errors for : ' + field);
          if (res.errors[field]) {
            const control = this.notificationForm.get(field);
            control.setErrors({invalid: res.errors[field].replace(/\{/, '').replace(/\}/, '')});
            control.markAsTouched({onlySelf: true});
          }
        });
      } else {
        this.toastr.success(this.translate.instant("notification-management.saved_successfully"), this.translate.instant("general.dialog_add_title"));
        this.router.navigate(['/user-defined-notification/list']);
      }
    });
  }

  validateInput(currentValue: NgbDate | null, input: string): NgbDate | null {
    const parsed = this.formatter.parse(input);
    return parsed && this.calendar.isValid(NgbDate.from(parsed)) ? NgbDate.from(parsed) : currentValue;
  }

  createNotificationTemplate(): NotificationTemplate {
    Object.keys(this.notificationForm.controls).forEach(field => {
      const control = this.notificationForm.get(field);
      control.markAsTouched({onlySelf: true});
    });

    if (this.notificationForm.invalid) {
      return;
    }

    let notificationTemplate = this.notificationForm.value;
    notificationTemplate.sendingDate = this.getSelectedDate(this.notificationDate);
    notificationTemplate.typeCode = this.USER_DEFINED;
    notificationTemplate.statusCode = this.DRAFT;
    notificationTemplate.notificationTemplateContents = this.contentForm.controls['contents'].value.filter(c => c.body !== '' || c.title !== '');
    return notificationTemplate;
  }

  getSelectedDate(selectedDate: NgbDate): string {
    let formattedDate = this.dateFormatterService.toString(selectedDate);
    return moment(formattedDate, 'DD/MM/YYYY').locale('en').format();
  }

  goBack() {
    this.confirmDialogService
      .confirm(this.translate.instant('notification-management.cancel_confirmation_text'),
        this.translate.instant('general.dialog_confirmation_title')).then(confirm => {
      if (confirm) {
        this.goBackToList()
      }
    });
  }

  onDateSelection(date: NgbDate) {
    this.notificationDate = date;
  }

  atLeastOne = (validator: ValidatorFn) => (group: FormGroup): ValidationErrors | null => {
    const hasAtLeastOne = group && group.controls && Object.keys(group.controls).some(k => !validator(group.controls[k]));
    return hasAtLeastOne ? null : {atLeastOne: true};
  };

  saveAndSend() {
    if (this.checkedCriteria === 0) this.saveAndSendToAll();
    else if (this.checkedCriteria === 1) this.saveAndSendToCategorizedApplicants(this.categorizedApplicantsForm.value);
    else if (this.checkedCriteria === 2) this.saveAndSendToSelectedApplicants();
  }

  saveAndSendToCategorizedApplicants(criteria) {
    this.isLoading = true;
    let notificationTemplate = this.createNotificationTemplate();
    notificationTemplate.statusCode = this.CONFIRMED;

    const categorizedNotificationVo = new CategorizedNotificationVo(notificationTemplate, criteria);

    this.notificationService.saveAndSendToCategorizedApplicants(categorizedNotificationVo).subscribe(res => {
      this.isLoading = false;
      if (res.hasOwnProperty('errors') && res.errors) {
        this.toastr.warning(this.translate.instant('general.dialog_form_error_text'), this.translate.instant("general.dialog_add_title"));
        Object.keys(this.notificationForm.controls).forEach(field => {
          console.log('looking for validation errors for : ' + field);
          if (res.errors[field]) {
            const control = this.notificationForm.get(field);
            control.setErrors({invalid: res.errors[field].replace(/\{/, '').replace(/\}/, '')});
            control.markAsTouched({onlySelf: true});
          }
        });
      } else {
        this.toastr.success(this.translate.instant("notification-management.saved_successfully"), this.translate.instant("general.dialog_add_title"));
        this.router.navigate(['/user-defined-notification/list']);
      }
    });
  }

  saveAndSendToSelectedApplicants() {

  }
}
