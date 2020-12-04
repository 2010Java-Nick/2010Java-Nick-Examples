import { Component, OnInit } from '@angular/core';
import { Booking } from 'src/app/models/booking';
import { BookingService } from 'src/app/services/booking.service';

@Component({
  selector: 'app-booking-list',
  templateUrl: './booking-list.component.html',
  styleUrls: ['./booking-list.component.css'],
  providers: []
})
export class BookingListComponent implements OnInit {

  searchValue = ``;

  tableStyle = {color: `blue`};

  bookingList: Booking[] = new Array();

  constructor(private bookingService: BookingService) { }

  ngOnInit(): void {
    this.bookingService.getBookingList().subscribe(
      (result) => { this.bookingList = result }
    );
  }

  searchValueInput(searchValue: string) {
    this.searchValue = searchValue;
  }

}
