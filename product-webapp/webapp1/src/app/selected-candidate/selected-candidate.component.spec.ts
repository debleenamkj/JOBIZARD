import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectedCandidateComponent } from './selected-candidate.component';

describe('SelectedCandidateComponent', () => {
  let component: SelectedCandidateComponent;
  let fixture: ComponentFixture<SelectedCandidateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SelectedCandidateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SelectedCandidateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
