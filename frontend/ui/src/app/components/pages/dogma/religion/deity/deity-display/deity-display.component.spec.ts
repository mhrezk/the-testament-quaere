import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeityDisplayComponent } from './deity-display.component';

describe('DeityDisplayComponent', () => {
  let component: DeityDisplayComponent;
  let fixture: ComponentFixture<DeityDisplayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DeityDisplayComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DeityDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
