import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReligionDisplayEditComponent } from './religion-display-edit.component';

describe('ReligionDisplayEditComponent', () => {
  let component: ReligionDisplayEditComponent;
  let fixture: ComponentFixture<ReligionDisplayEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ReligionDisplayEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ReligionDisplayEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
