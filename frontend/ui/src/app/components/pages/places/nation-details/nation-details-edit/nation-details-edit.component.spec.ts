import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NationDetailsEditComponent } from './nation-details-edit.component';

describe('NationDetailsEditComponent', () => {
  let component: NationDetailsEditComponent;
  let fixture: ComponentFixture<NationDetailsEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [NationDetailsEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NationDetailsEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
