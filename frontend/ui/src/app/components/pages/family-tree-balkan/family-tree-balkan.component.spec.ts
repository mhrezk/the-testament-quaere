import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FamilyTreeBalkanComponent } from './family-tree-balkan.component';

describe('FamilyTreeComponent', () => {
  let component: FamilyTreeBalkanComponent;
  let fixture: ComponentFixture<FamilyTreeBalkanComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FamilyTreeBalkanComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FamilyTreeBalkanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
