import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {StaffSuccessComponent} from './staff-success.component';

describe('StaffSuccessComponent', () => {
  let component: StaffSuccessComponent;
  let fixture: ComponentFixture<StaffSuccessComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [StaffSuccessComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StaffSuccessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
