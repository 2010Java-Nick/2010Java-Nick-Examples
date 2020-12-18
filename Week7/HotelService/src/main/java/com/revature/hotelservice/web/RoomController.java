package com.revature.hotelservice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.hotelservice.pojo.Room;
import com.revature.hotelservice.service.RoomService;

@RestController
public class RoomController {

	private RoomService roomService;

	@Autowired
	public void setroomService(RoomService roomService) {
		this.roomService = roomService;
	}
	
	@GetMapping("/room/{roomId}")
	public Room getroom(@PathVariable(name = "roomId") int roomId) {
		return roomService.getRoomById(roomId);
	}
	
	@GetMapping("/room")
	public List<Room> getAllrooms() {
		return roomService.getAllRoom();
	}
	
	@PostMapping("/room")
	public Room createroom(@RequestBody Room room) {
		return roomService.makeRoom(room);
	}
	
	@DeleteMapping("/room/{roomId}")
	public String deleteroom(@PathVariable("roomId")int roomId) {
		roomService.removeRoom(roomId);
		return "room successfully deleted";
	}
	
	@PutMapping("/room/{roomId}")
	public String updateroom(@PathVariable("roomId")int roomId, @RequestBody Room room) {
		roomService.updateRoom(roomId, room);
		return "room successfully updated";
	}
	
}
