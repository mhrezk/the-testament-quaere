import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FungusComponent } from './fungus.component';

describe('FungusComponent', () => {
  let component: FungusComponent;
  let fixture: ComponentFixture<FungusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FungusComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FungusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
