import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobSeekerLandingComponent } from './job-seeker-landing.component';

describe('JobSeekerLandingComponent', () => {
  let component: JobSeekerLandingComponent;
  let fixture: ComponentFixture<JobSeekerLandingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JobSeekerLandingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(JobSeekerLandingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
