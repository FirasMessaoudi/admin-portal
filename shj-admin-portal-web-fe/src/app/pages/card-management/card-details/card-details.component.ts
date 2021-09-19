import {Component, OnInit} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import {I18nService} from "@dcc-commons-ng/services";
import {PackageCatering} from "@model/package-catering.model";
import {ActivatedRoute, Router} from "@angular/router";
import {combineLatest} from "rxjs";
import {map} from "rxjs/operators";
import {AuthenticationService, CardService} from "@core/services";
import {ToastService} from "@shared/components/toast";
import {Lookup} from "@model/lookup.model";
import {LookupService} from "@core/utilities/lookup.service";
import {CountryLookup} from "@model/country-lookup.model";
import {Language} from "@model/enum/language.enum";
import {ApplicantCard} from "@model/card.model";
import {EAuthority} from "@shared/model";
import {NavigationService} from "@core/utilities/navigation.service";

@Component({
  selector: 'app-card-details',
  templateUrl: './card-details.component.html',
  styleUrls: ['./card-details.component.scss']
})
export class CardDetailsComponent implements OnInit {
  cardId: number;
  card: ApplicantCard;
  url: any = 'assets/images/default-avatar.svg';
  //TODO: to be deleted after wiring the backend to the frontend
  hamlahPackage: any;

  ritualTypes: Lookup[];
  housingCategories: Lookup[];
  housingTypes: Lookup[];
  packageTypes: Lookup[];
  relativeRelationships: Lookup[];
  countries: CountryLookup[];
  healthSpecialNeeds: Lookup[];
  maritalStatuses: Lookup[];
  ritualStepsLabels: Lookup[];
  groupLeaderTitle: Lookup[];
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
              private navigationService: NavigationService
  ) {
  }

  ngOnInit(): void {
    this.loadLookups();
    this.renderBackLink = this.navigationService.getHistory().length !== 1;
    combineLatest([this.route.params, this.route.queryParams]).pipe(map(results => ({
      params: results[0].id,
      qParams: results[1]
    }))).subscribe(results => {
      this.cardId = +results.params; // (+) converts string 'id' to a number

      if (this.cardId) {
        // load user details
        this.cardService.find(this.cardId).subscribe(data => {
          if (data && data.id) {
            this.card = data;
            console.log(this.card);
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
    //TODO: dummy data
    this.hamlahPackage = {"id":1, "typeCode":{"id":1, "code":null, "label":"VIP", "lang":null}, "hamlahId":1, "campId":1, "price":6500, "departureCity":"Mombai", "countryId":1, "packageHousings":[{"id":1, "type":"مزدلفة", "code":"25475", "labelAr":"مخيم مشاعل النور", "labelEn":"", "validityStart":"01-04-2021", "validityEnd":"02-04-2021", "addressAr":"مزدلفة بجوار محطة القطار", "addressEn":"", "isDefault":true, "packageCaterings":[{"id":1, "type":"غداء", "meal":"", "descriptionAr":"بوفيه مفتوح", "descriptionEn":""}]}], "packageTransportations":[{"id":1, "type":"باص", "validityStart":"03-04-2021", "validityEnd":"04-04-2021", "locationFrom":"منى", "locationTo":"مزدلفة", "vehicleNumber":"ب ح ج 259"}]};
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
    this.cardService.findRitualStepsLabels().subscribe(result => {
      this.ritualStepsLabels = result;
    });
    this.cardService.findGroupLeaderTitleLabels().subscribe(result=> {
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

  get canSeeCardDetails(): boolean {

    return this.authenticationService.hasAuthority(EAuthority.VIEW_CARD_DETAILS);
  }

  goBack() {
    this.navigationService.back();
  }

  packageCaterings(): PackageCatering[] {
    let packageCaterings: PackageCatering[] = [];
    //TODO: to be refactored after wiring the backend to the frontend
    this.hamlahPackage.packageHousings.forEach(housing => {
      packageCaterings = packageCaterings.concat(housing.packageCaterings);
    });
    return packageCaterings;
  }

}
