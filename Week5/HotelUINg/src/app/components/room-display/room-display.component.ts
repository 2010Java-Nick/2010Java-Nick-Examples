import { Component, OnInit } from '@angular/core';
import { Room } from 'src/app/models/room';
import { RoomService } from 'src/app/services/room.service';

@Component({
  selector: 'app-room-display',
  templateUrl: './room-display.component.html',
  styleUrls: ['./room-display.component.css']
})
export class RoomDisplayComponent implements OnInit {

  searchValue = ``;

  tableStyle = {color: `blue`};

  room!: Room;

  searchValueInput(searchValue: string) {
    this.searchValue = searchValue;
  }

  constructor(private roomService: RoomService) { }

  ngOnInit(): void {
    this.roomService.getRoomDetails().subscribe(
      (result) => {this.room = result}
    );

  }

}
