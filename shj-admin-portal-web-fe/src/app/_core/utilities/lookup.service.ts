import {I18nService} from "@dcc-commons-ng/services";
import {Injectable} from "@angular/core";
import {Lookup} from "@model/lookup.model";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

const defaultLang = 'ar';

@Injectable({
  providedIn: "root"
})
export class LookupService {

  constructor(private i18nService: I18nService, private http: HttpClient) {
  }

  localizedLabel(lookupItems: Lookup[], code: string): string {
    let item: Lookup = lookupItems?.find(type => type.code === code && this.i18nService.language.startsWith(type.lang));
    if (!item)
      item = lookupItems.find(type => type.code === code && type.lang.startsWith(defaultLang));
    return item?.label;
  }

  localizedItems(lookupItems: Lookup[]): Lookup[] {
    let items: Lookup[] = lookupItems?.filter(value => this.i18nService.language.startsWith(value.lang));
    if (!items)
      items = lookupItems.filter(value => value.lang.startsWith(defaultLang));
    return items;
  }

  loadGoogleMapsApiKey(): Observable<any> {
    return this.http.get<any>('/core/api/lookup/google-maps/api-key', {responseType: 'text' as 'json'});
  }
}
