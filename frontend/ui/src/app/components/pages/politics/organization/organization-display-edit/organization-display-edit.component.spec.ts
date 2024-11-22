import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrganizationDisplayEditComponent } from './organization-display-edit.component';

describe('OrganizationDisplayEditComponent', () => {
  let component: OrganizationDisplayEditComponent;
  let fixture: ComponentFixture<OrganizationDisplayEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [OrganizationDisplayEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(OrganizationDisplayEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
