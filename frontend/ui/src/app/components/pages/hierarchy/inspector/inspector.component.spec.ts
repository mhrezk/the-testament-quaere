import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InspectorComponent } from './inspector.component';

describe('InspectorComponent', () => {
  let component: InspectorComponent;
  let fixture: ComponentFixture<InspectorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [InspectorComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InspectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
