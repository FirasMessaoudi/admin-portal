import {Component, OnInit} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import {I18nService} from "@dcc-commons-ng/services";
import {ActivatedRoute, Router} from "@angular/router";
import {combineLatest} from "rxjs";
import {map} from "rxjs/operators";
import {AuthenticationService, CardService} from "@core/services";
import {ToastService} from "@shared/components/toast";
import {Lookup} from "@model/lookup.model";
import {LookupService} from "@core/utilities/lookup.service";
import {NationalityLookup} from "@model/nationality-lookup.model";
import {Language} from "@model/enum/language.enum";
import {ApplicantCard} from "@model/applicant-card.model";
import {EAuthority} from "@shared/model";
import {NavigationService} from "@core/utilities/navigation.service";
import {CardStatus} from "@model/enum/card-status.enum";
import {DigitalIdStatus} from "@model/enum/digital-id-status.enum";
import {CardStatusActions} from "@model/enum/card-status-actions.enum";
import {ConfirmDialogService} from "@shared/components/confirm-dialog";

@Component({
  selector: 'app-card-details',
  templateUrl: './card-details.component.html',
  styleUrls: ['./card-details.component.scss']
})
export class CardDetailsComponent implements OnInit {
  cardId: number;
  card: ApplicantCard;
  url: any = 'assets/images/default-avatar.svg';
  ritualTypes: Lookup[];
  housingCategories: Lookup[];
  housingTypes: Lookup[];
  packageTypes: Lookup[];
  housingSites: Lookup[];
  transportationTypes: Lookup[];
  relativeRelationships: Lookup[];
  countries: NationalityLookup[];
  healthSpecialNeeds: Lookup[];
  maritalStatuses: Lookup[];
  ritualStepsLabels: Lookup[];
  cardStatuses: Lookup[];
  groupLeaderTitle: Lookup[];
  applicantStatuses: Lookup[] = [];
  immunizations: Lookup[];
  languageNativeName = Language;
  renderBackLink = false;


  constructor(private route: ActivatedRoute,
              private router: Router,
              private toastr: ToastService,
              private cardService: CardService,
              private translate: TranslateService,
              private i18nService: I18nService,
              private lookupsService: LookupService,
              private authenticationService: AuthenticationService,
              private navigationService: NavigationService,
              private confirmDialogService: ConfirmDialogService
  ) {
  }

  ngOnInit(): void {
    this.loadLookups();
    // this.isLoading = true;
    this.renderBackLink = this.navigationService.getHistory().length !== 1;
    combineLatest([this.route.params, this.route.queryParams]).pipe(map(results => ({
      params: results[0].id,
      qParams: results[1]
    }))).subscribe(results => {
      this.cardId = +results.params; // (+) converts string 'id' to a number
      // this.isLoading = false;
      if (this.cardId) {
        // load user details
        this.cardService.find(this.cardId).subscribe(data => {
          if (data && data.id) {
            this.card = data;
          } else {
            this.toastr.error(this.translate.instant('general.route_item_not_found', {itemId: this.cardId}),
              this.translate.instant('general.dialog_error_title'));
            this.goToList();
          }
        });
      } else {
        this.toastr.error(this.translate.instant('general.route_id_param_not_found'),
          this.translate.instant('general.dialog_error_title'));
        this.goToList();
      }
    });
  }

  loadLookups() {
    this.cardService.findRitualTypes().subscribe(result => {
      this.ritualTypes = result;
    });
    this.cardService.findRelativeRelationships().subscribe(result => {
      this.relativeRelationships = result;
    });
    this.cardService.findCountries().subscribe(result => {
      this.countries = result;
    });
    this.cardService.findHealthSpecialNeeds().subscribe(result => {
      this.healthSpecialNeeds = result;
    });
    this.cardService.findMaritalStatuses().subscribe(result => {
      this.maritalStatuses = result;
    });
    this.cardService.findCardStatuses().subscribe(result => {
      this.cardStatuses = result;
    });
    this.cardService.findRitualStepsLabels().subscribe(result => {
      this.ritualStepsLabels = result;
    });
    this.cardService.findGroupLeaderTitleLabels().subscribe(result => {
      this.groupLeaderTitle = result;
    })

    this.cardService.findHousingTypes().subscribe(result => {
      this.housingTypes = result;
    });

    this.cardService.findHousingCategories().subscribe(result => {
      this.housingCategories = result;
    });

    this.cardService.findPackageTypes().subscribe(result => {
      this.packageTypes = result;
    });

    this.cardService.findHousingSites().subscribe(result => {
      this.housingSites = result;
    });

    this.cardService.findTransportationTypes().subscribe(result => {
      this.transportationTypes = result;
    });

    this.cardService.findDigitalIdStatuses().subscribe(result => {
      this.applicantStatuses = result;
    });

    this.cardService.findHealthImmunizations().subscribe(result => {
      this.immunizations = result;
    });

  }

