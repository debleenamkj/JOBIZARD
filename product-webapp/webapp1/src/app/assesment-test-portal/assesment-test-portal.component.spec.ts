import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssesmentTestPortalComponent } from './assesment-test-portal.component';

describe('AssesmentTestPortalComponent', () => {
  let component: AssesmentTestPortalComponent;
  let fixture: ComponentFixture<AssesmentTestPortalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AssesmentTestPortalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AssesmentTestPortalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
