import { TestBed } from '@angular/core/testing';

import { ExcerptService } from './excerpt.service';

describe('ExcerptService', () => {
  let service: ExcerptService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ExcerptService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
