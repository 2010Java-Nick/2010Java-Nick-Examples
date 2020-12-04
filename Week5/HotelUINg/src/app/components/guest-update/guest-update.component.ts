import { Component, OnInit } from '@angular/core';
import { GuestService } from 'src/app/services/guest.service';

@Component({
  selector: 'app-guest-update',
  templateUrl: './guest-update.component.html',
  styleUrls: ['./guest-update.component.css']
})
export class GuestUpdateComponent implements OnInit {

  guestId: any;
  guestFirstName: any;
  guestLastName: any;
  guestPhoneNumber: any;

  constructor(private guestService: GuestService) { }

  ngOnInit(): void {
  }

  public formInput(){
    
  }
  //take in form data
  //create a guest object 
  //pass guest object to updateGuest()
}
