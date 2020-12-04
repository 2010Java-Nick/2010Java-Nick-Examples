package HotelReservationJavalin.Dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
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
		List<Guest> guestList = null;
		Session sess = sessionFactory.openSession();
		CriteriaBuilder cb = sess.getCriteriaBuilder();
		CriteriaQuery<Guest> cq = cb.createQuery(Guest.class);
		Root<Guest> rootEntry = cq.from(Guest.class);
		CriteriaQuery<Guest> all = cq.select(rootEntry);
		TypedQuery<Guest> allQuery = sess.createQuery(all);
		guestList = allQuery.getResultList();
		sess.close();
		return guestList;
	}

	@Override
	public void updateGuest(int guestId, String firstName, String lastName, String phoneNumber, double payment){
	      Session session = sessionFactory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         Guest guest = 
	                    (Guest)session.get(Guest.class, guestId); 
	         guest.setFirstName(firstName);
	         guest.setLastName(lastName);
	         guest.setPhoneNumber(phoneNumber);
	         guest.setPayment(payment);
	         session.update(guest); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }

	@Override
	public void deleteGuest(Guest guest) {
		// TODO Auto-generated method stub
		
	}

}
