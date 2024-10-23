import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RaceDisplayEditComponent } from './race-display-edit.component';

describe('RaceDisplayEditComponent', () => {
  let component: RaceDisplayEditComponent;
  let fixture: ComponentFixture<RaceDisplayEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RaceDisplayEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RaceDisplayEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
