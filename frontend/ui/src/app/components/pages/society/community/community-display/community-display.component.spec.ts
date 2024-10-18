import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommunityDisplayComponent } from './community-display.component';

describe('CommunityDisplayComponent', () => {
  let component: CommunityDisplayComponent;
  let fixture: ComponentFixture<CommunityDisplayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CommunityDisplayComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CommunityDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
