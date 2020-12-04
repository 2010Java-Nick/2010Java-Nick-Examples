import { Component, OnInit } from '@angular/core';
import { Room } from 'src/app/models/room';
import { RoomService } from 'src/app/services/room.service';

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.css']
})
export class RoomListComponent implements OnInit {

  searchValue = ``;

  tableStyle = {color: `blue`};

  roomList: Room[] = new Array();

  searchValueInput(searchValue: string) {
    this.searchValue = searchValue;
  }

  constructor(private roomService: RoomService) { }

  ngOnInit(): void {
    this.roomService.getRoomList().subscribe(
      (result) => { this.roomList = result }
    );
  }

}
