import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProtozoanComponent } from './protozoan.component';

describe('ProtozoanComponent', () => {
  let component: ProtozoanComponent;
  let fixture: ComponentFixture<ProtozoanComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ProtozoanComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ProtozoanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
