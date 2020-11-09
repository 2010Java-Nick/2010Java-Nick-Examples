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

import SpellPointTracker.pojos.*;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorServiceTest {

    private CalculatorService calcService;
    private String username;
	private String password;
	private int level;
	private int casterType;
	private Player player;
	private Spell spellOne;
    private Spell spellTwo;
    private Spell falseSpell1;
    private Spell falseSpell2;
	private List<Spell> spells;
	private List<String> spellNames;
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
        player = new Player(1, username, password, 10, level, casterType);
		
		spellOne = new Spell(0, "cantrip", 0);
        spellTwo = new Spell(1, "magic missle", 1);
		falseSpell1 = new Spell(2, "should not be found", 3);
		falseSpell2 = new Spell(3, "should not be found", 9);
		spellNames = new ArrayList<>();
		spellNames.add("cantrip");
		spellNames.add("magic missle");
		spells = new ArrayList<Spell>();
		spells.add(spellOne);
		spells.add(spellTwo);
		casters = new ArrayList<>();
		caster = new Caster(0, "Bard", false, new Integer[]{0, 1});
		casters.add(caster);
        
        calcService = new CalculatorService();
        calcService.setCurrentPlayer(player);
	}

	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void getCasterSpellsTest(){
		spells.add(falseSpell1);
		spells.add(falseSpell2);
		List<String> newSpells = calcService.getCastersSpells(1, spells);
		
		assertTrue("Incorrect number of spells returned", newSpells.size() == 2);
		assertTrue("First spell doesn't match", newSpells.get(0).equals(spellOne.getName()));
		assertTrue("Second spell doesn't match", newSpells.get(1).equals(spellTwo.getName()));
	}
    @Test
	public void castSpellTest() {
        assertTrue("Spell one wasn't cast", calcService.castSpell(spellOne));
        assertTrue("Spell one's points weren't properly deducted", calcService.getCurrentPlayer().getCurrentPoints() == player.getCurrentPoints());
        assertTrue("Spell two wasn't cast", calcService.castSpell(spellTwo));
        assertTrue("Spell two's points weren't properly deducted", calcService.getCurrentPlayer().getCurrentPoints() == 8);
        assertFalse("False spell 2 did not fail", calcService.castSpell(falseSpell2));

	}
    
    @Test
	public void getStatusTest() {
        String expected = "Player 1: daveTheGamer Level 2 Bard -- Available Spell Points: 10";
		assertTrue("Status|" + calcService.getStatus(casters) + "|does not match expected:" + expected, calcService.getStatus(casters).equals(expected));
    }
    
    @Test
	public void restTest() {
        int expected = 40;
        calcService.rest(expected);
        int postRest = calcService.getCurrentPlayer().getCurrentPoints();

		assertTrue("Rest not reset properly", expected == postRest);
	}
}
