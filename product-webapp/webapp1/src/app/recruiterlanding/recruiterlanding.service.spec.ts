import { TestBed } from '@angular/core/testing';

import { RecruiterlandingService } from './recruiterlanding.service';

describe('RecruiterlandingService', () => {
  let service: RecruiterlandingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RecruiterlandingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
