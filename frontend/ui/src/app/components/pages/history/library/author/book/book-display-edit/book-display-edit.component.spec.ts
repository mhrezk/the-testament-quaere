import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookDisplayEditComponent } from './book-display-edit.component';

describe('BookDisplayEditComponent', () => {
  let component: BookDisplayEditComponent;
  let fixture: ComponentFixture<BookDisplayEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BookDisplayEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BookDisplayEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
