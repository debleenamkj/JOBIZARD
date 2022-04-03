import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CvTemplate2Component } from './cv-template2.component';

describe('CvTemplate2Component', () => {
  let component: CvTemplate2Component;
  let fixture: ComponentFixture<CvTemplate2Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CvTemplate2Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CvTemplate2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
