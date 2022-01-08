import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {StaffStepTwoComponent} from './staff-step-two.component';

describe('StaffStepTwoComponent', () => {
  let component: StaffStepTwoComponent;
  let fixture: ComponentFixture<StaffStepTwoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [StaffStepTwoComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StaffStepTwoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
