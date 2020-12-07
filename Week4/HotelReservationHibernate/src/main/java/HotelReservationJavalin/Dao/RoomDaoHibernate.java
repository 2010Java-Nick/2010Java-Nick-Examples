package HotelReservationJavalin.Dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import HotelReservationJavalin.util.GuestUpdateException;
import HotelReservationJavalin.util.SessionFactoryUtil;

import HotelReservationJavalin.pojos.Room;

public class RoomDaoHibernate implements RoomDao {

    SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactoryUtil().getSessionFactory();   
    
    @Override
    public void createRoom(Room room) {
        // TODO Auto-generated method stub

    }

    @Override
    public Room readRoom(int roomId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Room> readAllRooms() {
        List<Room> roomList = null;
		Session sess = sessionFactory.openSession();
		CriteriaBuilder cb = sess.getCriteriaBuilder();
		CriteriaQuery<Room> cq = cb.createQuery(Room.class);
		Root<Room> rootEntry = cq.from(Room.class);
		CriteriaQuery<Room> all = cq.select(rootEntry);
		TypedQuery<Room> allQuery = sess.createQuery(all);
		roomList = allQuery.getResultList();
		sess.close();
		return roomList;
    }

    @Override
    public Room updateRoom(int roomId, Room room){
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteRoom(Room room) {
        // TODO Auto-generated method stub

    }
    
}
