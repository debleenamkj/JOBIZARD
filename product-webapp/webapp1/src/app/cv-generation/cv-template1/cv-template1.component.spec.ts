import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CvTemplate1Component } from './cv-template1.component';

describe('CvTemplate1Component', () => {
  let component: CvTemplate1Component;
  let fixture: ComponentFixture<CvTemplate1Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CvTemplate1Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CvTemplate1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
