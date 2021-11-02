import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserDefinedNotificationAddComponent } from './user-defined-notification-add.component';

describe('UserDefinedNotificationAddComponent', () => {
  let component: UserDefinedNotificationAddComponent;
  let fixture: ComponentFixture<UserDefinedNotificationAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserDefinedNotificationAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserDefinedNotificationAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
