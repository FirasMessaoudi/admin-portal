import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StaffPrintingDetailsComponent } from './staff-printing-details.component';

describe('StaffPrintingDetailsComponent', () => {
  let component: StaffPrintingDetailsComponent;
  let fixture: ComponentFixture<StaffPrintingDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StaffPrintingDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StaffPrintingDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
