import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthorDisplayEditComponent } from './author-display-edit.component';

describe('AuthorDisplayEditComponent', () => {
  let component: AuthorDisplayEditComponent;
  let fixture: ComponentFixture<AuthorDisplayEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AuthorDisplayEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AuthorDisplayEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
