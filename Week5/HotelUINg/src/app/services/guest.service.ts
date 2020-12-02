import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Guest } from '../models/guest';

@Injectable({
  providedIn: 'root'
})
export class GuestService {

  private readonly GUEST_URL = "http://localhost:9091/guest";

  constructor(private httpClient: HttpClient) {

    this.guestList = new Array<Guest>();

   }

  public getGuestList(): Observable<Guest[]> {

    return this.httpClient.get<Guest[]>(this.GUEST_URL);

  }

}
