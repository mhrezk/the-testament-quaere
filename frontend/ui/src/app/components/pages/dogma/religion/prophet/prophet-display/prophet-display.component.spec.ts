import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProphetDisplayComponent } from './prophet-display.component';

describe('ProphetDisplayComponent', () => {
  let component: ProphetDisplayComponent;
  let fixture: ComponentFixture<ProphetDisplayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ProphetDisplayComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ProphetDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
