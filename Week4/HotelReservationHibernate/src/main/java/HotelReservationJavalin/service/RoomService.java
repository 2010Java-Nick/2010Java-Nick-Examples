package HotelReservationJavalin.service;

import java.util.List;

import HotelReservationJavalin.Dao.RoomDao;
import HotelReservationJavalin.Dao.RoomDaoHibernate;
import HotelReservationJavalin.pojos.Room;

public class RoomService {
    private RoomDao getRooms = new RoomDaoHibernate();

    public List<Room> getAllRooms(){
        return getRooms.readAllRooms();
    }
}
