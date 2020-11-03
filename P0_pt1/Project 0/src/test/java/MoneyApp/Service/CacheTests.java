package MoneyApp.Service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import MoneyAppPojos.Bank;
import MoneyAppPojos.Credit;
import MoneyAppPojos.User;
import MoneyAppServices.CacheServiceSIM;

public class CacheTests {
	
	private CacheServiceSIM<User> cacheServiceUser;
	private CacheServiceSIM<Bank> cacheServiceBank;
	private CacheServiceSIM<Credit> cacheServiceCredit;
	
	static private  Map<String,User> testUserCache;
	static private  Map<String,Bank> testBankCache;
	static private  Map<String,Credit> testCreditCache;
	
	private User testUser1;
	private User testUser2;
	
	private Bank testBank1;
	private Bank testBank2;
		
	private Credit testCredit1;
	private Credit testCredit2;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testUserCache = new HashMap<>();
		testBankCache = new HashMap<>();
		testCreditCache = new HashMap<>();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		testUser1 = new User("Michael Zide", "Mzide", "rEaLlYcLeVeR", "myemail24@gmail.com", "1234567890");
		testUser2 = new User("Patrick Robertson", "bmxer4life", "HelloWorldhehe!!", "bmxer4life@hotmail.com", "(465)113-1656");

		testUserCache.put(testUser1.getUsername(),testUser1);
		testUserCache.put(testUser2.getUsername(),testUser2);
		
		
		cacheServiceUser = new CacheServiceSIM<User>(testUserCache);
		
		testBank1 = new Bank("BofA",1212,"45485672634587123","091000019");
		testBank2 = new Bank("Walls Fargo",-10,"45444163123","011401533");

		
		testBankCache.put(testBank1.getAccountNumber(),testBank1);
		testBankCache.put(testBank2.getAccountNumber(),testBank2);
	
		cacheServiceBank = new CacheServiceSIM<Bank>(testBankCache);
		
		testCredit1 = new Credit("4003830171874018", "Visa", 1123, 123,121);
		testCredit2 = new Credit("5496198584584769", "Mastercard", 0121, 456,121);

		testCreditCache.put(testCredit1.getCardNum(),testCredit1);
		testCreditCache.put(testCredit2.getCardNum(),testCredit2);

		
		cacheServiceCredit = new CacheServiceSIM<Credit>(testCreditCache);
	}

	@After
	public void tearDown() throws Exception {
		testUserCache.clear();
	}

	@Test
	public void addToCacheTest() {
		
		User cachedUserTest = new User("Michael Myers", "HalloweenLuvr1031", "Iliketoslash00?!", "epichalloween@greatmovies.org", "123213223");
		Bank cachedBankTest = new Bank("Walls Fargo",16,"4544125332323","011401533");
		Credit cachedCreditTest = new Credit("4003846354018", "Visa", 1123, 123,555);
		
		cacheServiceUser.addToCache(cachedUserTest.getUsername(),cachedUserTest);
		cacheServiceBank.addToCache(cachedBankTest.getAccountNumber(),cachedBankTest);
		cacheServiceCredit.addToCache(cachedCreditTest.getCardNum(),cachedCreditTest);
		
		assertTrue("User " + cachedUserTest + " should be in the cache", testUserCache.containsValue(cachedUserTest));
		assertTrue("Bank " + cachedBankTest + " should be in the cache", testBankCache.containsValue(cachedBankTest));
		assertTrue("Credit " + cachedCreditTest + " should be in the cache", testCreditCache.containsValue(cachedCreditTest));
	}
	
	@Test
	public void retrieveItemFromCacheTest() {
		
		String testUsername = testUser1.getUsername();
		User retrievedUser =  cacheServiceUser.retrieveItemFromCache(testUsername);
		
		assertEquals("If username exists in cache, return true",true, testUser1.equals(retrievedUser));
	}
	
	/*@Test
	public void remFromCacheTes() {
		
		//testUserCache.remove(testUser1);
		cacheServiceUser.remFromCache(testUser1.getUsername());
		
		assertFalse("If user is removed, return true", cacheServiceUser.getCache().containsKey(testUser1.getUsername()));
		
	}*/

}
