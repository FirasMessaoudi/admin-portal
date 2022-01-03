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
import {combineLatest, Subscription} from "rxjs";
import {map} from "rxjs/operators";
import {NotificationTemplateContent} from "@model/notification-template-content.model";
import {ModalDismissReasons, NgbCalendar, NgbDate, NgbDateStruct, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {CompanyLite} from "@model/company-lite.model";
import {PackageHousing} from "@model/package-housing.model";
import {DateType} from "@shared/modules/hijri-gregorian-datepicker/consts";
import {DateFormatterService} from "@shared/modules/hijri-gregorian-datepicker/date-formatter.service";
import {ageRangeValidator, validateIsRequired} from "@pages/notification-management/notification-custom-validator";
import {ConfirmDialogService} from "@shared/components/confirm-dialog";
import {Applicant} from "@model/applicant.model";
import {ApplicantService} from "@core/services/applicant/applicant.service";
import {Page} from "@shared/model";
import {NotificationTemplateCategorizing} from "@model/notification-template-categorizing.model";

@Component({
  selector: 'app-user-defined-notification-details',
  templateUrl: './user-defined-notification-details.component.html',
  styleUrls: ['./user-defined-notification-details.component.scss']
})
export class UserDefinedNotificationDetailsComponent implements OnInit {
  closeResult = '';
  notificationCategories: Lookup[] = [];
  localizedNotificationCategories: Lookup[] = [];
  notificationForm: FormGroup;
  categorizingForm: FormGroup;
  searchForm: FormGroup;
  languages: Lookup[] = [];
  translatedLanguages: Lookup[] = [];
  activeId: number = 1;
  editMode: boolean;
  notificationTemplate: NotificationTemplate;
  notificationTemplateId: number;
  content: NotificationTemplateContent;
  notificationStatuses: Lookup[] = [];
  now: Date = new Date();
  today: NgbDate;
  nationalities: Lookup[] = [];
  companies: CompanyLite[] = [];
  camps: PackageHousing[] = [];
  minSendingDateGregorian: NgbDateStruct;
  minSendingDateHijri: NgbDateStruct;
  selectedDateType: any;
  canEdit: boolean;
  applicants: Array<Applicant> = [];
  savedApplicants: Array<Applicant> = [];
  addedApplicants: Array<Applicant> = [];
  addedApplicantsCurrentPage: number = 1;
  addedApplicantsPageSize: number = 10;
  pageArray: Array<number>;
  page: Page;
  ids: string;
  checkedCriteria: number = 0;
  isSelectAllClicked: boolean;
  isSelectLoading: boolean;
  selectedApplicants: Array<Applicant> = [];

  private listSubscription: Subscription;
  private searchSubscription: Subscription;

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
              private applicantService: ApplicantService,
              private modalService: NgbModal,
              private activeRoute: ActivatedRoute,
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
    this.initCategorizingForm();
    combineLatest([this.route.params, this.route.queryParams]).pipe(map(results => ({
      params: results[0].id,
      qParams: results[1]
    }))).subscribe(results => {
      this.notificationTemplateId = +results.params; // (+) converts string 'id' to a number
      if (this.notificationTemplateId) {
        this.editMode = false;
        this.notificationService.findUserDefinedNotificationTemplateById(this.notificationTemplateId).subscribe(data => {
          if (data && data.id) {
            this.notificationTemplate = data;
            if (data.notificationTemplateCategorizing) {
              if (data.notificationTemplateCategorizing?.selectedApplicants) {
                this.ids = data.notificationTemplateCategorizing?.selectedApplicants;
                this.listApplicants(data.notificationTemplateCategorizing?.selectedApplicants, 0);
                this.checkedCriteria = 2;
              } else {
                this.checkedCriteria = 1;
              }
              this.updateCategorizingForm();
            }
            //Enable edit if sending time is less or equal than current datetime and notification is not processed
            this.canEdit = (new Date(this.notificationTemplate?.sendingDate) > this.now) && !data.isProcessed;
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

  addApplicants() {
    this.addedApplicants = [...this.addedApplicants, ...this.selectedApplicants];
    this.applicants = [];
    this.selectedApplicants = [];
    this.isSelectAllClicked = false;
    this.modalService.dismissAll();
  }

  ngOnDestroy() {
    if (this.listSubscription) {
      this.listSubscription.unsubscribe();
    }
    if (this.searchSubscription) {
      this.searchSubscription.unsubscribe();
    }
  }

  selectOne(event, id) {
    const selectedIndex = this.applicants.findIndex(card => card.id === id);

    if (event.target.checked) {
      this.selectedApplicants.push(this.applicants[selectedIndex]);
    } else {
      this.selectedApplicants.splice(this.selectedApplicants.findIndex(card => card.id === id), 1);
    }

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
      id: null,
      creationDate: null,
      description: ['', Validators.required],
      sendingDate: [null, Validators.required],
      categoryCode: [null, Validators.required],
      typeCode: null,
      severity: null,
      enabled: false,
      userSpecific: false,
      forceSending: false,
      statusCode: null,
      notificationTemplateContents: this.formBuilder.array([])
    });
  }

  addTemplateContents(language: string) {
    const content = this.formBuilder.group({
      id: 0,
      creationDate: null,
      lang: language,
      title: '',
      body: '',
    }, {validator: validateIsRequired});
    this.notificationTemplateContents.push(content);
  }

  initCategorizingForm() {
    this.categorizingForm = this.formBuilder.group({
      campId: null,
      companyId: null,
      nationalityCode: null,
      age: this.formBuilder.group({
        minAge: [null, [Validators.min(0), Validators.max(120)]],
        maxAge: [null, [Validators.min(0), Validators.max(120)]],
      }, {validator: ageRangeValidator}),
      gender: null
    });
  }

  initSearchForm(): void {
    this.searchForm = this.formBuilder.group({
      uin: '',
      idNumber: '',
      passportNumber: ''
    });
  }

  get f() {
    return this.notificationForm.controls;
  }

  get cf() {
    return this.categorizingForm.controls;
  }

  get notificationTemplateContents(): FormArray {
    return <FormArray>this.notificationForm.get('notificationTemplateContents');
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

  listApplicants(selectedApplicants: string, pageNumber: number): void {
    const ids = selectedApplicants.split(",").filter(x => x.trim().length).map(Number);
    this.listSubscription = this.applicantService.findByIds(ids, pageNumber)
      .subscribe(data => {
        this.applicants = [];
        this.pageArray = [];
        this.page = data;
        if (this.page != null) {
          this.pageArray = Array.from(this.pageCounter(this.page.totalPages));
          this.savedApplicants = this.page.content;
        }
      });
  }

  pageCounter(i: number): Array<number> {
    return new Array(i);
  }

  updateForm() {
    let templateId = this.activeRoute.snapshot.params.id;
    if (templateId) {
      this.notificationForm.patchValue({
        id: templateId,
        sendingDate: this.notificationTemplate?.sendingDate,
        categoryCode: this.notificationTemplate?.categoryCode,
        severity: this.notificationTemplate?.important,
        enabled: this.notificationTemplate?.enabled,
        userSpecific: this.notificationTemplate?.userSpecific,
        description: this.notificationTemplate?.description,
        typeCode: this.notificationTemplate?.typeCode,
        statusCode: this.notificationTemplate?.statusCode,
        forceSending: this.notificationTemplate?.forceSending
      });
      this.notificationTemplate.notificationTemplateContents.forEach(c => {
        let content = this.notificationForm.controls.notificationTemplateContents['controls'].find(field => field['controls'].lang.value === c.lang);
        content.controls.id.setValue(c.id);
        content.controls.creationDate.setValue(c.creationDate);
        content.controls.title.setValue(c.title);
        content.controls.body.setValue(c.body);
      });
      if (!this.canEdit) {
        this.notificationForm.get('creationDate').disable();
        this.notificationForm.get('categoryCode').disable();
        this.notificationForm.get('severity').disable();
        this.notificationForm.get('enabled').disable();
        this.notificationForm.get('userSpecific').disable();
        this.notificationForm.get('typeCode').disable();
        this.notificationForm.get('statusCode').disable();
        this.notificationForm.get('forceSending').disable();
        this.notificationForm.get('notificationTemplateContents').disable();

        this.categorizingForm.controls.campId.disable();
        this.categorizingForm.controls.companyId.disable();
        this.categorizingForm.controls.nationalityCode.disable();
        this.categorizingForm.controls.gender.disable();
        this.categorizingForm.get('age')['controls']['minAge'].disable();
        this.categorizingForm.get('age')['controls']['maxAge'].disable();
      }

      this.notificationForm.markAllAsTouched();
      this.categorizingForm.markAllAsTouched();
    }
  }

  updateCategorizingForm() {
    this.categorizingForm.patchValue({
      campId: this.notificationTemplate?.notificationTemplateCategorizing?.campId,
      companyId: this.notificationTemplate?.notificationTemplateCategorizing?.companyId,
      nationalityCode: this.notificationTemplate?.notificationTemplateCategorizing?.nationalityCode,
      age: {
        minAge: this.notificationTemplate?.notificationTemplateCategorizing?.minAge,
        maxAge: this.notificationTemplate?.notificationTemplateCategorizing?.maxAge
      },
      gender: this.notificationTemplate?.notificationTemplateCategorizing.gender
    });
  }

  save() {
    let notificationTemplate = this.createNotificationTemplate();
    this.notificationForm.markAllAsTouched();
    if (this.checkedCriteria === 1) {
      this.categorizingForm.markAllAsTouched();
      if (this.categorizingForm.invalid) {
        return;
      }
    }
    if (this.notificationForm.invalid) {
      if (this.notificationTemplateContents.invalid) {
        // Set focus on tab containing errors
        this.activeId = this.translatedLanguages.findIndex(lang => lang.code === this.notificationTemplateContents.controls.find(control => control.invalid).value.lang) + 1;
      }
      return;
    }
    if (this.checkedCriteria === 2 && this.addedApplicants.length === 0) {
      return;
    }

    this.confirmDialogService
      .confirm(this.translate.instant('notification-management.save_changes_confirmation_text'),
        this.translate.instant('general.dialog_confirmation_title')).then(confirm => {
      if (confirm) {
        this.notificationService.updateUserDefined(notificationTemplate).subscribe(res => {
          if (res.hasOwnProperty('errors') && res.errors) {
            this.toastr.warning(this.translate.instant('general.dialog_form_error_text'), this.translate.instant("general.dialog_edit_title"));
            Object.keys(this.notificationForm.controls).forEach(field => {
              console.log('looking for validation errors for : ' + field);
              if (res.errors[field]) {
                const control = this.notificationForm.get(field);
                control.setErrors({invalid: res.errors[field].replace(/\{/, '').replace(/\}/, '')});
                control.markAsTouched({onlySelf: true});
              }
            });
          } else {
            this.toastr.success(this.translate.instant("general.dialog_edit_success_text"), this.translate.instant("general.dialog_edit_title"));
            this.router.navigate(['/user-defined-notification/list']);
          }
        });
      }
    });
  }

  createNotificationTemplate(): NotificationTemplate {
    let notificationTemplate: NotificationTemplate = this.notificationForm.value;

    if (this.canEdit) {
      notificationTemplate.notificationTemplateContents = this.notificationForm.get('notificationTemplateContents').value
        .filter(c => c.body.trim() !== '' || c.title.trim() !== '');
      notificationTemplate.notificationTemplateContents.forEach(c => {
        c.title = c.title.replace(/\s/g, " ").trim();
        c.body = c.body.replace(/\s/g, " ").trim();
      });
    }

    if (this.checkedCriteria === 1) {
      notificationTemplate.notificationTemplateCategorizing = this.getCategorizing();
    } else if (this.checkedCriteria === 2 && this.addedApplicants?.length > 0) {
      const selectedApplicantsCSV = this.addedApplicants.map(applicant => applicant.id).join(",");
      let notificationTemplateCategorizing = new NotificationTemplateCategorizing(selectedApplicantsCSV)
      notificationTemplate.notificationTemplateCategorizing = notificationTemplateCategorizing;
      notificationTemplateCategorizing.selectedApplicants = selectedApplicantsCSV;
    }

    return notificationTemplate;
  }

  getCategorizing() {
    let categorizing = this.categorizingForm.value;
    const minAge = this.categorizingForm.value.age.minAge;
    const maxAge = this.categorizingForm.value.age.maxAge;
    if (minAge) categorizing.minAge = minAge;
    if (maxAge) categorizing.maxAge = maxAge;
    return categorizing;
  }

  openSearchModal(content) {
    this.modalService.open(content, {
      ariaLabelledBy: 'modal-basic-title',
      centered: true,
      size: 'lg'
    }).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
      this.resetSelectionModal();
    });
  }

  resetSelectionModal() {
    this.searchForm.reset()
    this.applicants = [];
    this.selectedApplicants = [];
    this.isSelectAllClicked = false;
    this.modalService.dismissAll();
  }

  dismiss() {
    this.modalService.dismissAll();
  }

  get canSeeAddUpdateUserDefinedNotification(): boolean {
    //TODO Update authorities
    // return this.authenticationService.hasAuthority(EAuthority.ADD_USER) || this.authenticationService.hasAuthority(EAuthority.EDIT_USER);
    return true;
  }

  setCurrentPage(page: number) {
    this.addedApplicantsCurrentPage = page;
  }

  getTotalPages(total, size): number {
    return Math.floor((total + size - 1) / size);
  }

  goBackToList() {
    this.router.navigate(['/user-defined-notification/list']);
  }

  enableEditMode() {
    this.editMode = true;
    this.updateForm();
    this.initSearchForm();
    this.addedApplicants = [...this.savedApplicants];
    this.applicants = [];
  }

  checkContentIsValid(i): boolean {
    const localizedContent = this.notificationTemplate?.notificationTemplateContents[i];
    return !this.editMode && (localizedContent?.title?.length > 0 && localizedContent?.body?.length > 0);
  }

  search(pageNumber: number): void {
    this.isSelectLoading = true;
    this.searchSubscription = this.applicantService.search(this.searchForm.value, this.addedApplicants.map(applicant => applicant.id), pageNumber)
      .subscribe(data => {
        this.isSelectLoading = false;
        this.applicants = [];
        this.pageArray = [];
        this.page = data;
        if (this.page != null) {
          this.pageArray = Array.from(this.pageCounter(this.page.totalPages));
          this.applicants = this.page.content;
        }
      });
  }

  isChecked(applicant) {
    return this.selectedApplicants.some(c => c.id === applicant.id);
  }

  undoAddApplicant(applicantId: number) {
    this.addedApplicants.splice(this.addedApplicants.findIndex(applicant => applicant.id === applicantId), 1);
    if (this.addedApplicantsCurrentPage !== 1 && this.addedApplicants.length % this.addedApplicantsPageSize === 0) this.addedApplicantsCurrentPage--;
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
        this.editMode = false;
        this.activeId = 1;
      }
    });
  }

  updateTabIndex(activeId) {
    this.activeId = activeId;
  }

}
