import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FaeComponent } from './fae.component';

describe('FaeComponent', () => {
  let component: FaeComponent;
  let fixture: ComponentFixture<FaeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FaeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FaeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
