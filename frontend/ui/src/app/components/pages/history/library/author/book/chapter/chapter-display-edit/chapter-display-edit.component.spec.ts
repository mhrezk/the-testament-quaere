import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChapterDisplayEditComponent } from './chapter-display-edit.component';

describe('ChapterDisplayEditComponent', () => {
  let component: ChapterDisplayEditComponent;
  let fixture: ComponentFixture<ChapterDisplayEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ChapterDisplayEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ChapterDisplayEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
