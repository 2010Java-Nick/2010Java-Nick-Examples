package HotelReservationSpring.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Repository;

import HotelReservationSpring.pojos.Guest;
import HotelReservationSpring.util.GuestUpdateException;
import HotelReservationSpring.util.SessionFactoryUtil;

//at runtime, Spring is going to instantiate an instance of our GuestDaoHibernate
//this instance will be placed in our IOC container/DI container/Spring Bean Container
//Bean Factory vs. ApplicationContext
//It would then be a Spring Bean
@Repository
public class GuestDaoHibernate implements GuestDao {//, BeanNameAware{

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
	public Guest updateGuest(int guestId, Guest guest) throws GuestUpdateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteGuest(Guest guest) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * @Override public void setBeanName(String name) {
	 * System.out.println("Bean name set to " + name); }
	 */

}
