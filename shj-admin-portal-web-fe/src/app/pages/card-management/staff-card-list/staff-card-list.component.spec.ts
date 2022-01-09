import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StaffCardListComponent } from './staff-card-list.component';

describe('StaffCardListComponent', () => {
  let component: StaffCardListComponent;
  let fixture: ComponentFixture<StaffCardListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StaffCardListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StaffCardListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
