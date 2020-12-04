import { Component, Input, OnInit } from '@angular/core';
import { Booking } from 'src/app/models/booking';

@Component({
  selector: '.app-booking-list-item',
  templateUrl: './booking-list-item.component.html',
  styleUrls: ['./booking-list-item.component.css']
})
export class BookingListItemComponent implements OnInit {


  @Input()
  booking!: Booking;

  constructor() { }

  ngOnInit(): void {
  }

}
