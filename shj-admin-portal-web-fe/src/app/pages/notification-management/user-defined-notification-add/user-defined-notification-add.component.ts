import {Component, OnInit, ViewChild} from '@angular/core';
import {Router} from '@angular/router';
import {LangChangeEvent, TranslateService} from '@ngx-translate/core';
import {Lookup} from '@model/lookup.model';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService, CardService, NotificationService,} from '@core/services';
import {LookupService} from '@core/utilities/lookup.service';
import {I18nService} from '@dcc-commons-ng/services';
import {NotificationTemplate} from '@model/notification-template.model';
import {ToastService} from '@shared/components/toast';
import {ModalDismissReasons, NgbDateStruct, NgbModal,} from '@ng-bootstrap/ng-bootstrap';
import {ConfirmDialogService} from '@shared/components/confirm-dialog';
import {CompanyLite} from '@model/company-lite.model';
import {PackageHousing} from '@model/package-housing.model';
import {EAuthority, Page} from '@shared/model';
import {Applicant} from '@model/applicant.model';
import {Subscription} from 'rxjs';
import {ApplicantService} from '@core/services/applicant/applicant.service';
import {DateType} from '@shared/modules/hijri-gregorian-datepicker/consts';
import {DateFormatterService} from '@shared/modules/hijri-gregorian-datepicker/date-formatter.service';
import {
  HijriGregorianDatetimepickerComponent
} from '@shared/modules/hijri-gregorian-datepicker/datetimepicker/hijri-gregorian-datetimepicker.component';
import {ageRangeValidator, validateIsRequired,} from '@pages/notification-management/notification-custom-validator';
import {NotificationTemplateCategorizing} from '@model/notification-template-categorizing.model';
import {CompanyStaffTitleLookup} from '@model/company-staff-title-lookup';
import {CompanyStaff} from "@model/company-staff.model";
import {StaffService} from "@core/services/staff/staff.service";

@Component({
  selector: 'app-user-defined-notification-add',
  templateUrl: './user-defined-notification-add.component.html',
  styleUrls: ['./user-defined-notification-add.component.scss'],
})
export class UserDefinedNotificationAddComponent implements OnInit {
  DRAFT: string = 'DRAFT';
  CONFIRMED: string = 'CONFIRMED';
  closeResult = '';
  notificationCategories: Lookup[] = [];
  localizedNotificationCategories: Lookup[] = [];
  notificationForm: FormGroup;
  categorizingForm: FormGroup;
  languages: Lookup[] = [];
  translatedLanguages: Lookup[] = [];
  activeId;
  checkedCriteria: number = 0;
  creationDate: Date = new Date();
  isLoading: boolean;
  nationalities: Lookup[] = [];
  companies: CompanyLite[] = [];
  camps: PackageHousing[] = [];
  staffTitles: CompanyStaffTitleLookup[] = [];
  searchApplicantForm: FormGroup;
  searchStaffForm: FormGroup;
  pageArray: Array<number>;
  page: Page;
  selectedApplicants: Array<Applicant> = [];
  applicants: Array<Applicant> = [];
  addedApplicants: Array<Applicant> = [];
  selectedStaff: Array<CompanyStaff> = [];
  companyStaff: Array<CompanyStaff> = [];
  addedStaff: Array<CompanyStaff> = [];
  addedApplicantsCurrentPage: number = 1;
  addedApplicantsPageSize: number = 10;
  addedStaffCurrentPage: number = 1;
  addedStaffPageSize: number = 10;
  isSelectAllClicked: boolean;
  isSelectLoading: boolean;
  minSendingDateGregorian: NgbDateStruct;
  minSendingDateHijri: NgbDateStruct;
  dateString: string;
  selectedDateType: any;
  applicantsCount: number;
  staffCount: number;
  usersCount: number;
  show: boolean;
  @ViewChild('datePicker') datePicker: HijriGregorianDatetimepickerComponent;

  private listSubscription: Subscription;
  private searchSubscription: Subscription;

  constructor(
    private notificationService: NotificationService,
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
    private dateFormatterService: DateFormatterService,
    private staffService: StaffService
  ) {
  }

