import { TestBed } from '@angular/core/testing';

import { NationDetailsService } from './nation-details.service';

describe('NationDetailsService', () => {
  let service: NationDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NationDetailsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
