import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeityComponent } from './deity.component';

describe('DeityComponent', () => {
  let component: DeityComponent;
  let fixture: ComponentFixture<DeityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DeityComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DeityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
