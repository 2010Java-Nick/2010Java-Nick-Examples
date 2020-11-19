package HotelReservationJavalin.Dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import HotelReservationJavalin.pojos.Guest;
import HotelReservationJavalin.util.GuestUpdateException;
import HotelReservationJavalin.util.SessionFactoryUtil;

public class GuestDaoHibernate implements GuestDao{

	SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactoryUtil().getSessionFactory();
	
	@Override
	public void createGuest(Guest guest) {
		
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		sess.save(guest);
		tx.commit();
		sess.close();
	}

	@Override
	public Guest readGuest(int guestId) {
		// TODO Auto-generated method stub
		Guest g;
		Session sess = sessionFactory.openSession();
		g = sess.get(Guest.class, guestId);
		sess.close();
		return g;
	}

	@Override
	public List<Guest> readAllGuests() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Guest updateGuest(int guestId, Guest guest) throws GuestUpdateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteGuest(Guest guest) {
		// TODO Auto-generated method stub
		
	}

}
