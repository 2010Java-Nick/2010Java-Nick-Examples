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
import org.mockito.junit.MockitoJUnitRunner;

import SpellPointTracker.pojos.*;
import SpellPointTracker.services.*;

@RunWith(MockitoJUnitRunner.class)
public class SpellPointsControllerTest {
	
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
	private int points;
	private int casterType;
	private Player player;
	private String status;
	private Spell spellOne;
	private Spell spellTwo;
	private Spell spellThree;
	private List<Spell> spells;
	private List<String> spellNames;
	private Integer[] spellIds;
	private List<Caster> casters;
	private Caster caster;

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
		points = 6;
		player = new Player(1, username, password, 0, level, casterType);
		status = "Player 1: daveTheGamer Level 2 Bard%nAvailable Spell Points: 20 ";

		
		spellOne = new Spell(0, "cantrip", 0);
		spellTwo = new Spell(1, "magic missle", 1);
		spellThree = new Spell(2, "Wish", 9);
		spellIds = new Integer[]{0, 1, 2};
		spellNames = new ArrayList<>();
		spellNames.add("cantrip");
		spellNames.add("magic missle");
		spellNames.add("Wish");
		spells = new ArrayList<Spell>();
		spells.add(spellOne);
		spells.add(spellTwo);
		spells.add(spellThree);
		casters = new ArrayList<>();
		caster = new Caster(0, "Bard", false, spellIds);
		casters.add(caster);

		when(playerService.getPlayer(username, password)).thenReturn(player);
		when(playerService.createPlayer(username, password, points ,level, casterType)).thenReturn(true);

		when(calcService.getCurrentPlayer()).thenReturn(player);
		when(calcService.setCurrentPlayer(player)).thenReturn(true);
		when(calcService.castSpell(spellOne)).thenReturn(true);
		when(calcService.getStatus(casters)).thenReturn("Player 1: daveTheGamer Level 2 Bard%nAvailable Spell Points: 20 ");

		when(casterService.getMaxPoints(0, level)).thenReturn(6);
		when(casterService.getAllCasters()).thenReturn(casters);

		when(spellService.getSpell("cantrip")).thenReturn(spellOne);
		when(spellService.getSpell("Wish")).thenReturn(spellThree);

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
	}

	@Test
	public void createNewPlayerTest() {
		assertTrue("createNewPlayer returned False", control.createNewPlayer(username, password, level, casterType));
		verify(playerService).createPlayer(username, password, points, level, casterType);
	}

	@Test
	public void castSpellTest() {
		assertTrue("Spell was not properly cast", control.castSpell("cantrip"));
		assertFalse("Spell of too high level was cast", control.castSpell("Wish"));
		verify(spellService).getSpell("cantrip");
		verify(calcService).castSpell(spellOne);
	}

	@Test
	public void restTest(){
		control.rest();
		verify(calcService).getCurrentPlayer();
		verify(casterService).getMaxPoints(0, level);
		verify(calcService).rest(6);
	}

	@Test
	public void getStatusTest(){
		String testStatus = control.getStatus();
		assertTrue("Status returned does not match expected", status.equals(testStatus));
		verify(calcService).getStatus(casters);
	}
}
