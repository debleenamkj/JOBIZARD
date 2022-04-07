import { TestBed } from '@angular/core/testing';

import { SkilltestServiceService } from './skilltest-service.service';

describe('SkilltestServiceService', () => {
  let service: SkilltestServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SkilltestServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
