import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParasiteComponent } from './parasite.component';

describe('ParasiteComponent', () => {
  let component: ParasiteComponent;
  let fixture: ComponentFixture<ParasiteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ParasiteComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ParasiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
