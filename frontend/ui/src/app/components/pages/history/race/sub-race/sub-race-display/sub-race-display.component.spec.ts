import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubRaceDisplayComponent } from './sub-race-display.component';

describe('SubRaceDisplayComponent', () => {
  let component: SubRaceDisplayComponent;
  let fixture: ComponentFixture<SubRaceDisplayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SubRaceDisplayComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SubRaceDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
