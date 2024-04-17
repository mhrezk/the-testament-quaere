import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomFamilyTreeComponent } from './custom-family-tree.component';

describe('CustomFamilyTreeComponent', () => {
  let component: CustomFamilyTreeComponent;
  let fixture: ComponentFixture<CustomFamilyTreeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CustomFamilyTreeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CustomFamilyTreeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
