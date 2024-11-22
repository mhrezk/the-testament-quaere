import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProphetDisplayEditComponent } from './prophet-display-edit.component';

describe('ProphetDisplayEditComponent', () => {
  let component: ProphetDisplayEditComponent;
  let fixture: ComponentFixture<ProphetDisplayEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ProphetDisplayEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ProphetDisplayEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
