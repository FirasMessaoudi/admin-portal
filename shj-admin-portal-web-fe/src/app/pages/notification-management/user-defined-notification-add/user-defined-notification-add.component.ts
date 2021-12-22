import {Component, OnInit, ViewChild} from '@angular/core';
import {Router} from "@angular/router";
import {LangChangeEvent, TranslateService} from "@ngx-translate/core";
import {Lookup} from "@model/lookup.model";
import {AbstractControl, FormArray, FormBuilder, FormGroup, ValidatorFn, Validators} from "@angular/forms";
import {AuthenticationService, CardService, NotificationService} from "@core/services";
import {LookupService} from "@core/utilities/lookup.service";
import {I18nService} from "@dcc-commons-ng/services";
import {NotificationTemplate} from "@model/notification-template.model";
import {ToastService} from "@shared/components/toast";
import {ModalDismissReasons, NgbDateStruct, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ConfirmDialogService} from "@shared/components/confirm-dialog";
import {CompanyLite} from "@model/company-lite.model";
import {PackageHousing} from "@model/package-housing.model";
import {EAuthority, Page} from "@shared/model";
import {CategorizedNotificationVo} from "@model/categorized-notification-vo.model";
import {Applicant} from "@model/applicant.model";
import {Subscription} from "rxjs";
import {ApplicantService} from "@core/services/applicant/applicant.service";
import {DateType} from "@shared/modules/hijri-gregorian-datepicker/datepicker/consts";
import {DateFormatterService} from "@shared/modules/hijri-gregorian-datepicker/datepicker/date-formatter.service";
import {
  HijriGregorianDatepickerComponent
} from "@shared/modules/hijri-gregorian-datepicker/datepicker/hijri-gregorian-datepicker.component";

function nonEmptyBody(c: AbstractControl): { [key: string]: any } | null {
  let title = c.get('title');
  let body = c.get('body');
  let lang = c.get('lang');
  if (title.pristine || body.pristine) {
    return null;
  }
  if (lang.value.toLowerCase() === 'ar' || lang.value.toLowerCase() === 'en') {
    if (title.value.trim().length === 0) {
      title.setErrors({'required': true});
    }
    if (body.value.trim().length === 0) {
      body.setErrors({'required': true});
    }
  }
  return null;
}

export function requiredArabicAndEnglishContent(): ValidatorFn {
  return (formArray: FormArray): { [key: string]: any } | null => {
    let valid: boolean = true;
    formArray.controls.filter(c => c.value.lang.toLowerCase() === 'ar' || c.value.lang.toLowerCase() === 'en')
      .forEach((contents: FormGroup) => {
        valid = valid && contents.value.title.length > 0 && contents.value.body.length > 0;
      });
    return valid ? null : {requiredContent: 'Arabic and english content required'}
  }
}

function invalidAgeRange(c: AbstractControl): { [key: string]: any } | null {
  let minAge = c.get('minAge');
  let maxAge = c.get('maxAge');
  if (minAge.value === null || maxAge.value === null) {
    return null;
  }
  if (minAge.pristine || maxAge.pristine) {
    return null;
  }
  if (minAge.value > maxAge.value) {
    c.setErrors({invalidAgeRange: 'Invalid age range'});
    return {invalidAgeRange: 'Invalid age range'};
  }
  return null;
}

@Component({
  selector: 'app-user-defined-notification-add',
  templateUrl: './user-defined-notification-add.component.html',
  styleUrls: ['./user-defined-notification-add.component.scss']
})
export class UserDefinedNotificationAddComponent implements OnInit {
  USER_DEFINED: string = 'USER_DEFINED';
  DRAFT: string = 'DRAFT';
  CONFIRMED: string = 'CONFIRMED';

  closeResult = '';
  notificationCategories: Lookup[] = [];
  localizedNotificationCategories: Lookup[] = [];
  notificationForm: FormGroup;
  categorizedApplicantsForm: FormGroup;
  languages: Lookup[] = [];
  translatedLanguages: Lookup[] = [];
  activeId;
  checkedCriteria: number = 0;
  creationDate: Date = new Date();
  notificationTemplate: NotificationTemplate;
  isLoading: boolean;
  nationalities: Lookup[] = [];
  companies: CompanyLite[] = [];
  camps: PackageHousing[] = [];
  searchForm: FormGroup;
  pageArray: Array<number>;
  page: Page;
  selectedApplicants: Array<Applicant> = [];
  applicants: Array<Applicant> = [];
  addedApplicants: Array<Applicant> = [];
  addedApplicantsCurrentPage: number = 1;
  addedApplicantsPageSize: number = 10;
  isSelectAllClicked: boolean;
  isSelectLoading: boolean;
  isAllSelected: boolean;

  selectedSendingDate: NgbDateStruct;
  minSendingDateGregorian: NgbDateStruct;
  minSendingDateHijri: NgbDateStruct;
  dateString: string;
  selectedDateType: any;

  @ViewChild('datePicker') datePicker: HijriGregorianDatepickerComponent;

