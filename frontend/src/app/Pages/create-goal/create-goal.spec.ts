import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateGoal } from './create-goal';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';
import {CallAPI} from '../../Services/call-api';

describe('CreateGoal', () => {
  let component: CreateGoal;
  let fixture: ComponentFixture<CreateGoal>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateGoal],
      providers: [
        provideHttpClient(),
        provideHttpClientTesting(),
        CallAPI
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateGoal);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
