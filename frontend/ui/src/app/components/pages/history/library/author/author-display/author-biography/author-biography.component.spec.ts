import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthorBiographyComponent } from './author-biography.component';

describe('AuthorBiographyComponent', () => {
  let component: AuthorBiographyComponent;
  let fixture: ComponentFixture<AuthorBiographyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AuthorBiographyComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AuthorBiographyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
