package HotelReservationSpring.service;

import java.util.List;

import HotelReservationSpring.dao.RoomDao;
import HotelReservationSpring.dao.RoomDaoHibernate;
import HotelReservationSpring.pojos.Room;

public class RoomService {
    private RoomDao getRooms = new RoomDaoHibernate();

    public List<Room> getAllRooms(){
        return getRooms.readAllRooms();
    }
}
