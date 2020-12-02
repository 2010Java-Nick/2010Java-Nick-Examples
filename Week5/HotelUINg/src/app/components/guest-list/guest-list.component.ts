import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Guest } from 'src/app/models/guest';
import { GuestService } from 'src/app/services/guest.service';

@Component({
  selector: 'app-guest-list',
  templateUrl: './guest-list.component.html',
  styleUrls: ['./guest-list.component.css'],
  providers: []
})
export class GuestListComponent implements OnInit {

  tableStyle = {color: `blue`};

  guestList: Guest[] = new Array();

  constructor(private guestService: GuestService) { }

  ngOnInit(): void {
    this.guestService.getGuestList().subscribe(
      (result) => { this.guestList = result }
    );
  }

}
