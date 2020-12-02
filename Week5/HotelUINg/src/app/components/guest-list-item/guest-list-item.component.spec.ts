import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GuestListItemComponent } from './guest-list-item.component';

describe('GuestListItemComponent', () => {
  let component: GuestListItemComponent;
  let fixture: ComponentFixture<GuestListItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GuestListItemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GuestListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
