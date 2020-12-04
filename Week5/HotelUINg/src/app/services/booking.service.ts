import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Booking } from '../models/booking';

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  private readonly BOOKING_URL = "http://localhost:9091/booking";

  constructor(private httpClient: HttpClient) {

  }

  public getBookingList(): Observable<Booking[]> {

    return this.httpClient.get<Booking[]>(this.BOOKING_URL);

  }
}
