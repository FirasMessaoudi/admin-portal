import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {StaffStepFourComponent} from './staff-step-four.component';

describe('StaffStepFourComponent', () => {
  let component: StaffStepFourComponent;
  let fixture: ComponentFixture<StaffStepFourComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [StaffStepFourComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StaffStepFourComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
