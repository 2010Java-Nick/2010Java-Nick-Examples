package HotelReservation.service.test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import HotelReservation.pojos.Guest;
import HotelReservation.pojos.Room;
import HotelReservation.pojos.Room.RoomType;
import HotelReservation.service.CustomCacheServiceSimpleInMemory;

public class CustomCacheServiceSimpleInMemoryTest {
	
	private CustomCacheServiceSimpleInMemory<Guest> cacheService;
	
	static private Set<Guest> testCache;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		testCache = new HashSet<>();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		Room room1 = new Room(2,false, RoomType.DOUBLE_BED, true, 101);
		
		Room room2 = new Room(1, true, RoomType.SINGLE_BED, false, 201);
		
		Guest guest1 = new Guest("Jack Skelton", room1, "555-555-5555", 200.22);
		
		Guest guest2 = new Guest("Николас Юрча", room1, "246-189-1987", 65.32);
		
		Guest guest3 = new Guest("Norm Normal", room2, "3365951122", 0.0);
		
		Guest guest4 = new Guest(null, null, null, 0.0);
		
		testCache.add(guest1);
		testCache.add(guest2);
		testCache.add(guest3);
		testCache.add(guest4);
		
		cacheService = new CustomCacheServiceSimpleInMemory<Guest>(testCache);

	}

	@After
	public void tearDown() throws Exception {
		
		testCache.clear();
		
	}

	@Test
	public void addToCacheSimpleGuestTest() {
		
		Guest guestToAdd = new Guest("Sally Skelton", new Room(4, true, RoomType.LUXURY_SUITE, true, 666), "00000000000", 666.00);
		
		cacheService.addToCache(guestToAdd);
		
		assertEquals("Guest " + guestToAdd + " should be in the cache", true, testCache.contains(guestToAdd));
		
	}

}
