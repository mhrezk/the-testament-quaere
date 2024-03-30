import { TestBed } from '@angular/core/testing';

import { BattalionService } from './battalion.service';

describe('BattalionService', () => {
  let service: BattalionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BattalionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
