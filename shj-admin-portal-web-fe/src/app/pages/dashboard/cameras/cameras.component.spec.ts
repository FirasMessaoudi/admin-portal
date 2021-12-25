/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { CamerasComponent } from './cameras.component';

describe('CamerasComponent', () => {
  let component: CamerasComponent;
  let fixture: ComponentFixture<CamerasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CamerasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CamerasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
