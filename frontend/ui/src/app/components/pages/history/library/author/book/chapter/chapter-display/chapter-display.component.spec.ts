import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChapterDisplayComponent } from './chapter-display.component';

describe('ChapterDisplayComponent', () => {
  let component: ChapterDisplayComponent;
  let fixture: ComponentFixture<ChapterDisplayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ChapterDisplayComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ChapterDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
