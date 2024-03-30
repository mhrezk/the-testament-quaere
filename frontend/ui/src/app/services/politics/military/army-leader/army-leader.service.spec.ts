import { TestBed } from '@angular/core/testing';

import { ArmyLeaderService } from './army-leader.service';

describe('ArmyLeaderService', () => {
  let service: ArmyLeaderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ArmyLeaderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
