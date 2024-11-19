import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReligionDescriptionComponent } from './religion-description.component';

describe('ReligionDescriptionComponent', () => {
  let component: ReligionDescriptionComponent;
  let fixture: ComponentFixture<ReligionDescriptionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ReligionDescriptionComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ReligionDescriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
