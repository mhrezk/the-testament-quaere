import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonDetailsEditComponent } from './person-details-edit.component';

describe('PersonDetailsEditComponent', () => {
  let component: PersonDetailsEditComponent;
  let fixture: ComponentFixture<PersonDetailsEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PersonDetailsEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PersonDetailsEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