  ngOnInit(): void {
    // calendar default;
    let todayGregorian = this.dateFormatterService.todayGregorian();
    let todayHijri = this.dateFormatterService.todayHijri();
    this.minSendingDateGregorian = todayGregorian;
    this.minSendingDateHijri = todayHijri;
    this.selectedDateType = DateType.Gregorian;

    this.loadLookups();
    this.initCreateForm();
    this.initCategorizingForm();
    this.initSearchApplicantForm();
    this.initSearchStaffForm();

    this.translate.onLangChange.subscribe((event: LangChangeEvent) => {
      this.translatedLanguages = this.languages.filter(
        (c) =>
          event.lang.toLowerCase().substr(0, 2) ===
          c.lang.toLowerCase().substr(0, 2)
      );
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

  initCreateForm() {
    this.notificationForm = this.formBuilder.group({
      categoryCode: [null, Validators.required],
      important: false,
      enabled: true,
      userSpecific: false,
      forceSending: false,
      description: ['', Validators.required],
      sendingDate: [null, Validators.required],
      notificationTemplateContents: this.formBuilder.array([]),
      notificationTemplateCategorizing: null,
    });
  }

  initCategorizingForm() {
    this.categorizingForm = this.formBuilder.group({
      campId: null,
      companyId: null,
      nationalityCode: null,
      notificationCategory: 0,
      age: this.formBuilder.group(
        {
          minAge: ['', [Validators.min(0), Validators.max(120)]],
          maxAge: ['', [Validators.min(0), Validators.max(120)]],
        },
        {validator: ageRangeValidator}
      ),
      gender: null,
    });
  }

  initSearchApplicantForm(): void {
    this.searchApplicantForm = this.formBuilder.group({
      uin: null,
      idNumber: null,
      passportNumber: null,
    });
  }
  initSearchStaffForm(): void {
    this.searchStaffForm = this.formBuilder.group({
      suin: '',
      staffTitle: null,
    });
  }

  addTemplateContents(language: string) {
    const content = this.formBuilder.group(
      {
        lang: language,
        title: '',
        body: '',
      },
      {validator: validateIsRequired}
    );
    this.notificationTemplateContents.push(content);
  }

  get notificationTemplateContents(): FormArray {
    return <FormArray>this.notificationForm.get('notificationTemplateContents');
  }

  get f() {
    return this.notificationForm.controls;
  }

  get cf() {
    return this.categorizingForm.controls;
  }

  loadLookups() {
    this.notificationService
      .findNotificationCategories()
      .subscribe((result) => {
        this.notificationCategories = result;
        this.localizedNotificationCategories =
          this.lookupsService.localizedItems(this.notificationCategories);
      });

    this.notificationService.findLanguages().subscribe((result) => {
      this.languages = result;
      this.translatedLanguages = this.languages.filter(
        (c) =>
          this.currentLanguage.toLowerCase().substr(0, 2) ===
          c.lang.toLowerCase().substr(0, 2)
      );
      this.activeId = 1;
      this.translatedLanguages.forEach((lang) =>
        this.addTemplateContents(lang.code)
      );
    });

    this.cardService
      .findCountries()
      .subscribe((res) => (this.nationalities = res));
    this.notificationService
      .loadCompanies()
      .subscribe((res) => (this.companies = res));
    this.notificationService.loadCamps().subscribe((res) => (this.camps = res));
    this.staffService.loadStaffRoles().subscribe(
      (res) => ( this.staffTitles = res.filter(value => this.i18nService.language.startsWith(value.lang))));
    }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  get canSeeAddUpdateUserDefinedNotification(): boolean {
    return this.authenticationService.hasAuthority(
      EAuthority.USER_DEFINED_NOTIFICATION_MANAGEMENT
    );
  }

  lookupService(): LookupService {
    return this.lookupsService;
  }

  goBackToList() {
    this.router.navigate(['/user-defined-notification/list']);
  }

  save(statusCode: string) {
    let notificationTemplate = this.createNotificationTemplate();
    if (this.DRAFT === statusCode) {
      this.notificationForm.markAllAsTouched();
      if (this.checkedCriteria === 1){
        notificationTemplate.statusCode = this.DRAFT;
        this.confirmDialogService
          .confirm(
            this.translate.instant(
              'notification-management.save_changes_confirmation_text'
            ),
            this.translate.instant('general.dialog_confirmation_title')
          )
          .then((confirm) => {
            if (confirm) {
              this.proceedSaving(notificationTemplate);
            }
          });
      }
      if (this.checkedCriteria === 2){
        notificationTemplate.statusCode = this.DRAFT;
        this.confirmDialogService
          .confirm(
            this.translate.instant(
              'notification-management.save_changes_confirmation_text'
            ),
            this.translate.instant('general.dialog_confirmation_title')
          )
          .then((confirm) => {
            if (confirm) {
              this.proceedSaving(notificationTemplate);
            }
          });
      }
      if (this.checkedCriteria === 3) {
        this.categorizingForm.markAllAsTouched();
        if (this.categorizingForm.invalid) {
          return;
        }
      }
      if (this.notificationForm.invalid) {
        if (this.notificationTemplateContents.invalid) {
          // Set focus on tab containing errors
          this.activeId =
            this.translatedLanguages.findIndex(
              (lang) =>
                lang.code ===
                this.notificationTemplateContents.controls.find(
                  (control) => control.invalid
                ).value.lang
            ) + 1;
        }
        return;
      }
      if (this.checkedCriteria === 4 || this.checkedCriteria === 5) {
        notificationTemplate.statusCode = this.DRAFT;
        this.confirmDialogService
          .confirm(
            this.translate.instant(
              'notification-management.save_changes_confirmation_text'
            ),
            this.translate.instant('general.dialog_confirmation_title')
          )
          .then((confirm) => {
            if (confirm) {
              this.proceedSaving(notificationTemplate);
            }
          });
      }
    } else if (this.CONFIRMED === statusCode) {
      notificationTemplate.statusCode = this.CONFIRMED;
      this.proceedSaving(notificationTemplate);
      this.dismiss();
    }
  }

  proceedSaving(notificationTemplate: NotificationTemplate) {
    this.notificationService.save(notificationTemplate).subscribe((res) => {
      if (res.hasOwnProperty('errors') && res.errors) {
        this.toastr.warning(
          this.translate.instant('general.dialog_form_error_text'),
          this.translate.instant('general.dialog_add_title')
        );
        Object.keys(this.notificationForm.controls).forEach((field) => {
          console.log('looking for validation errors for : ' + field);
          if (res.errors[field]) {
            const control = this.notificationForm.get(field);
            control.setErrors({
              invalid: res.errors[field].replace(/\{/, '').replace(/\}/, ''),
            });
            control.markAsTouched({onlySelf: true});
          }
        });
      } else {
        this.toastr.success(
          this.translate.instant('notification-management.saved_successfully'),
          this.translate.instant('general.dialog_add_title')
        );
        this.router.navigate(['/user-defined-notification/list']);
      }
    });
  }

  dismiss() {
    this.modalService.dismissAll();
  }

  getApplicantsCount() {
    if (this.checkedCriteria === 3) {
      this.applicantService
        .countCategorizedApplicants(this.getCategorizing())
        .subscribe((res) => (this.applicantsCount = res));
    } else if (this.checkedCriteria === 4) {
      this.applicantsCount = this.addedApplicants.length;
    }else if (this.checkedCriteria === 5) {
      this.staffCount = this.addedStaff.length;
    }else if (this.checkedCriteria === 1) {
      this.applicantService
        .countApplicantsHavingCurrentRitual()
        .subscribe((res) => (this.applicantsCount = res));
    } else if (this.checkedCriteria === 2) {
      this.staffService.countRegisteredStaff()
        .subscribe((res) => (this.staffCount = res));
    }
  }

  getCategorizing() {
    let categorizing = this.categorizingForm.value;
    const minAge = this.categorizingForm.value.age.minAge;
    const maxAge = this.categorizingForm.value.age.maxAge;
    if (minAge) categorizing.minAge = minAge;
    if (maxAge) categorizing.maxAge = maxAge;
    categorizing.notificationCategory = 3;
    return categorizing;
  }

  createNotificationTemplate(): NotificationTemplate {
    let notificationTemplate: NotificationTemplate =
      this.notificationForm.value;
    notificationTemplate.notificationTemplateContents = this.notificationForm
      .get('notificationTemplateContents')
      .value.filter((c) => c.body.trim() !== '' || c.title.trim() !== '');

    if (this.checkedCriteria === 3) {
      notificationTemplate.notificationTemplateCategorizing =
        this.getCategorizing();
    }
    else if (this.checkedCriteria === 1) {
      notificationTemplate.notificationTemplateCategorizing =
        new NotificationTemplateCategorizing(null, null, 1 );
    }
    else if (this.checkedCriteria === 2) {
      notificationTemplate.notificationTemplateCategorizing =
        new NotificationTemplateCategorizing(null, null, 2 );
    }
    else if (this.checkedCriteria === 4 && this.addedApplicants?.length > 0) {
      const selectedApplicantsCSV = this.addedApplicants
        .map((applicant) => applicant.id)
        .join(',');
      const notificationTemplateCategorizing =
        new NotificationTemplateCategorizing(selectedApplicantsCSV, null,4 );
      notificationTemplate.notificationTemplateCategorizing =
        notificationTemplateCategorizing;
      notificationTemplateCategorizing.selectedApplicants =
        selectedApplicantsCSV;
    }
    else if (this.checkedCriteria === 5 && this.addedStaff?.length > 0) {
      const selectedStaffCSV = this.addedStaff
        .map((staff) => staff.id)
        .join(',');
      const notificationTemplateCategorizing =
        new NotificationTemplateCategorizing(null, selectedStaffCSV,5);
      notificationTemplate.notificationTemplateCategorizing =
        notificationTemplateCategorizing;
      notificationTemplateCategorizing.selectedStaff =
        selectedStaffCSV;
    }

    notificationTemplate.notificationTemplateContents.forEach((c) => {
      c.title = c.title.replace(/\s/g, ' ').trim();
      c.body = c.body.replace(/\s/g, ' ').trim();
    });
    return notificationTemplate;
  }

  cancel() {
    this.confirmDialogService
      .confirm(
        this.translate.instant(
          'notification-management.cancel_confirmation_text'
        ),
        this.translate.instant('general.dialog_confirmation_title')
      )
      .then((confirm) => {
        if (confirm) {
          this.goBackToList();
        }
      });
  }

  addApplicants() {
    this.addedApplicants = [
      ...this.addedApplicants,
      ...this.selectedApplicants,
    ];
    this.applicants = [];
    this.selectedApplicants = [];
    this.isSelectAllClicked = false;
    this.modalService.dismissAll();
  }
  addCompanyStaff(){
    this.addedStaff = [
      ...this.addedStaff,
      ...this.selectedStaff,
    ];
    this.companyStaff = [];
    this.selectedStaff = [];
    this.modalService.dismissAll();
  }

  resetSelectionModal() {
    this.searchApplicantForm.reset();
    this.searchStaffForm.reset();
    this.applicants = [];
    this.selectedApplicants = [];
    this.isSelectAllClicked = false;
    this.companyStaff = [];
    this.selectedStaff = [];
    this.modalService.dismissAll();
  }

  resetConfirmationModal() {
    this.applicantsCount = null;
    this.staffCount = null;
    this.usersCount = null;
    this.modalService.dismissAll();
  }

  searchApplicants(pageNumber: number): void {
    this.isSelectLoading = true;
    this.searchSubscription = this.applicantService
      .search(
        this.searchApplicantForm.value,
        this.addedApplicants.map((applicant) => applicant.id),
        pageNumber
      )
      .subscribe((data) => {
        this.isSelectLoading = false;
        this.applicants = [];
        this.pageArray = [];
        this.page = data;
        if (this.page != null) {
          this.pageArray = Array.from(this.pageCounter(this.page.totalPages));
          this.applicants = this.page.content;
        }
      });
    console.log(this.page);
  }
  searchCompanyStaff(pageNumber: number): void {
    this.isSelectLoading = true;
    console.log('*******************************');
    console.log(this.searchStaffForm.value);
    this.searchSubscription = this.staffService
      .search(
        this.searchStaffForm.value,
        this.addedStaff.map((companyStaff) => companyStaff.id),
        pageNumber
      )
      .subscribe((data) => {
        this.isSelectLoading = false;
        this.companyStaff = [];
        this.pageArray = [];
        this.page = data;
        if (this.page != null) {
          this.pageArray = Array.from(this.pageCounter(this.page.totalPages));
          this.companyStaff = this.page.content;
        }
      });
    console.log(this.companyStaff);
    console.log(this.page);
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
    this.modalService
      .open(content, {
        ariaLabelledBy: 'modal-basic-title',
        centered: true,
        size: 'lg',
      })
      .result.then(
      (result) => {
        this.closeResult = `Closed with: ${result}`;
      },
      (reason) => {
        this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
      }
    );
  }

  pageCounter(i: number): Array<number> {
    return new Array(i);
  }

  isApplicantChecked(applicant) {
    return this.selectedApplicants.some((c) => c.id === applicant.id);
  }
  isStaffChecked(staff) {
    return this.selectedStaff.some((c) => c.id === staff.id);
  }

  selectOneApplicant(event, id) {
    const selectedIndex = this.applicants.findIndex((card) => card.id === id);

    if (event.target.checked) {
      this.selectedApplicants.push(this.applicants[selectedIndex]);
    } else {
      this.selectedApplicants.splice(
        this.selectedApplicants.findIndex((card) => card.id === id),
        1
      );
    }
  }
  selectOneStaff(event, id) {
    const selectedIndex = this.companyStaff.findIndex((card) => card.id === id);

    if (event.target.checked) {
      this.selectedStaff.push(this.companyStaff[selectedIndex]);
    } else {
      this.selectedStaff.splice(
        this.selectedStaff.findIndex((card) => card.id === id),
        1
      );
    }
  }
  openSearchModal(content) {
    this.modalService
      .open(content, {
        ariaLabelledBy: 'modal-basic-title',
        centered: true,
        size: 'lg',
      })
      .result.then(
      (result) => {
        this.closeResult = `Closed with: ${result}`;
      },
      (reason) => {
        this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
        this.resetSelectionModal();
      }
    );
  }

  openSendModal(content) {
    console.log(this.notificationForm.get('sendingDate'));
    this.notificationForm.markAllAsTouched();
    if (this.checkedCriteria === 3) {
      this.categorizingForm.markAllAsTouched();
      if (this.categorizingForm.invalid) {
        return;
      }
    }
    if (this.notificationForm.invalid) {
      return;
    }
    if (this.checkedCriteria === 4 && this.addedApplicants.length === 0) {
      return;
    }
    this.modalService
      .open(content, {
        ariaLabelledBy: 'modal-basic-title',
        centered: true,
        size: 'lg',
      })
      .result.then(
      (result) => {
        this.closeResult = `Closed with: ${result}`;
      },
      (reason) => {
        this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
        this.resetConfirmationModal();
      }
    );
  }

  undoAddApplicant(applicantId: number) {
    this.addedApplicants.splice(
      this.addedApplicants.findIndex(
        (applicant) => applicant.id === applicantId
      ),
      1
    );
    if (
      this.addedApplicantsCurrentPage !== 1 &&
      this.addedApplicants.length % this.addedApplicantsPageSize === 0
    )
      this.addedApplicantsCurrentPage--;
  }

  undoAddStaff(staffId: number) {
    this.addedStaff.splice(
      this.addedStaff.findIndex(
        (staff) => staff.id === staffId
      ),
      1
    );
    if (
      this.addedStaffCurrentPage !== 1 &&
      this.addedStaff.length % this.addedStaffPageSize === 0
    )
      this.addedStaffCurrentPage--;
  }

  setCurrentPage(page: number) {
    this.addedApplicantsCurrentPage = page;
  }
  setCurrentStaffPage(page: number) {
    this.addedStaffCurrentPage = page;
  }

  getTotalPages(total, size): number {
    return Math.floor((total + size - 1) / size);
  }

  onSendingDateChange(event) {
    if (event) {
      let dateStruct =
        this.datePicker.selectedDateType == DateType.Gregorian
          ? this.dateFormatterService.toHijri(event)
          : this.dateFormatterService.toGregorian(event);
      let dateStructGreg =
        this.datePicker.selectedDateType == DateType.Gregorian
          ? event
          : this.dateFormatterService.toGregorian(event);
      let dateStructHijri =
        this.datePicker.selectedDateType == DateType.Gregorian
          ? this.dateFormatterService.toHijri(event)
          : event;
      this.dateString = this.dateFormatterService.toString(dateStruct);
      this.notificationForm.controls.sendingDateGregorian.setValue(
        this.dateFormatterService.toDate(dateStructGreg)
      );
      this.notificationForm.controls.sendingDateHijri.setValue(
        this.dateFormatterService
          .toString(dateStructHijri)
          .split('/')
          .reverse()
          .join('')
      );
    }
    console.log(event);
  }

  updateTabIndex(activeId) {
    this.activeId = activeId;
  }
}
