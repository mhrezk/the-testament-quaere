import { TestBed } from '@angular/core/testing';

import { NationTypeService } from './nation-type.service';

describe('NationTypeService', () => {
  let service: NationTypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NationTypeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
