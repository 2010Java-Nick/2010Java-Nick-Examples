package HotelReservationSpring.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import HotelReservationSpring.pojos.Room;

@Repository
public class RoomDaoHibernate implements RoomDao {

    //SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactoryUtil().getSessionFactory();  
	
	private SessionFactory sessionFactory;
	
	
    @Autowired
    @Qualifier("mySessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
    public void createRoom(Room room) {
        // TODO Auto-generated method stub

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED) // [P2OFFLIMITS]
    public Room readRoom(int roomId) {
        return sessionFactory.getCurrentSession().get(Room.class, roomId);  //[P2OFFLIMITS]
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
