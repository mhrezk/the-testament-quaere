import { TestBed } from '@angular/core/testing';

import { SubRaceService } from './sub-race.service';

describe('SubRaceService', () => {
  let service: SubRaceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SubRaceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
