import {I18nService} from "@dcc-commons-ng/services";
import {Injectable} from "@angular/core";
import {Lookup} from "@model/lookup.model";

@Injectable({
  providedIn: "root"
})
export class LookupService {

  constructor(private i18nService: I18nService ) {
  }

  localizedLabel(lookupItems: Lookup[], code: string): string {
    return lookupItems.find(type => type.code === code && this.i18nService.language.startsWith(type.lang)).label;
  }
}
