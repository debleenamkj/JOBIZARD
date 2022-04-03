import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CvTemplate3Component } from './cv-template3.component';

describe('CvTemplate3Component', () => {
  let component: CvTemplate3Component;
  let fixture: ComponentFixture<CvTemplate3Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CvTemplate3Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CvTemplate3Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
