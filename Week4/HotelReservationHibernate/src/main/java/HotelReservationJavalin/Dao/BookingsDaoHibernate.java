package HotelReservationJavalin.Dao;
import HotelReservationJavalin.pojos.Booking;
import HotelReservationJavalin.util.SessionFactoryUtil;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class BookingsDaoHibernate {


		SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactoryUtil().getSessionFactory();
		
		

		@Override
		public List<Booking> readAllBookings() {
			List<Booking> books = null;
			try(Session sess = sessionFactory.openSession()){
				CriteriaBuilder cb = sess.getCriteriaBuilder();
				CriteriaQuery<Booking> query = cb.createQuery(Booking.class);
				Root<Booking> root = query.from(Booking.class);
				CriteriaQuery<Booking> all = query.select(root);
				TypedQuery<Booking> allQuery = sess.createQuery(all);
				books = allQuery.getResultList();
			}catch(Exception e) {
				
			}
			return books;
		}

		

	

	
	
	public BookingsDaoHibernate() {
		// TODO Auto-generated constructor stub
	}

}
