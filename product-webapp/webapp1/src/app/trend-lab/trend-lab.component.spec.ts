import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrendLabComponent } from './trend-lab.component';

describe('TrendLabComponent', () => {
  let component: TrendLabComponent;
  let fixture: ComponentFixture<TrendLabComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TrendLabComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TrendLabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
