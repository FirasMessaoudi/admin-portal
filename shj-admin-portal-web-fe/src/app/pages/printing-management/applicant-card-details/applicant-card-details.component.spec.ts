import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ApplicantCardDetailsComponent } from './applicant-card-details.component';

describe('ApplicantCardDetailsComponent', () => {
  let component: ApplicantCardDetailsComponent;
  let fixture: ComponentFixture<ApplicantCardDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ApplicantCardDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ApplicantCardDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
