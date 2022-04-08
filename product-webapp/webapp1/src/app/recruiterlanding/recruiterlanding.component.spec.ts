import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecruiterlandingComponent } from './recruiterlanding.component';

describe('RecruiterlandingComponent', () => {
  let component: RecruiterlandingComponent;
  let fixture: ComponentFixture<RecruiterlandingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecruiterlandingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RecruiterlandingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
