import { Component, OnInit } from '@angular/core';
import { Guest } from 'src/app/models/guest';

@Component({
  selector: '.app-guest-list-item',
  templateUrl: './guest-list-item.component.html',
  styleUrls: ['./guest-list-item.component.css']
})
export class GuestListItemComponent implements OnInit {

  guest: Guest = {firstName: `Acacia `, lastName: `Holliday`, roomNumber: 456, phone: `5555555`};

  constructor() { }

  ngOnInit(): void {
  }

}
