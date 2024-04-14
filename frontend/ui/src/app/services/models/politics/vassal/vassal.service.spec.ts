import { TestBed } from '@angular/core/testing';

import { VassalService } from './vassal.service';

describe('VassalService', () => {
  let service: VassalService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VassalService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
