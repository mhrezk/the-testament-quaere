import { TestBed } from '@angular/core/testing';

import { PunditService } from './pundit.service';

describe('PunditService', () => {
  let service: PunditService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PunditService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
