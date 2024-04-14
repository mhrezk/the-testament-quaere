import { TestBed } from '@angular/core/testing';

import { NationLeaderService } from './nation-leader.service';

describe('NationLeaderService', () => {
  let service: NationLeaderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NationLeaderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
