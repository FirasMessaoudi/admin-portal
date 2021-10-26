import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {SystemDefinedNotificationDetailsComponent} from './system-defined-notification-details.component';

describe('SystemDefinedNotificationDetailsComponent', () => {
  let component: SystemDefinedNotificationDetailsComponent;
  let fixture: ComponentFixture<SystemDefinedNotificationDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [SystemDefinedNotificationDetailsComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SystemDefinedNotificationDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
