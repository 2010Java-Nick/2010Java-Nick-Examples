package SpellPointTracker.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
//import org.apache.log4j.Logger;

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
	@Mock
	private CalculatorService calcService;

	private SpellPointsController control;
	private String username;
	private String password;
	private int level;
	private int casterType;
	private Player player;
	private String status;
	private Spell spellOne;
	private Spell spellTwo;
	private List<Spell> spells;
	private List<String> spellNames;
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
		level = 2;
		casterType = 0;
		player = new Player(1, username, password, 0, level, casterType);
		status = "Player 1: daveTheGamer Level 2 Bard%nAvailable Spell Points: 20 ";

		
		spellOne = new Spell(0, "cantrip", 0, 0);
		spellTwo = new Spell(1, "magic missle", 1, 4);
		spellIds = new int[]{0, 1};
		spellNames = new ArrayList<>();
		spellNames.add("cantrip");
		spellNames.add("magic missle");
		spells = new ArrayList<Spell>();
		spells.add(spellOne);
		spells.add(spellTwo);


		when(playerService.getPlayer(username, password)).thenReturn(player);
		when(playerService.createPlayer(username, password, level, casterType)).thenReturn(true);

		when(calcService.getCurrentPlayer()).thenReturn(player);
		when(calcService.setCurrentPlayer(player)).thenReturn(true);
		when(calcService.castSpell(spellOne)).thenReturn(true);
		when(calcService.getStatus()).thenReturn("Player 1: daveTheGamer Level 2 Bard%nAvailable Spell Points: 20 ");

		when(casterService.getCastersSpells(player.getCasterType())).thenReturn(spellIds);
		when(casterService.getMaxPoints(0, level)).thenReturn(20);

		when(spellService.getSpells(spellIds)).thenReturn(spells);
		when(spellService.getSpell("cantrip")).thenReturn(spellOne);

		control = new SpellPointsController(casterService, playerService, spellService, calcService);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void setCurrentPlayerTest() {
		assertTrue("setCurrentPlayer returned False", control.setCurrentPlayer(username, password));
		verify(playerService).getPlayer(username, password);
		verify(calcService).setCurrentPlayer(player);
		verify(casterService).getCastersSpells(player.getCasterType());
		verify(spellService).getSpells(spellIds);
	}

	@Test
	public void createNewPlayerTest() {
		assertTrue("createNewPlayer returned False", control.createNewPlayer(username, password, level, casterType));
		verify(playerService).createPlayer(username, password, level, casterType);
	}

	@Test
	public void castSpellTest() {
		assertTrue("Spell was not properly cast", control.castSpell("cantrip"));
		verify(spellService).getSpell("cantrip");
		verify(calcService).castSpell(spellOne);
	}

	@Test
	public void restTest(){
		control.rest();
		verify(calcService).getCurrentPlayer();
		verify(casterService).getMaxPoints(0, level);
		verify(calcService).rest(20);
	}

	@Test
	public void getStatusTest(){
		String testStatus = control.getStatus();
		assertTrue("Status returned does not match expected", status.equals(testStatus));
		verify(calcService).getStatus();
	}
}
