import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CvGenerationComponent } from './cv-generation.component';

describe('CvGenerationComponent', () => {
  let component: CvGenerationComponent;
  let fixture: ComponentFixture<CvGenerationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CvGenerationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CvGenerationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
