package SpellPointTracker.controllers;

import org.apache.log4j.Logger;

import SpellPointTracker.pojos.*;
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
    private Player currentPlayer;
    private Spell[] availableSpells;

    public SpellPointsController(CasterService casterService, PlayerService playerService, SpellService spellService){
        this.casterService = casterService;
        this.playerService = playerService;
        this.spellService = spellService;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Askes the User Service for a player with these params,
     * Is not concerned about validation.
     * @param username
     * @param password
     */
    public boolean setCurrentPlayer(String username, String password) {
        return false;
    }

    public void setCurrentPlayer(Player player){
        this.currentPlayer = player;
    }

    /**
     * Passes params to the Player Service for creation and storing.
     * @param username
     * @param password
     * @param level
     * @param casterType 0=Bard 1=Cleric 2=Druid 3=Paladin 4=Sorcerer 5=Warlock 6=Wizard
     */
    public boolean createNewPlayer(String username, String password, int level, int casterType){
        return false;
    }

    /**
     * returns a list of Spell objects able to be casted.
     * This is determined by the currentPlayer's casterType, currentLevel, and currentPoints
     * @return Spell[] of spells available to cast.
     */
    public Spell[] getAvailableSpells() {
        this.refreshAvailableSpells();
        return availableSpells;
    }

    /**
     * Executed by the controlled to verify current spell list is up to date
     */
    private void refreshAvailableSpells() {

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
        return false;
    }

    /**
     * Set's currentPlayer's points back to max
     */
    public void rest(){
        
    }    
}
