import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NationDetailsHistoryComponent } from './nation-details-history.component';

describe('NationDetailsHistoryComponent', () => {
  let component: NationDetailsHistoryComponent;
  let fixture: ComponentFixture<NationDetailsHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [NationDetailsHistoryComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NationDetailsHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