  public get cardStatus(): typeof CardStatus {
    return CardStatus;
  }

  public get actions(): typeof CardStatusActions {
    return CardStatusActions;
  }

  changeCardStatus(actionCode: string, confirmationText: string) {
    if (this.isUserHasAllowedAuthority(actionCode)) {
      this.confirmDialogService.confirm(this.translate.instant(confirmationText), this.translate.instant('general.dialog_confirmation_title')).then(confirm => {
        if (confirm) {

          if(actionCode == this.actions.REPRINT_CARD)          
          {
           console.log("Reprint The Card");
           this.router.navigate(['/card/print',this.card?.referenceNumber]);
          }
          else 
          {
            this.cardService.changeCardStatus(this.card.id, actionCode).subscribe(result => {
              this.card = result;
              this.cardId = this.card.id;
              let cardStatusChangedText = '';
              if (actionCode == this.actions.ACTIVATE_CARD) {
                cardStatusChangedText = 'card-management.dialog_activated_card_success_text';
              } else if (actionCode == this.actions.SUSPEND_CARD) {
                cardStatusChangedText = 'card-management.dialog_suspended_card_success_text';
              } else if (actionCode == this.actions.CANCEL_CARD) {
                cardStatusChangedText = 'card-management.dialog_canceled_card_success_text';
              } 
              else if(actionCode == this.actions.REPRINT_CARD){
                cardStatusChangedText = 'card-management.dialog_canceled_card_success_text';
              }
              else {
                cardStatusChangedText = 'card-management.dialog_reissued_card_success_text';
              }
              this.toastr.success(this.translate.instant(cardStatusChangedText), this.translate.instant('general.dialog_edit_title'));
            }, error => {
              this.toastr.warning(this.translate.instant("general.dialog_error_text"), this.translate.instant("general.dialog_edit_title"));
            });

          }

        
        }
      });
    } else {
      this.toastr.error(this.translate.instant('card-management.user_not_authorized'),
        this.translate.instant('general.dialog_error_title'));
    }
  }


  isUserHasAllowedAuthority(statusCode: string) {
    switch (statusCode.toLowerCase()) {
      case 'suspend_card': {
        return this.authenticationService.hasAuthority(EAuthority.SUSPEND_CARD);
      }
      case 'reissue_card': {
        return this.authenticationService.hasAuthority(EAuthority.REISSUE_CARD);
      }
      case 'activate_card': {
        return this.authenticationService.hasAuthority(EAuthority.ACTIVATE_CARD);
      }
      case 'cancel_card': {
        return this.authenticationService.hasAuthority(EAuthority.CANCEL_CARD);
      }
      case 'reprint_card': {
        return this.authenticationService.hasAuthority(EAuthority.CANCEL_CARD);
      }
      default: {
        return false;
      }
    }
  }

  goToList() {
    this.router.navigate(['/cards/list']);
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  lookupService(): LookupService {
    return this.lookupsService;
  }

  buildDigitalIdClass(status: any): string {
    switch (status) {
      case DigitalIdStatus.VALID:
        return "done";
      case DigitalIdStatus.INVALID:
        return "Suspended";
      default:
        return "done";
    }
  }

  get canSeeCardDetails(): boolean {

    return this.authenticationService.hasAuthority(EAuthority.VIEW_CARD_DETAILS);
  }

  goBack() {
    this.navigationService.back();
  }

  /**
   * Returns the css class for the given status
   *
   * @param status the current card status
   */
  buildStatusClass(status: any): string {
    switch (status) {
      case CardStatus.SUSPENDED:
        return "Suspended";
      case CardStatus.CANCELLED:
        return "new";
      default:
        return "done";
    }
  }

}