  private listSubscription: Subscription;
  private searchSubscription: Subscription;

  constructor(private notificationService: NotificationService,
              private authenticationService: AuthenticationService,
              private lookupsService: LookupService,
              private formBuilder: FormBuilder,
              private router: Router,
              private translate: TranslateService,
              private i18nService: I18nService,
              private cardService: CardService,
              private applicantService: ApplicantService,
              private confirmDialogService: ConfirmDialogService,
              private toastr: ToastService,
              private modalService: NgbModal,
              private dateFormatterService: DateFormatterService) {
  }


  ngOnInit(): void {
    // calendar default;
    let toDayGregorian = this.dateFormatterService.todayGregorian();
    let toDayHijri = this.dateFormatterService.todayHijri();
    this.minSendingDateGregorian = {
      year: toDayGregorian.year,
      month: toDayGregorian.month,
      day: toDayGregorian.day
    };
    this.minSendingDateHijri = {
      year: toDayHijri.year,
      month: toDayHijri.month,
      day: toDayHijri.day
    };
    this.selectedDateType = DateType.Gregorian;

    this.loadLookups();
    this.initForm();
    this.initCategorizedApplicantsForm();
    this.initSearchForm();

    this.translate.onLangChange.subscribe((event: LangChangeEvent) => {
      this.translatedLanguages = this.languages.filter(c =>
        event.lang.toLowerCase().substr(0, 2) === c.lang.toLowerCase().substr(0, 2));
    })

    this.notificationService.loadCompanies().subscribe(res => this.companies = res);

    this.notificationService.loadCamps().subscribe(res => this.camps = res);
  }

  ngOnDestroy() {
    if (this.listSubscription) {
      this.listSubscription.unsubscribe();
    }
    if (this.searchSubscription) {
      this.searchSubscription.unsubscribe();
    }
  }

  initForm() {
    this.notificationForm = this.formBuilder.group({
      categoryCode: [null, Validators.required],
      important: false,
      enabled: true,
      userSpecific: false,
      forceSending: false,
      description: '',
      sendingDateGregorian: ['', Validators.compose([Validators.required])],
      sendingDateHijri: ['', Validators.compose([Validators.required])],
      sendingTime: [null, Validators.compose([Validators.required])],
      notificationTemplateContents: this.formBuilder.array([], requiredArabicAndEnglishContent())
    });
  }

  initSearchForm(): void {
    this.searchForm = this.formBuilder.group({
      uin: '',
      idNumber: '',
      passportNumber: ''
    });
  }

  addTemplateContents(language: string) {
    const content = this.formBuilder.group({
      lang: language,
      title: [''],
      body: ['']
    }, {validator: nonEmptyBody});
    this.notificationTemplateContents.push(content);
  }

  get notificationTemplateContents(): FormArray {
    return <FormArray>this.notificationForm.get('notificationTemplateContents');
  }

  get f() {
    return this.notificationForm.controls;
  }

