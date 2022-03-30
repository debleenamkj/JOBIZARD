import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SalaryTrendLabComponent } from './salary-trend-lab.component';

describe('SalaryTrendLabComponent', () => {
  let component: SalaryTrendLabComponent;
  let fixture: ComponentFixture<SalaryTrendLabComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SalaryTrendLabComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SalaryTrendLabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
