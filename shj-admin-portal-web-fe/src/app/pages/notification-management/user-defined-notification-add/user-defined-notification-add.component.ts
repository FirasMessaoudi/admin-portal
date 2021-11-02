import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {LangChangeEvent, TranslateService} from "@ngx-translate/core";
import {Lookup} from "@model/lookup.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {NotificationService} from "@core/services";
import {LookupService} from "@core/utilities/lookup.service";
import {I18nService} from "@dcc-commons-ng/services";
import {CompanyLite} from "@model/company-lite.model";

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
  companiesList: CompanyLite[] = []
  nationalitiesList: Lookup[] = [];
  constructor(private notificationService: NotificationService,
              private lookupsService: LookupService,
              private formBuilder: FormBuilder,
              private router: Router,
              private translate: TranslateService,
              private i18nService: I18nService,
  ) {
  }

  ngOnInit(): void {
    this.notificationService.loadCompanies().subscribe(result => {
      this.companiesList = result;
    });
    this.notificationService.loadNationality().subscribe(result => {
      this.nationalitiesList = result;
    });


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
      creationDate: {value: null, disabled: true},
      sendingDate: {value: null, disabled: true},
      notificationName: [''],
      notificationCategory: [null],
      severity: [null],
      enabled: {value: false},
      notificationTitle: [''],
      notificationDetails: ['']
    });
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
}
