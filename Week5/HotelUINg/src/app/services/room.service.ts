import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Room } from '../models/room';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  private readonly ROOM_URL = "http://localhost:9091/room";

  constructor(private httpClient: HttpClient) { }

  public getRoomDetails(): Observable<Room>{

return this.httpClient.get<Room>(this.ROOM_URL);

  }
}


