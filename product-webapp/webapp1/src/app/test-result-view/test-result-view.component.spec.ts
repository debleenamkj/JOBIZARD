import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestResultViewComponent } from './test-result-view.component';

describe('TestResultViewComponent', () => {
  let component: TestResultViewComponent;
  let fixture: ComponentFixture<TestResultViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TestResultViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TestResultViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
