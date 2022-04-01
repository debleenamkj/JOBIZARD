import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecruiterRegisterComponent } from './recruiter-register.component';

describe('RecruiterRegisterComponent', () => {
  let component: RecruiterRegisterComponent;
  let fixture: ComponentFixture<RecruiterRegisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecruiterRegisterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RecruiterRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
