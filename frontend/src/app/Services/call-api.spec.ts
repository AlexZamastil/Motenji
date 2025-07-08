import { TestBed } from '@angular/core/testing';
import {provideHttpClientTesting} from '@angular/common/http/testing';
import { CallAPI } from './call-api';
import {provideHttpClient} from '@angular/common/http';

describe('CallAPI', () => {
  let service: CallAPI;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        provideHttpClient(),
        provideHttpClientTesting(),
        CallAPI
      ]
    });
    service = TestBed.inject(CallAPI);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
