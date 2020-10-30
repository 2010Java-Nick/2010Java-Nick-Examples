package SpellPointTracker.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
//import org.apache.log4j.Logger;

import SpellPointTracker.controllers.SpellPointsController;
import SpellPointTracker.pojos.*;
import SpellPointTracker.services.*;

@RunWith(MockitoJUnitRunner.class)
public class SpellPointsControllerTest {

	//private static Logger Log = Logger.getLogger("controllerLog");
	@Mock
	private CasterService casterService;
	@Mock
	private PlayerService playerService;
	@Mock
	private SpellService spellService;

	private SpellPointsController control;
	private String username;
	private String password;
	private Player player;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		username = "daveTheGamer";
		password = "password1!";
		player = new Player(1, username, password, 0, 0, 0);

		when(playerService.getPlayer(username, password)).thenReturn(player);
		control = new SpellPointsController(casterService, playerService, spellService);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void setCurrentPlayerTest() {
		assertTrue("setCurrentPlayer returned False", control.setCurrentPlayer(username, password));
		verify(playerService.getPlayer(username, password));
	}
}
