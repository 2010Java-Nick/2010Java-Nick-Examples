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
        // Calls playerService.getPlayer, catches exception
        // if success, calls calcService.setCurrentPlayer
        // calls Caster service for caster's spell ids
        // calls Spell service for Spell objects
        // then calls calcService.setCastersSpells
        // returns success or failure
        return false;
    }

    /**
     * Passes params to the Player Service for creation and storing.
     * @param username
     * @param password
     * @param level
     * @param casterType 0=Bard 1=Cleric 2=Druid 3=Paladin 4=Sorcerer 5=Warlock 6=Wizard
     */
    public boolean createNewPlayer(String username, String password, int level, int casterType){
        // Calls playerService.createPlayer, ends on failure
        return false;
    }

    /**
     * Get a list of available spells for currentPlayer's ability to cast
     */
    public List<String> getAvailableSpellNames() {
        //TODO calls calcService.getCastersSpells
        return null;

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
        // retreves spell from spellService
        // passes to calcService to cast
        // returns success or failure
        return false;
    }

    /**
     * Set's currentPlayer's points back to max
     */
    public void rest(){
        //calls calcService for currentPlayer
        //calls casterService.getMaxPoints(casterID, level)
        //calls calcService.rest(maxPoints);
    }    
}
