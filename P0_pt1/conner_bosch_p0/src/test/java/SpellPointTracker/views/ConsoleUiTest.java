package SpellPointTracker.views;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
//import org.apache.log4j.Logger;

import SpellPointTracker.controllers.SpellPointsController;

@RunWith(MockitoJUnitRunner.class)
public class ConsoleUiTest {

	//private static Logger Log = Logger.getLogger("consoleUILog");

	@Mock
	private SpellPointsController control;
	@Mock
	private BufferedReader input;
	private ConsoleUI userInterface;

	List<String> spells = new ArrayList<>();

    @BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		spells.add("cantrip");
		when(input.ready()).thenReturn(true);
		when(input.markSupported()).thenReturn(true);
		when(control.setCurrentPlayer("daveThePlayer", "password1")).thenReturn(true);
		when(control.createNewPlayer("daveThePlayer", "password1", 2, 0)).thenReturn(true);
		when(control.castSpell("cantrip")).thenReturn(true);
		when(control.getAvailableSpellNames()).thenReturn(spells);
		userInterface = Mockito.spy(new ConsoleUI(control, input));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void startInterfaceTest() {

		try{
			when(input.readLine()).thenReturn("Login");
		} catch (IOException e) {
			fail("readLine failed:" + e);
		}
		userInterface.startInterface();
		verify(userInterface).promptLogin();

		try {
			when(input.readLine()).thenReturn("Create");
		} catch (IOException e) {
			fail("readLine failed:" + e);
		}
		userInterface.startInterface();
		verify(userInterface).promptUserCreate();
	}

	@Test
	public void promptActionTest() {
		//Test the result of Cast input
		try{
			when(input.readLine()).thenReturn("Cast");
		} catch (IOException e) {
			fail("readLine failed:" + e);
		}
		userInterface.promptAction();
		verify(control).getStatus();
		verify(userInterface).castSpell();

		//Test the result of Rest input
		try {
			when(input.readLine()).thenReturn("Rest");
		} catch (IOException e) {
			fail("readLine failed:" + e);
		}
		userInterface.promptAction();
		verify(userInterface).rest();
	}

	@Test
	public void promptLoginTest() {
		//Verify correct passing of username password
		try {
			when(input.readLine()).thenReturn("daveThePlayer").thenReturn("password1");
		} catch (IOException e) {
			fail("readLine failed:" + e);
		}
		assertTrue("Method Returned false", userInterface.promptLogin());
		verify(control).setCurrentPlayer("daveThePlayer", "password1");

		//Verify invalid input flagged
		try {
			when(input.readLine()).thenReturn("dave_*$@%n").thenReturn("pass");
		} catch (IOException e) {
			fail("readLine failed:" + e);
		}
		userInterface.promptLogin();
	}

	@Test
	public void promptUserCreateTest() {
		//Verify correct creation input
		try {
			when(input.readLine()).thenReturn("daveThePlayer").thenReturn("password1").thenReturn("2").thenReturn("0");
		} catch (IOException e) {
			fail("readLine failed:" + e);
		}
		assertTrue("Method returned false", userInterface.promptUserCreate());
		verify(control).createNewPlayer("daveThePlayer", "password1", 2, 0);

		//Verify incorrect creation input recursively calls
		try {
			when(input.readLine()).thenReturn("dav#$%n_=;").thenReturn("pas").thenReturn("asdf").thenReturn("asdf");
		} catch (IOException e) {
			fail("readLine failed:" + e);
		}
		assertFalse("Method returned true when expected false", userInterface.promptUserCreate());	
	}

	@Test
	public void castSpellTest() {
		//Verify correct spell cast input
		try {
			when(input.readLine()).thenReturn("cantrip");
		} catch (IOException e) {
			fail("readLine failed:" + e);
		}
		assertTrue("Method returned false", userInterface.castSpell());
		verify(control).getAvailableSpellNames();
		verify(control).castSpell("cantrip");

		//Verify incorrect spell cast input
		try {
			when(input.readLine()).thenReturn("as24^$(#%n");
		} catch (IOException e) {
			fail("readLine failed:" + e);
		}
		assertFalse("Returned true when false expected", userInterface.castSpell());
	}

	@Test
	public void restTest() {
		assertTrue("Method returned false", userInterface.rest());
		verify(control).rest();
	}
}
