package MoneyApp.Service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import MoneyAppPojos.Bank;
import MoneyAppPojos.Credit;
import MoneyAppServices.CreateMoneyImpl;


public class CreateMoneyTests {
	
	private CreateMoneyImpl testObj; 
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		testObj = new CreateMoneyImpl();

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createBankTest() {
		Bank bankRef = new Bank("Walls Fargo",16,"4544125332323","011401533");
		Bank bankTest = testObj.createBank("Walls Fargo",16,"4544125332323","011401533");
		
		assertEquals(true,bankTest.equals(bankRef));
		
	}
	
	@Test
	public void createCreditTest() {
		Credit creditRef = new Credit("4003846354018", "Visa", 1123, 123,100);
		Credit creditTest = testObj.createCredit("4003846354018", "Visa", 1123, 123,100);
		
		assertEquals(true,creditTest.equals(creditRef));
		
	}

}
