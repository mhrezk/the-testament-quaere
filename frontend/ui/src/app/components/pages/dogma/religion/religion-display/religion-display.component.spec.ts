import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReligionDisplayComponent } from './religion-display.component';

describe('ReligionDisplayComponent', () => {
  let component: ReligionDisplayComponent;
  let fixture: ComponentFixture<ReligionDisplayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ReligionDisplayComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ReligionDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
