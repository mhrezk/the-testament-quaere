import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FamilyNgTreeComponent } from './family-ng-tree.component';

describe('FamilyNgTreeComponent', () => {
  let component: FamilyNgTreeComponent;
  let fixture: ComponentFixture<FamilyNgTreeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FamilyNgTreeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FamilyNgTreeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
