import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BacteriaComponent } from './bacteria.component';

describe('BacteriaComponent', () => {
  let component: BacteriaComponent;
  let fixture: ComponentFixture<BacteriaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BacteriaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BacteriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
