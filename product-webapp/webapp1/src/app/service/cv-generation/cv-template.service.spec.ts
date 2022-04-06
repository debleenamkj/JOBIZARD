import { TestBed } from '@angular/core/testing';

import { CvTemplateService } from './cv-template.service';

describe('CvTemplateService', () => {
  let service: CvTemplateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CvTemplateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
