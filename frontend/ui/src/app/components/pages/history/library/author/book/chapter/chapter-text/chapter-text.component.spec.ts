import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChapterTextComponent } from './chapter-text.component';

describe('ChapterTextComponent', () => {
  let component: ChapterTextComponent;
  let fixture: ComponentFixture<ChapterTextComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ChapterTextComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ChapterTextComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
