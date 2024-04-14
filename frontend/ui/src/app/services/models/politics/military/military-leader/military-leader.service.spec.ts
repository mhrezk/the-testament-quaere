import { TestBed } from '@angular/core/testing';

import { MilitaryLeaderService } from './military-leader.service';

describe('MilitaryLeaderService', () => {
  let service: MilitaryLeaderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MilitaryLeaderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
