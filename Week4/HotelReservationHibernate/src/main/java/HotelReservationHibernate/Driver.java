package HotelReservationHibernate;

import HotelReservationJavalin.Dao.GuestDaoHibernate;
import HotelReservationJavalin.pojos.Guest;
import HotelReservationJavalin.util.GuestUpdateException;

public class Driver {

	public static void main(String[] args) {
		
		Guest guest = new Guest("Zach", "leonardo", "573729163", 800.0);
		
		GuestDaoHibernate guestDao = new GuestDaoHibernate();
		
//		guestDao.createGuest(guest);
//		
//		Guest newGuest = guestDao.readGuest(39);
////		
//		System.out.println(newGuest.toString());
////		
//		System.out.println(guestDao.readAllGuests()); 
//		
//		try {
//			Guest newGuest1 = guestDao.updateGuest(2, guest);
//			System.out.println(newGuest1.toString());
//		} catch (GuestUpdateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		guestDao.deleteGuest(newGuest);

	}

}
