import { TestBed } from '@angular/core/testing';

import { OceanService } from './ocean.service';

describe('OceanService', () => {
  let service: OceanService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OceanService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
