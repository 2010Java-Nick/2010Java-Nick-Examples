package SpellPointTracker.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
//import org.apache.log4j.Logger;

import SpellPointTracker.pojos.Caster;
import SpellPointTracker.pojos.Player;


@RunWith(MockitoJUnitRunner.class)
public class CasterServiceImplTest {

	//private static Logger Log = Logger.getLogger("casterServiceLog");
	
	private CasterService casterService;
	private String username;
	private String password;
	private int level;
	private int casterType;
	private Player player;
	private Caster bard;
	private Caster cleric;
	private int[] maxPoints;
	private int[] spellIds;
	private int[] maxSpellLevels;
	private List<Caster> casters;


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
	player = new Player(1, username, password, 20, level, casterType);

	maxPoints = new int[]{4, 12, 20};
	spellIds = new int[]{0, 1};
	maxSpellLevels = new int[]{1, 1, 2, 2};
	bard = new Caster(1, "Bard", maxPoints, spellIds, maxSpellLevels);

	maxPoints = new int[]{0, 12, 20};
	spellIds = new int[]{0, 1, 3};
	maxSpellLevels = new int[]{0, 1, 2, 3};
	cleric = new Caster(2, "Cleric", maxPoints, spellIds, maxSpellLevels);

	casters = new ArrayList<>();
	casters.add(bard);
	casters.add(cleric);

	casterService = new CasterServiceImpl();
	casterService.setAllCasters(casters);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getCasterTest() {
		Caster retrieved = casterService.getCaster(bard.getId());
		assertTrue("Did not retrieve correct caster", retrieved.equals(bard));

		retrieved = casterService.getCaster(10);
		assertTrue("False caster returns not null", retrieved == null);
	}

	@Test
	public void getCastersSpellsTest() {
		int[] retrievedSpellIds = casterService.getCastersSpells(bard.getId());
		assertTrue("Retrieved spell ID's do not match", retrievedSpellIds.equals(bard.getSpellIds()));
		
		retrievedSpellIds = casterService.getCastersSpells(10);
		assertTrue("False caster returns not null", retrievedSpellIds == null);
	}

	@Test
	public void getMaxPointsTest() {
		int maxP = casterService.getMaxPoints(bard.getId(), level);
		assertTrue("Points were not correctly pulled", maxP == 12);

		maxP = casterService.getMaxPoints(bard.getId(), 0);
		assertTrue("False level returns not -1", maxP == -1);

		maxP = casterService.getMaxPoints(10, level);
		assertTrue("False CasterID returns not -1", maxP == -1);
	}

	@Test
	public void getMaxSpellLevelTest() {
		int maxSL = casterService.getMaxSpellLevel(bard.getId(), level);
		assertTrue("Points were not correctly pulled", maxSL == 1);

		maxSL = casterService.getMaxSpellLevel(bard.getId(), 0);
		assertTrue("False level returns not -1", maxSL == -1);

		maxSL = casterService.getMaxSpellLevel(10, level);
		assertTrue("False CasterID returns not -1", maxSL == -1);
	}
}
