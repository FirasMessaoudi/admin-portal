import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {StaffStepThreeComponent} from './staff-step-three.component';

describe('StaffStepThreeComponent', () => {
  let component: StaffStepThreeComponent;
  let fixture: ComponentFixture<StaffStepThreeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [StaffStepThreeComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StaffStepThreeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
