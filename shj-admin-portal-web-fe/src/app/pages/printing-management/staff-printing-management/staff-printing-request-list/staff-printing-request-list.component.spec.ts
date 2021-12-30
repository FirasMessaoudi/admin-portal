import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {StaffPrintingRequestListComponent} from './staff-printing-request-list.component';

describe('PrintingRequestListComponent', () => {
  let component: StaffPrintingRequestListComponent;
  let fixture: ComponentFixture<StaffPrintingRequestListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [StaffPrintingRequestListComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StaffPrintingRequestListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
