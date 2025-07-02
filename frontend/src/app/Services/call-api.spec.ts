import { TestBed } from '@angular/core/testing';

import { CallAPI } from './call-api';

describe('CallAPI', () => {
  let service: CallAPI;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CallAPI);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
