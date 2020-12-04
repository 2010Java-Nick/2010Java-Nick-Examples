import { HttpClientTestingModule } from '@angular/common/http/testing';
import { Pipe, PipeTransform } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Guest } from 'src/app/models/guest';
import { Transform } from 'stream';

import { GuestListComponent } from './guest-list.component';

@Pipe({
  name: 'guestFilter'
})
class MockGuestFilterPipe implements PipeTransform {

  transform(guestList: Guest[], value: string): Guest[] {
    
    if (value === '123') {
      return [ { firstName: '', lastName: '', roomNumber: 123, phoneNumber: ''} ];
    }
    
    return [];
  }

}


describe('GuestListComponent', () => {
  let component: GuestListComponent;
  let fixture: ComponentFixture<GuestListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GuestListComponent,
        MockGuestFilterPipe],
      imports: [HttpClientTestingModule]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GuestListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
