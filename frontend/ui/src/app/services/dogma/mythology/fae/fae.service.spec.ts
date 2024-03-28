import { TestBed } from '@angular/core/testing';

import { FaeService } from './fae.service';

describe('FaeService', () => {
  let service: FaeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FaeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
