import {Component, OnInit} from '@angular/core';
import {I18nService} from "@dcc-commons-ng/services";
import {ActivatedRoute, Router} from "@angular/router";
import {ToastService} from "@shared/components/toast";
import {LangChangeEvent, TranslateService} from "@ngx-translate/core";
import {AuthenticationService, NotificationService} from "@core/services";
import {LookupService} from "@core/utilities/lookup.service";
import {Lookup} from "@model/lookup.model";
import {FormBuilder, FormGroup} from "@angular/forms";

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

  lookupService(): LookupService {
    return this.lookupsService;
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
}
