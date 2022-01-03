import {NgModule} from '@angular/core';
import {HijriGregorianDatepickerComponent} from './datepicker/hijri-gregorian-datepicker.component';
import {HijriDatepickerComponent} from './hijri-datepicker/hijri-datepicker.component';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule, FormsModule} from '@angular/forms';
import {NgbModule, NgbDateParserFormatter} from '@ng-bootstrap/ng-bootstrap';
import {DateFormatterService} from './date-formatter.service';
import {CustomNgbDateParserFormatter} from './CustomNgbDateParserFormatter';
import {ProvideParentFormDirective} from './provide-parent-form.directive';
import {TranslateModule} from "@ngx-translate/core";
import {NgbDatepickerI18nTitleDirective} from './ngb-datepicker-i18n-title.directive';
import {
  HijriGregorianDatetimepickerComponent
} from "@shared/modules/hijri-gregorian-datepicker/datetimepicker/hijri-gregorian-datetimepicker.component";
import {
  HijriDatetimePickerComponent
} from "@shared/modules/hijri-gregorian-datepicker/hijri-datetimepicker/hijri-datetime-picker.component";
import {HijriGregorianRangePickerComponent} from './rangepicker/hijri-gregorian-range-picker.component';

// https://eslamelmadny.github.io/HijriGregorianDatepicker/
// https://github.com/EslamElmadny/HijriGregorianDatepicker

@NgModule({
  declarations: [
    HijriGregorianDatepickerComponent,
    HijriGregorianDatetimepickerComponent,
    HijriDatepickerComponent,
    HijriDatetimePickerComponent,
    ProvideParentFormDirective,
    NgbDatepickerI18nTitleDirective,
    HijriGregorianRangePickerComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    NgbModule,
    TranslateModule
  ],
  providers: [
    {provide: NgbDateParserFormatter, useClass: CustomNgbDateParserFormatter},
    DateFormatterService
  ],
  exports: [HijriGregorianDatepickerComponent, HijriGregorianDatetimepickerComponent, HijriGregorianRangePickerComponent]
})
export class HijriGregorianDatepickerModule {
}
