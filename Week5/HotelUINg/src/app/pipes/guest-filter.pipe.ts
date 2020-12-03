import { Pipe, PipeTransform } from '@angular/core';
import { Guest } from '../models/guest';

@Pipe({
  name: 'guestFilter' // used like "guestList | guestFilter(someValue)"
})
export class GuestFilterPipe implements PipeTransform {

  transform(value: string, ...guestList: Guest[]): Guest[] {

    let filteredGuestList = guestList.filter(
      (guest) => {if (guest.firstName.startsWith(value) || guest.lastName.startsWith(value)
        || guest.phoneNumber.startsWith(value) || guest.roomNumber.toString().startsWith(value))
        return true;
        else return false;
      }
    );

        /*  let filteredGuestList = guestList.filter(
      (guest) => {if (Object.values(guest).map(element => {
        return <string>element.startsWith(value)
      }).reduce<boolean>(previous, next) => {return previous || next})}
    ); */

    return filteredGuestList;
  }

}
