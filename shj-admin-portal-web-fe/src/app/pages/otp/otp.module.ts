import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {OtpComponent} from './otp.component';
import {OtpRoutingModule} from './otp-routing.module';

import {ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {SharedModule} from '@app/_shared/shared.module';


@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    OtpRoutingModule,
    HttpClientModule,
    SharedModule
  ],
  declarations: [
    OtpComponent
  ]
})
export class OtpModule {

}
