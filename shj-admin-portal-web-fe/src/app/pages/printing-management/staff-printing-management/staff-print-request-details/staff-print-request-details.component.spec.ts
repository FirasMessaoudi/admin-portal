import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {StaffPrintRequestDetailsComponent} from './staff-print-request-details.component';

describe('StaffPrintRequestDetailsComponent', () => {
  let component: StaffPrintRequestDetailsComponent;
  let fixture: ComponentFixture<StaffPrintRequestDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [StaffPrintRequestDetailsComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StaffPrintRequestDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
