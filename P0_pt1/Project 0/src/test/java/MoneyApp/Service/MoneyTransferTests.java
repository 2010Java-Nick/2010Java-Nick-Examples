package MoneyApp.Service;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import MoneyAppPojos.Bank;
import MoneyAppPojos.Credit;
import MoneyAppServices.MoneyTransferServiceImpl;


public class MoneyTransferTests {
	
	private Bank testBank1;
	private Bank testBank2;
		
	private Credit testCredit1;
	private Credit testCredit2;
	
	MoneyTransferServiceImpl creditSend = new MoneyTransferServiceImpl();
	MoneyTransferServiceImpl fundsAdded = new MoneyTransferServiceImpl();
	MoneyTransferServiceImpl fundsSubtracted = new MoneyTransferServiceImpl();
	
	double amount;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		testBank1 = new Bank("BofA",10,"45485672634587123","091000019");
		testBank2 = new Bank("Walls Fargo",10,"45444163123","011401533");
		
		testCredit1 = new Credit("4003830171874018", "Visa", 1123, 123,10);
		testCredit2 = new Credit("5496198584584769", "Mastercard", 0121, 456,4);
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void sendMoneyTest() {
		amount = 5.25;
		assertTrue("Transfer should be successful",creditSend.SendMoney(testCredit1,testCredit2, amount));
		assertEquals("Value should be added",true, 4.75==testCredit1.getBalance());
		assertEquals("Value should be subtracted",true, 9.25==testCredit2.getBalance());
	
	}
	
	@Test
	public void sendMoneyTestFail() {
		amount = 5.25;
		assertFalse("Transfer should be unsuccessful",creditSend.SendMoney(testCredit2,testCredit1, amount));
		assertEquals("Value should be unchanged",true, 10==testCredit1.getBalance());
		assertEquals("Value should be unchanged",true, 4==testCredit2.getBalance());
	}
	
	@Test
	public void addFundsTest() {
		amount = 1.25;
		assertTrue("Transfer should be successful",fundsAdded.AddFunds(testBank1, testCredit1,amount));
		assertEquals("Value should be added",true, 8.75==testBank1.getCurrentBalance());
		assertEquals("Value should be subtracted",true, 11.25==testCredit1.getBalance());
		
	}
	
	@Test
	public void addFundsTestFail() {
		amount = 1300;
		assertFalse("Transfer should be unsuccessful",fundsAdded.AddFunds(testBank1, testCredit1,amount));
		assertEquals("Value should be unchanged",true, 10==testBank1.getCurrentBalance());
		assertEquals("Value should be unchanged",true, 10==testCredit1.getBalance());
		
	}
	
	@Test
	public void removefundsTest() {
		amount = 3.25;
		assertTrue("Transfer should be successful",fundsSubtracted.Removefunds(testCredit2, testBank2,amount));
		assertEquals("Value should be subtracted",true, .75==testCredit2.getBalance());
		assertEquals("Value should be added",true, 13.25==testBank2.getCurrentBalance());
		
	}
	
	@Test
	public void removefundsTestFail() {
		amount = 40;
		assertFalse("Transfer should be unsuccessful",fundsSubtracted.Removefunds(testCredit2, testBank2,amount));
		assertEquals("Value should be unchanged",true, 4==testCredit2.getBalance());
		assertEquals("Value should be unchanged",true, 10==testBank2.getCurrentBalance());
		
	}
}
