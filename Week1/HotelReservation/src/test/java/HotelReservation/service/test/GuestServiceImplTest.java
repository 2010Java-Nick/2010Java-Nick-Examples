package HotelReservation.service.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import HotelReservation.pojos.Guest;
import HotelReservation.pojos.Room;
import HotelReservation.pojos.Room.RoomType;
import HotelReservation.service.CustomCacheService;
import HotelReservation.service.GuestServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class GuestServiceImplTest {
	
	private GuestServiceImpl guestService;
	
	@Mock
	private CustomCacheService<Guest> customCache; 
	
	List<Guest> guestList;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		guestList = new ArrayList<Guest>();
		
		Room room1 = new Room(2,false, RoomType.DOUBLE_BED, true, 101);
		
		Room room2 = new Room(1, true, RoomType.SINGLE_BED, false, 201);
		
		Guest guest1 = new Guest("Jack Skelton", room1, "555-555-5555", 200.22);
		
		Guest guest2 = new Guest("Николас Юрча", room1, "246-189-1987", 65.32);
		
		Guest guest3 = new Guest("Norm Normal", room2, "3365951122", 0.0);
		
		Guest guest4 = new Guest(null, null, null, 0.0);
		
		guestList.add(guest1);
		guestList.add(guest2);
		guestList.add(guest3);
		guestList.add(guest4);
		
		when((customCache.retrieveAllItems())).thenReturn(guestList);
		guestService = new GuestServiceImpl(customCache);
		
	}

	@After
	public void tearDown() throws Exception {
		
		guestList.clear();
		
	}

	@Test
	public void createSimpleGuestTest() {
		
		Room room1 = new Room(2,false, RoomType.DOUBLE_BED, true, 101);
		Guest testGuest = guestService.createGuest("Jack Skelton", "555-555-5555", 200.22, room1);
		assertEquals("Should create guest object", "Jack Skelton", testGuest.getName());
		verify(customCache).addToCache(testGuest);
		
	}
	
	@Test
	public void getSimpleGuestListTest() {
		
		assertEquals("Should return full list of guests", guestList, guestService.getAllGuests());
		
	}

}
