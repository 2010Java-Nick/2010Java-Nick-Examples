import { Component, Input, OnInit } from '@angular/core';
import { Guest } from 'src/app/models/guest';

@Component({
  selector: '.app-guest-list-item',
  templateUrl: './guest-list-item.component.html',
  styleUrls: ['./guest-list-item.component.css']
})
export class GuestListItemComponent implements OnInit {

  @Input()
  guest!: Guest;

  constructor() { }

  ngOnInit(): void {
  }

}
