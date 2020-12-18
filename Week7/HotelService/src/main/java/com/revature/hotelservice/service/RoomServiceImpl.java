package com.revature.hotelservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.hotelservice.dao.RoomRepo;
import com.revature.hotelservice.pojo.Room;

@Service
public class RoomServiceImpl implements RoomService {

	private RoomRepo roomRepo;

	@Autowired
	public void setRoomRepo(RoomRepo roomRepo) {
		this.roomRepo = roomRepo;
	}

	@Override
	public Room getRoomById(int id) {
		return roomRepo.getOne(id);
	}

	@Override
	public List<Room> getAllRoom() {
		return roomRepo.findAll();
	}

	@Override
	public Room makeRoom(Room room) {
		return roomRepo.save(room);
	}

	@Override
	public void removeRoom(int id) {
		Room room = roomRepo.getOne(id);
		roomRepo.delete(room);
	}

	@Override
	public void updateRoom(int id, Room room) {
		Room existingRoom = roomRepo.getOne(id);
		if (existingRoom != null) {
			room.setRoomId(id);
			roomRepo.save(room);
		}

	}

}
