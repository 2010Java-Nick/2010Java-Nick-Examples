package SpellPointTracker.controllers;

import java.util.List;

import org.apache.log4j.Logger;

import SpellPointTracker.pojos.*;
import SpellPointTracker.services.CalculatorService;
import SpellPointTracker.services.CasterService;
import SpellPointTracker.services.PlayerService;
import SpellPointTracker.services.SpellService;

/**
 * Handles the connection between the view and services.
 * Passes any information to its designated service and back.
 */
public class SpellPointsController {
    
    private static Logger Log = Logger.getLogger("controllerLog");
    
    private CasterService casterService;
    private PlayerService playerService;
    private SpellService spellService;
    private CalculatorService calcService;

    public SpellPointsController(CasterService casterService, PlayerService playerService, SpellService spellService, CalculatorService calcService){
        this.casterService = casterService;
        this.playerService = playerService;
        this.spellService = spellService;
        this.calcService = calcService;
    }

    /**
     * Passes username and password for validation, takes the resulting
     * Player object and sets it as the current player.
     * @param username
     * @param password
     * @return
     */
    public boolean setCurrentPlayer(String username, String password){
        Player player = playerService.getPlayer(username, password);

        try {
            calcService.setCurrentPlayer(player);
            int[] spellIds = casterService.getCastersSpells(player.getCasterType());
            List<Spell> spells = spellService.getSpells(spellIds);
            calcService.setCastersSpells(spells);
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    /**
     * Passes params to the Player Service for creation and storing.
     * @param username
     * @param password
     * @param level
     * @param casterType 0=Bard 1=Cleric 2=Druid 3=Paladin 4=Sorcerer 5=Warlock 6=Wizard
     */
    public boolean createNewPlayer(String username, String password, int level, int casterType){
        try {
            playerService.createPlayer(username, password, level, casterType);
            this.setCurrentPlayer(username, password);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * Get a list of available spells for currentPlayer's ability to cast
     */
    public List<String> getAvailableSpellNames() {
        Player player = calcService.getCurrentPlayer();
        int max = casterService.getMaxSpellLevel(player.getCasterType(), player.getCurrentLevel());
        return calcService.getCastersSpells(max);
    }

    /**
     * Checks if currentPlayer has enough points to cast inputed spell
     * and if currentLevel is high enough for the spell
     * If those are met, decrements points and returns true
     * else returns false 
     * @param spellToCast
     * @return success?
     */
    public boolean castSpell(String spellName) {
        Spell spell = spellService.getSpell(spellName);
        return calcService.castSpell(spell);
    }

    /**
     * Set's currentPlayer's points back to max
     */
    public void rest(){
        Player player = calcService.getCurrentPlayer();
        int points = casterService.getMaxPoints(player.getCasterType(), player.getCurrentLevel());
        calcService.rest(points);
    }    

    public String getStatus(){
        return calcService.getStatus();
    }
}
