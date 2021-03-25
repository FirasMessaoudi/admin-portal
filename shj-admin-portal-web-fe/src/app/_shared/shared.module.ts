import {NgModule} from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';

import {AccordionModule} from './modules/accordion/accordion.module';

// @ng-bootstrap
import {NgBootstrapModule} from '@shared/ng-bootstrap.module';

// SVG Icons
import {SvgIconModule} from '@app/_shared/components/svg-icon/svg-icon.module';
import {ArchwizardModule} from 'angular-archwizard';
import {ConfirmDialogComponent} from "@shared/components/confirm-dialog";
import {DateAgoPipe} from '@shared/pipes/date/date-ago/date-ago.pipe';
import {DateFormatPipe} from '@shared/pipes/date/date-format.pipe';
import {HijriFormatPipe} from '@shared/pipes/date/hijri-format.pipe';

import {NgbDatepickerModule, NgbModule, NgbToastModule, NgbTooltipModule} from '@ng-bootstrap/ng-bootstrap';
import {ToastsContainer} from './components/toast';
import {NgbdDatepickerIslamicumalqura} from './directives/datepicker-islamicumalqura';
import {IbanStatusPipe} from "@shared/pipes/iban/iban-status.pipe";
import {TranslateModule} from "@ngx-translate/core";
import {NgxCaptchaModule} from "ngx-captcha";
import {DccCommonsNgPipesModule} from '@dcc-commons-ng/pipes';
import {HijriGregorianDatepickerModule} from "@shared/modules/hijri-gregorian-datepicker/hijri-gregorian-datepicker.module";
import {NgMultiSelectDropDownModule} from "ng-multiselect-dropdown";


@NgModule({
  declarations: [
    ToastsContainer,
    ConfirmDialogComponent,
    DateAgoPipe,
    NgbdDatepickerIslamicumalqura,
    DateFormatPipe,
    HijriFormatPipe,
    IbanStatusPipe
  ],
  imports: [
    CommonModule,
    NgBootstrapModule,
    NgbToastModule,
    ArchwizardModule,
    SvgIconModule,
    AccordionModule,
    NgbModule,
    TranslateModule,
    NgxCaptchaModule,
    NgbDatepickerModule,
    HijriGregorianDatepickerModule,
    NgMultiSelectDropDownModule
  ],
  providers: [DateAgoPipe, DatePipe, DateFormatPipe, HijriFormatPipe, NgxCaptchaModule],
  exports: [
    NgbdDatepickerIslamicumalqura,
    AccordionModule,
    SvgIconModule,
    NgBootstrapModule,
    ArchwizardModule,
    TranslateModule,
    NgxCaptchaModule,
    NgbDatepickerModule,
    NgbTooltipModule,
    DateAgoPipe,
    ConfirmDialogComponent,
    ToastsContainer,
    DateFormatPipe,
    HijriFormatPipe,
    DatePipe,
    IbanStatusPipe,
    DccCommonsNgPipesModule,
    NgMultiSelectDropDownModule
  ],
  entryComponents: [
    NgbdDatepickerIslamicumalqura,
    ConfirmDialogComponent
  ]
})
export class SharedModule {
}
