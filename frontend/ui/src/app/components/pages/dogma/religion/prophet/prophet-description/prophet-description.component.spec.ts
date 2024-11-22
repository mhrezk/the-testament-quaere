import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProphetDescriptionComponent } from './prophet-description.component';

describe('ProphetDescriptionComponent', () => {
  let component: ProphetDescriptionComponent;
  let fixture: ComponentFixture<ProphetDescriptionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ProphetDescriptionComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ProphetDescriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
