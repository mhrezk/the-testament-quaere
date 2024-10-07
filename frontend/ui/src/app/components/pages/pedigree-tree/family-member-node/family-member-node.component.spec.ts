import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FamilyMemberNodeComponent } from './family-member-node.component';

describe('FamilyMemberNodeComponent', () => {
  let component: FamilyMemberNodeComponent;
  let fixture: ComponentFixture<FamilyMemberNodeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FamilyMemberNodeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FamilyMemberNodeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
