import { TestBed } from '@angular/core/testing';

import { TockenInterceptorService } from './tocken-interceptor.service';

describe('TockenInterceptorService', () => {
  let service: TockenInterceptorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TockenInterceptorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
