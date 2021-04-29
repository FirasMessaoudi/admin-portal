import {Component, OnInit} from '@angular/core';
import {Card} from "@model/card.model";
import {TranslateService} from "@ngx-translate/core";
import {I18nService} from "@dcc-commons-ng/services";
import {PackageCatering} from "@model/package-catering.model";
import {ActivatedRoute, Router} from "@angular/router";
import {combineLatest} from "rxjs";
import {map} from "rxjs/operators";
import {CardService} from "@core/services";
import {ToastService} from "@shared/components/toast";
import {Lookup} from "@model/lookup.model";

@Component({
  selector: 'app-card-details',
  templateUrl: './card-details.component.html',
  styleUrls: ['./card-details.component.scss']
})
export class CardDetailsComponent implements OnInit {
  cardId: number;
  card: Card;
  url: any = 'assets/images/default-avatar.svg';
  //TODO: to be deleted after wiring the backend to the frontend
  hamlahPackage: any;
  ritualTypes: Lookup[];

  constructor(private route: ActivatedRoute,
              private router: Router,
              private toastr: ToastService,
              private cardService: CardService,
              private translate: TranslateService,
              private i18nService: I18nService) { }

  ngOnInit(): void {
    this.cardService.findRitualTypes().subscribe(result => {
      console.log('ritual types: ' + result);
      this.ritualTypes = result;
    });

    combineLatest(this.route.params, this.route.queryParams).pipe(map(results => ({
      params: results[0].id,
      qParams: results[1]
    }))).subscribe(results => {
      this.cardId = +results.params; // (+) converts string 'id' to a number

      if (this.cardId) {
        // load user details
        this.cardService.find(this.cardId).subscribe(data => {
          if (data && data.id) {
            this.card = data;
          } else {
            this.toastr.error(this.translate.instant('general.card_item_not_found', {itemId: this.cardId}),
              this.translate.instant('general.dialog_error_title'));
            this.goToList();
          }
        });
      } else {
        this.toastr.error(this.translate.instant('general.card_id_param_not_found'),
          this.translate.instant('general.dialog_error_title'));
        this.goToList();
      }
    });
    //TODO: dummy data
    this.hamlahPackage = {"id":1, "typeCode":{"id":1, "code":null, "label":"VIP", "lang":null}, "hamlahId":1, "campId":1, "price":6500, "departureCity":"Mombai", "countryId":1, "packageHousings":[{"id":1, "type":"مزدلفة", "code":"25475", "labelAr":"مخيم مشاعل النور", "labelEn":"", "validityStart":"01-04-2021", "validityEnd":"02-04-2021", "addressAr":"مزدلفة بجوار محطة القطار", "addressEn":"", "isDefault":true, "packageCaterings":[{"id":1, "type":"غداء", "meal":"", "descriptionAr":"بوفيه مفتوح", "descriptionEn":""}]}], "packageTransportations":[{"id":1, "type":"باص", "validityStart":"03-04-2021", "validityEnd":"04-04-2021", "locationFrom":"منى", "locationTo":"مزدلفة", "vehicleNumber":"ب ح ج 259"}]};

  }

  goToList() {
    this.router.navigate(['/cards/list']);
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  lookupLabel(code: string): string {
    return this.ritualTypes.find(type => type.code === code && type.lang === this.i18nService.language).label;
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
