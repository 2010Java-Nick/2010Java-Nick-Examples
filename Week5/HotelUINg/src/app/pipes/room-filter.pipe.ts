import { Pipe, PipeTransform } from '@angular/core';
import { Room } from '../models/room';

@Pipe({
  name: 'roomFilter'
})
export class RoomFilterPipe implements PipeTransform {

  transform(roomList: Room[], value: string): Room[] {
   
    let filteredRoomList = roomList.filter(
      (room) => {
        if (room.roomId.toString().startsWith(value) || 
            room.roomNumber.toString().startsWith(value) || 
            room.roomService.toString().startsWith(value) || 
            room.beds.toString().startsWith(value) ||
            room.roomType.startsWith(value) ||
            room.smoking.toString().startsWith(value))
              return true;
        
        else 
              return false;
      }
    );
 
    return roomList.filter((room) => (room.roomId.toString().startsWith(value) || 
                                      room.roomNumber.toString().startsWith(value) || 
                                      room.roomService.toString().startsWith(value) || 
                                      room.beds.toString().startsWith(value) ||
                                      room.roomType.startsWith(value) ||
                                      room.smoking.toString().startsWith(value)));
  }

}
