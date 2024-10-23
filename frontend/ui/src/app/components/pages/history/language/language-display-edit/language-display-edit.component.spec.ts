import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LanguageDisplayEditComponent } from './language-display-edit.component';

describe('LanguageDisplayEditComponent', () => {
  let component: LanguageDisplayEditComponent;
  let fixture: ComponentFixture<LanguageDisplayEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LanguageDisplayEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LanguageDisplayEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
