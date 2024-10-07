import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PedigreeTreeComponent } from './pedigree-tree.component';

describe('PedigreeTreeComponent', () => {
  let component: PedigreeTreeComponent;
  let fixture: ComponentFixture<PedigreeTreeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PedigreeTreeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PedigreeTreeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
