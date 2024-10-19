import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubRaceComponent } from './sub-race.component';

describe('SubRaceComponent', () => {
  let component: SubRaceComponent;
  let fixture: ComponentFixture<SubRaceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SubRaceComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SubRaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
