import { Injectable } from '@angular/core';
import { Guest } from '../models/guest';

@Injectable({
  providedIn: 'root'
})
export class GuestService {

  private guestList: Guest[];

  constructor() {

    this.guestList = new Array<Guest>();
    this.guestList.push({firstName: `Acacia`, lastName: `Holliday`, roomNumber: 456, phone: `5555555`});
    this.guestList.push({firstName: `Michael`, lastName: `McAuliffe`, roomNumber: 789, phone: `5555555`});
    this.guestList.push({firstName: `Chris`, lastName: `Breniser`, roomNumber: 123, phone: `5555555`});

   }

  public getGuestList(): Guest[] {

    return this.guestList;

  }

}