  get cf() {
    return this.categorizedApplicantsForm.controls;
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
      this.translatedLanguages.forEach(lang => this.addTemplateContents(lang.code));
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
      camp: null,
      company: null,
      nationality: null,
      age: this.formBuilder.group({
        minAge: ['', [Validators.min(0), Validators.max(120)]],
        maxAge: ['', [Validators.min(0), Validators.max(120)]],
      }, {validator: invalidAgeRange}),
      gender: null
    });
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
    this.confirmDialogService.confirm(
      this.translate.instant('notification-management.save_changes_confirmation_text'),
      this.translate.instant('general.dialog_confirmation_title')).then(confirm => {
      if (confirm) {
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

  createNotificationTemplate(): NotificationTemplate {
    Object.keys(this.notificationForm.controls).forEach(field => {
      const control = this.notificationForm.get(field);
      control.markAsTouched({onlySelf: true});
    });

    if (this.notificationForm.invalid) {
      return;
    }

    let notificationTemplate = this.notificationForm.value;
    notificationTemplate.typeCode = this.USER_DEFINED;
    notificationTemplate.statusCode = this.DRAFT;

    let sendingDate = this.notificationForm.value.sendingDateGregorian;

    console.log(sendingDate);

    sendingDate.setHours(this.notificationForm.value.sendingTime.hour);
    sendingDate.setMinutes(this.notificationForm.value.sendingTime.minute);

    console.log(sendingDate);

    notificationTemplate.sendingDate = sendingDate;

    notificationTemplate.notificationTemplateContents = this.notificationForm.get('notificationTemplateContents')
      .value.filter(c => c.body.trim() !== '' || c.title.trim() !== '');
    notificationTemplate.notificationTemplateContents.forEach(c => {
      c.title = c.title.replace(/\s/g, " ").trim();
      c.body = c.body.replace(/\s/g, " ").trim();
    });
    console.log(notificationTemplate);
    return notificationTemplate;
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

  saveAndSend() {
    this.confirmDialogService.confirm(
      this.translate.instant('notification-management.save_changes_confirmation_text'),
      this.translate.instant('general.dialog_confirmation_title')).then(confirm => {
      if (confirm) {
        if (this.checkedCriteria === 0) {
          this.saveAndSendToAll();
        } else if (this.checkedCriteria === 1) {
          this.saveAndSendToCategorizedApplicants(this.categorizedApplicantsForm.value);
        } else if (this.checkedCriteria === 2) {
          this.saveAndSendToSelectedApplicants(this.addedApplicants.map(applicant => applicant.id));
        }
      }
    });
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

  saveAndSendToSelectedApplicants(selectedApplicants) {
    this.isLoading = true;
    let notificationTemplate = this.createNotificationTemplate();
    notificationTemplate.statusCode = this.CONFIRMED;

    this.notificationService.saveAndSendToSelectedApplicants(notificationTemplate, selectedApplicants).subscribe(res => {
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

  addApplicants() {
    this.addedApplicants = [...this.addedApplicants, ...this.selectedApplicants];
    this.applicants = [];
    this.selectedApplicants = [];
    this.isSelectAllClicked = false;
    this.modalService.dismissAll();
  }

  resetModal() {
    this.searchForm.reset()
    this.applicants = [];
    this.selectedApplicants = [];
    this.isSelectAllClicked = false;
    this.modalService.dismissAll();
  }

  search(pageNumber: number): void {
    this.isLoading = true;
    this.searchSubscription = this.applicantService.search(this.searchForm.value, this.addedApplicants.map(applicant => applicant.id), pageNumber)
      .subscribe(data => {
        this.isLoading = false;
        this.applicants = [];
        this.pageArray = [];
        this.page = data;
        if (this.page != null) {
          this.pageArray = Array.from(this.pageCounter(this.page.totalPages));
          this.applicants = this.page.content;
        }
      });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  open(content) {
    this.modalService.open(content, {
      ariaLabelledBy: 'modal-basic-title',
      centered: true,
      size: 'lg'
    }).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  pageCounter(i: number): Array<number> {
    return new Array(i);
  }

  loading(): boolean {
    return this.isLoading || this.isSelectLoading;
  }

  isChecked(card) {
    return this.selectedApplicants.some(c => c.id === card.id);
  }

  isAllChecked() {
    if (this.applicants.length > 0)
      return this.applicants.map(c => c.id).every(id => this.selectedApplicants.map(c => c.id).includes(id));
  }

  selectApplicantsInThePage(event) {
    this.isSelectAllClicked = true;
    if (event.target.checked) {
      this.applicants.forEach(card => {
        if (!this.selectedApplicants.map(c => c.id).includes(card.id)) {
          this.selectedApplicants.push(card);
        }
      })
    } else {
      this.applicants.forEach(card => {
        this.selectedApplicants.splice(this.selectedApplicants.findIndex(c => c.id === card.id), 1);
      })
    }

    this.isAllSelected = this.selectedApplicants.length === this.page.totalElements;
  }

  selectOne(event, id) {
    const selectedIndex = this.applicants.findIndex(card => card.id === id);

    if (event.target.checked) {
      this.selectedApplicants.push(this.applicants[selectedIndex]);
    } else {
      this.selectedApplicants.splice(this.selectedApplicants.findIndex(card => card.id === id), 1);
    }

    this.isAllSelected = this.selectedApplicants.length === this.page.totalElements;
  }

  openLg(content) {
    this.modalService.open(content, {
      ariaLabelledBy: 'modal-basic-title',
      centered: true,
      size: 'lg'
    }).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
      this.resetModal();
    });
  }

  undoAddApplicant(applicantId: number) {
    this.addedApplicants.splice(this.addedApplicants.findIndex(applicant => applicant.id === applicantId), 1);
    if (this.addedApplicantsCurrentPage !== 1 && this.addedApplicants.length % this.addedApplicantsPageSize === 0) this.addedApplicantsCurrentPage--;
  }

  setCurrentPage(page: number) {
    this.addedApplicantsCurrentPage = page;
  }

  getTotalPages(total, size): number {
    return Math.floor((total + size - 1) / size);
  }

  onSendingDateChange(event) {
    if (event) {
      let dateStruct = this.datePicker.selectedDateType == DateType.Gregorian ? this.dateFormatterService.toHijri(event) : this.dateFormatterService.toGregorian(event);
      let dateStructGreg = this.datePicker.selectedDateType == DateType.Gregorian ? event : this.dateFormatterService.toGregorian(event);
      let dateStructHijri = this.datePicker.selectedDateType == DateType.Gregorian ? this.dateFormatterService.toHijri(event) : event;
      this.dateString = this.dateFormatterService.toString(dateStruct);
      this.notificationForm.controls.sendingDateGregorian.setValue(this.dateFormatterService.toDate(dateStructGreg));
      this.notificationForm.controls.sendingDateHijri.setValue(this.dateFormatterService.toString(dateStructHijri).split('/').reverse().join(''));
    }
  }
}
