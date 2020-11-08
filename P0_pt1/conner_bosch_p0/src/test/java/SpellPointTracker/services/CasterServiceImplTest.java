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
import org.mockito.junit.MockitoJUnitRunner;

import SpellPointTracker.pojos.Caster;


@RunWith(MockitoJUnitRunner.class)
public class CasterServiceImplTest {
	
	private CasterServiceImpl casterService;
	private int level;
	private Caster bard;
	private Caster cleric;
	private Integer[] spellIds;
	private List<Caster> casters;


    @BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		level = 2;

		spellIds = new Integer[]{0, 1, 2};
		bard = new Caster(0, "Bard", false, spellIds);

		spellIds = new Integer[]{0, 3, 4, 5};
		cleric = new Caster(1, "Cleric", false, spellIds);

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
		Integer[] retrievedSpellIds = casterService.getCastersSpells(bard.getId());
		assertTrue("Retrieved spell ID's do not match", retrievedSpellIds.equals(bard.getSpellIds()));
		
		retrievedSpellIds = casterService.getCastersSpells(10);
		assertTrue("False caster returns not null", retrievedSpellIds == null);
	}

	@Test
	public void getMaxPointsTest() {
		int maxP = casterService.getMaxPoints(bard.getId(), level);
		assertTrue("Points were not correctly pulled", maxP == 6);

		maxP = casterService.getMaxPoints(bard.getId(), 0);
		assertTrue("False level returns not -1", maxP == 0);

		maxP = casterService.getMaxPoints(10, level);
		assertTrue("False CasterID returns not -1", maxP == 0);
	}

	@Test
	public void getMaxSpellLevelTest() {
		int maxSL = casterService.getMaxSpellLevel(bard.getId(), level);
		assertTrue("Points were not correctly pulled", maxSL == 1);

		maxSL = casterService.getMaxSpellLevel(bard.getId(), 0);
		assertTrue("False level returns not -1", maxSL == 0);

		maxSL = casterService.getMaxSpellLevel(10, level);
		assertTrue("False CasterID returns not -1", maxSL == 0);
	}
}
