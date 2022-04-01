import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SkilltestComponent } from './skilltest.component';

describe('SkilltestComponent', () => {
  let component: SkilltestComponent;
  let fixture: ComponentFixture<SkilltestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SkilltestComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SkilltestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
