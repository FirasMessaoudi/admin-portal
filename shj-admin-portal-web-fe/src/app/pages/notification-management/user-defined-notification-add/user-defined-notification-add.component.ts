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
import {ModalDismissReasons, NgbCalendar, NgbDate, NgbDateParserFormatter, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {DateFormatterService} from "@shared/modules/hijri-gregorian-datepicker/datepicker/date-formatter.service";
import {ConfirmDialogService} from "@shared/components/confirm-dialog";
import * as momentjs from 'moment';
import {CompanyLite} from "@model/company-lite.model";
import {PackageHousing} from "@model/package-housing.model";
import {EAuthority, Page} from "@shared/model";
import {CategorizedNotificationVo} from "@model/categorized-notification-vo.model";
import {Applicant} from "@model/applicant.model";
import {Subscription} from "rxjs";
import {ApplicantService} from "@core/services/applicant/applicant.service";

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

  closeResult = '';
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

  private listSubscription: Subscription;
  private searchSubscription: Subscription;

  constructor(private notificationService: NotificationService,
              private authenticationService: AuthenticationService,
              private lookupsService: LookupService,
              private formBuilder: FormBuilder,
              private router: Router,
              private translate: TranslateService,
              private i18nService: I18nService,
              private calendar: NgbCalendar,
              private cardService: CardService,
              private applicantService: ApplicantService,
              private dateFormatterService: DateFormatterService,
              private confirmDialogService: ConfirmDialogService,
              private toastr: ToastService,
              private modalService: NgbModal,
              public formatter: NgbDateParserFormatter) {
    this.notificationDate = calendar.getToday();
    this.today = calendar.getToday();
  }

  ngOnInit(): void {
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

    this.contentForm = this.formBuilder.group({
      contents: this.formBuilder.array([], this.atLeastOne(Validators.required))
    });
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
      description: ['', [Validators.minLength(3), Validators.maxLength(250),]],
      categoryCode: [null, Validators.required],
      important: [false, Validators.required],
      userSpecific: [false, Validators.required],
      forceSending: [false, Validators.required],
      enabled: [true, Validators.required]
    });
  }

  initSearchForm(): void {
    this.searchForm = this.formBuilder.group({
      uin: [null],
      idNumber: [null],
      passportNumber: [null]
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
    else if (this.checkedCriteria === 2) this.saveAndSendToSelectedApplicants(this.addedApplicants.map(applicant => applicant.id));
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
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
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
    this.modalService.open(content, {size: 'xl'});
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
}
