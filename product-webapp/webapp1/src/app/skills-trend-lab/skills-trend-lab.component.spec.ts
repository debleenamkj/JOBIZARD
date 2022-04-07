import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SkillsTrendLabComponent } from './skills-trend-lab.component';

describe('SkillsTrendLabComponent', () => {
  let component: SkillsTrendLabComponent;
  let fixture: ComponentFixture<SkillsTrendLabComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SkillsTrendLabComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SkillsTrendLabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
