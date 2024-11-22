import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeityDisplayEditComponent } from './deity-display-edit.component';

describe('DeityDisplayEditComponent', () => {
  let component: DeityDisplayEditComponent;
  let fixture: ComponentFixture<DeityDisplayEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DeityDisplayEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DeityDisplayEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
