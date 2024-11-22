import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeityDescriptionComponent } from './deity-description.component';

describe('DeityDescriptionComponent', () => {
  let component: DeityDescriptionComponent;
  let fixture: ComponentFixture<DeityDescriptionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DeityDescriptionComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DeityDescriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
