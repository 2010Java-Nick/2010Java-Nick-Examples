package HotelReservationSpring.controller;

import java.util.ArrayList;
import java.util.List;

import HotelReservationSpring.dto.RoomDto;
import HotelReservationSpring.pojos.Room;
import HotelReservationSpring.service.RoomService;
import io.javalin.http.Context;

public class RoomController {
    RoomService roomService = new RoomService();

    public void getAllRooms(Context ctx) {

        List<Room> roomList = roomService.getAllRooms();
        List<RoomDto> roomDtoList = new ArrayList<>();
		
		for (Room room : roomList) {
			roomDtoList.add(new RoomDto(room));
		}
        ctx.json(roomList);
        
    }
    
}
