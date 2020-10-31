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
import org.mockito.Spy;
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
	private int level;
	private int casterType;
	private Player player;
	private Spell spellOne;
	private Spell spellTwo;
	private Spell[] spells;
	private int[] spellIds;

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
		level = 0;
		casterType = 0;
		
		spellOne = new Spell(0, "cantrip", 0, 0);
		spellTwo = new Spell(1, "magic missle", 1, 4);
		spellIds = new int[]{0, 1};
		spells = new Spell[]{spellOne, spellTwo};

		player = new Player(1, username, password, 0, 0, 0);

		when(playerService.getPlayer(username, password)).thenReturn(player);
		when(spellService.getSpells(spellIds)).thenReturn(spells);
		control = new SpellPointsController(casterService, playerService, spellService);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void setCurrentPlayerTest() {
		assertTrue("setCurrentPlayer returned False", control.setCurrentPlayer(username, password));
		assertTrue("setCurrentPlayer did not change currentPlayer", (control.getCurrentPlayer().getUsername().equals(username)));
		verify(playerService).getPlayer(username, password);
	}

	@Test
	public void createNewPlayerTest() {
		assertTrue("createNewPlayer returned False", control.createNewPlayer(username, password, level, casterType));
		verify(playerService).createPlayer(username, password, level, casterType);
	}

	@Test
	public void getAvailableSpellsTest() {
		assertTrue("Spells not returned properly", control.getAvailableSpells().equals(spells));
		verify(spellService).getSpells(spellIds);
	}
}
