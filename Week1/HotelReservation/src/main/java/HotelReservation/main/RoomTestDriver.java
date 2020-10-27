package HotelReservation.main;

import HotelReservation.pojos.Room;
import HotelReservation.pojos.Room.RoomType;

public class RoomTestDriver {
	
	public static void main(String[] args) {
		
		Room room = new Room();
		
		System.out.println("Room: " + room);
		
		room.setBeds(2);
		room.setRoomNumber(107);
		room.setRoomService(false);
		room.setSmoking(false);
		room.setRoomType(RoomType.LUXURY_SUITE);
		
		System.out.println("Room: " + room);
		
	}

}
