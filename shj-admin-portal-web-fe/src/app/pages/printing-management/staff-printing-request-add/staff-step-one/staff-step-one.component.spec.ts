import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {StaffStepOneComponent} from './staff-step-one.component';

describe('StaffStepOneComponent', () => {
  let component: StaffStepOneComponent;
  let fixture: ComponentFixture<StaffStepOneComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [StaffStepOneComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StaffStepOneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
