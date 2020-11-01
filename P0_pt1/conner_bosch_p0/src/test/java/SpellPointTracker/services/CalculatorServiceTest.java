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

import SpellPointTracker.pojos.*;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorServiceTest {

    //private static Logger Log = Logger.getLogger("calculatorLog");

    private CalculatorService calcService;
    private String username;
	private String password;
	private int level;
	private int casterType;
	private Player player;
	private Spell spellOne;
    private Spell spellTwo;
    private Spell falseSpell;
	private List<Spell> spells;
	private List<String> spellNames;

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
		
		spellOne = new Spell(0, "cantrip", 0, 0);
        spellTwo = new Spell(1, "magic missle", 1, 4);
        falseSpell = new Spell(2, "should not be found", 2, 4);
		spellNames.add("cantrip");
		spellNames.add("magic missle");
		spells = new ArrayList<Spell>();
		spells.add(spellOne);
        spells.add(spellTwo);
        
        
        calcService.setCurrentPlayer(player);
        calcService.setCastersSpells(spells);
	}

	@After
	public void tearDown() throws Exception {
	}
    
    @Test
	public void castSpellTest() {
        assertTrue("Spell one wasn't cast", calcService.castSpell(spellOne));
        assertTrue("Spell one's points weren't properly deducted", calcService.getCurrentPlayer().getCurrentPoints() == player.getCurrentPoints());
        assertTrue("Spell two wasn't cast", calcService.castSpell(spellTwo));
        assertTrue("Spell two's points weren't properly deducted", calcService.getCurrentPlayer().getCurrentPoints() == player.getCurrentPoints()-4);
        assertFalse("False spell did not fail", calcService.castSpell(falseSpell));

	}
    
    @Test
	public void getStatusTest() {
        String expected = "Player 1: daveTheGamer Level 2 Bard%nAvailable Spell Points: 20 ";
		assertTrue("Status|" + calcService.getStatus() + "|does not match expected:" + expected, calcService.getStatus().equals(expected));
    }
    
    @Test
	public void restTest() {
        int expected = 40;
        calcService.rest(expected);
        int postRest = calcService.getCurrentPlayer().getCurrentPoints();

		assertTrue("Rest not reset properly", expected == postRest);
	}
}
