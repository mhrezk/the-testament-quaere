import { TestBed } from '@angular/core/testing';

import { SquadLeaderService } from './squad-leader.service';

describe('SquadLeaderService', () => {
  let service: SquadLeaderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SquadLeaderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
