import { Pipe, PipeTransform } from '@angular/core';
import { Booking } from '../models/booking';

@Pipe({
  name: 'bookingFilter'
})
export class BookingFilterPipe implements PipeTransform {

  transform(bookingList: Booking[], value: string): Booking[] {

    let filteredBookingList = bookingList.filter(
      (book) => {
        if (book.checkIn.startsWith(value) || 
          book.checkOut.startsWith(value) || 
          book.hotelName.startsWith(value) || 
          book.roomNumber.toString().startsWith(value) ||
          this.checkGuestNames(book, value))
        return true;
        else return false;
      }
    );
    
    return filteredBookingList;
  }

  checkGuestNames(book: Booking, value: string): boolean {
    for (var name of book.guestNames){
      if (name.startsWith(value)){
        return true;
      }
    }
    return false;
  }
}
