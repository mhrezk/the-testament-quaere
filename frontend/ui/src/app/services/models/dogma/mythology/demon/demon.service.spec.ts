import { TestBed } from '@angular/core/testing';

import { DemonService } from './demon.service';

describe('DemonService', () => {
  let service: DemonService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DemonService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
