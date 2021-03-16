import { NgModule } from '@angular/core';
import { CdkTableModule } from '@angular/cdk/table';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModalModule, NgbCarouselModule, NgbPaginationModule, NgbNavModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
    imports: [
        NgbCarouselModule,
        NgbModalModule,
        NgbNavModule,
        CdkTableModule,
        FormsModule,
        ReactiveFormsModule,
        NgbPaginationModule
    ],
    exports: [
        NgbCarouselModule,
        NgbModalModule,
        NgbNavModule,
        CdkTableModule,
        FormsModule,
        ReactiveFormsModule,
        NgbPaginationModule
    ],
})
export class NgBootstrapModule { }
