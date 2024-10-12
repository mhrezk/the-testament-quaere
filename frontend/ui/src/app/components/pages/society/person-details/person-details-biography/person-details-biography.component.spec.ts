import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonDetailsBiographyComponent } from './person-details-biography.component';

describe('PersonDetailsBiographyComponent', () => {
  let component: PersonDetailsBiographyComponent;
  let fixture: ComponentFixture<PersonDetailsBiographyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PersonDetailsBiographyComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PersonDetailsBiographyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
