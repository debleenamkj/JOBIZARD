import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobSeekerRegisterComponent } from './job-seeker-register.component';

describe('JobSeekerRegisterComponent', () => {
  let component: JobSeekerRegisterComponent;
  let fixture: ComponentFixture<JobSeekerRegisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JobSeekerRegisterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(JobSeekerRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
