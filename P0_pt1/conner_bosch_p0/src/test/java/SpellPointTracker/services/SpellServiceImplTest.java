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

import SpellPointTracker.pojos.Spell;

@RunWith(MockitoJUnitRunner.class)
public class SpellServiceImplTest {
	
	private SpellService spellService;
	private Spell spellOne;
	private Spell spellTwo;
	private Spell spellThree;
	private List<Spell> spells;
	private List<Spell> noSpells;
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
		spellOne = new Spell(0, "cantrip", 0, 0);
		spellTwo = new Spell(1, "magic missle", 1, 4);
		spellThree = new Spell(2, "fireball", 3, 12);
		spellIds = new int[]{0, 1};
		spellNames = new ArrayList<>();
		spellNames.add("cantrip");
		spellNames.add("magic missle");
		spells = new ArrayList<Spell>();
		noSpells = new ArrayList<Spell>();
		spells.add(spellOne);
		spells.add(spellTwo);
		spells.add(spellThree);

		spellService = new SpellServiceImpl();
		spellService.setAllSpells(spells);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getSpellTest() {
		spellService.setAllSpells(spells);
		Spell s1 = spellService.getSpell(spellOne.getName());
		assertTrue("Retrieved spell does not match expected", s1.equals(spellOne));

		spellService.setAllSpells(noSpells);
		assertTrue("Retrieve spell even though no spells stored", spellService.getSpell(spellOne.getName()) == null);
	}

	@Test
	public void getSpellsTest() {
		List<Spell> retrievedSpells = spellService.getSpells(spellIds);
		assertFalse("Retrieved all spells, not specific ones", spells.size() == retrievedSpells.size());
		assertFalse("Retrieved no spells", retrievedSpells.size() == 0);
		assertTrue("Retrieved spells do not contain spell One", retrievedSpells.contains(spellOne));
		assertTrue("Retrieved spells do not contain spell Two", retrievedSpells.contains(spellTwo));
	}
}
