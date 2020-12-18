package com.revature.hotelservice.service;

import java.util.List;

import com.revature.hotelservice.pojo.Room;

public interface RoomService {

	public Room getRoomById(int id);

	public List<Room> getAllRoom();

	public Room makeRoom(Room room);

	public void removeRoom(int id);

	public void updateRoom(int id, Room room);

}
