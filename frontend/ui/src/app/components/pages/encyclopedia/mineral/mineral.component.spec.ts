import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MineralComponent } from './mineral.component';

describe('MineralComponent', () => {
  let component: MineralComponent;
  let fixture: ComponentFixture<MineralComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MineralComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MineralComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
