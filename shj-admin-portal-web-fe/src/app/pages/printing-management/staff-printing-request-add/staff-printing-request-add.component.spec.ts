import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {StaffPrintingRequestAddComponent} from './staff-printing-request-add.component';

describe('StaffPrintingRequestAddComponent', () => {
  let component: StaffPrintingRequestAddComponent;
  let fixture: ComponentFixture<StaffPrintingRequestAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [StaffPrintingRequestAddComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StaffPrintingRequestAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
