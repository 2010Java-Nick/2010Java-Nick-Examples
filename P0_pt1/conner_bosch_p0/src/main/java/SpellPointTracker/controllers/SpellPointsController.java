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
     * Player object and sets it as the current player. Also initializes
     * player's available spells.
     * @param username
     * @param password
     * @return True on success
     */
    public boolean setCurrentPlayer(String username, String password){
        Player player = playerService.getPlayer(username, password);

        try {
            calcService.setCurrentPlayer(player);
        } catch (NullPointerException e) {
            Log.error("NullPointer thrown in setCurrentPlayer: " + e);
            return false;
        }
        return true;
    }

    /**
     * Passes params to the Player Service for creation and storing. 
     * Then sets that player as the current player.
     * @param username
     * @param password
     * @param level
     * @param casterType 0=Bard 1=Cleric 2=Druid 3=Paladin 4=Sorcerer 5=Warlock 6=Wizard
     */
    public boolean createNewPlayer(String username, String password, int level, int casterType){
        try {
            int points = casterService.getMaxPoints(casterType, level);
            playerService.createPlayer(username, password, points, level, casterType);
            this.setCurrentPlayer(username, password);
            return true;
        } catch (Exception e) {
            Log.error("Exception thrown in createNewPlayer " + e);
            return false;
        }

    }

    /**
     * Get a list of available spells for currentPlayer's ability to cast
     */
    public List<String> getAvailableSpellNames() {
        try {
        Player player = calcService.getCurrentPlayer();
        int max = casterService.getMaxSpellLevel(player.getCasterType(), player.getCurrentLevel());
        List<Spell> spells = spellService.getSpells(casterService.getCastersSpells(player.getCasterType()));
        return calcService.getCastersSpells(max, spells);
        } catch (Exception e) {
            Log.error("Error in getAvailableSpellNames: " + e);
            return null;
        }
    }

    /**
     * Gets spell object matching spellName from spellService
     * passes that object to calcService to be cast
     * @param spellToCast
     * @return True on success
     */
    public boolean castSpell(String spellName) {
        Boolean success = false;
        try {
            Player player = calcService.getCurrentPlayer();
            Spell spell = spellService.getSpell(spellName);

            int max = casterService.getMaxSpellLevel(player.getCasterType(), player.getCurrentLevel());

            if(!(spell.getLevel() > max)){
                success = calcService.castSpell(spell);
                playerService.updatePlayer(player.getId(), player.getUsername(), player.getPassword(), 
                                           player.getCurrentPoints(), player.getCurrentLevel(), player.getCasterType());
            } else {
                Log.warn("Cast attempted with spell of too high level, Spell: " + spellName + ". Player: " + player.getUsername());
            }
        } catch (Exception e) {
            Log.error("Exception in castSpell: " + e);
            return false;
        }
        return success;
    }

    /**
     * Set's currentPlayer's points back to max
     */
    public void rest(){
        try {
            Player player = calcService.getCurrentPlayer();
            int points = casterService.getMaxPoints(player.getCasterType(), player.getCurrentLevel());
            calcService.rest(points);

            playerService.updatePlayer(player.getId(), player.getUsername(), player.getPassword(), 
                                       player.getCurrentPoints(), player.getCurrentLevel(), player.getCasterType());
        } catch (Exception e){
            Log.error("Exception in rest: " + e);
        }
    }    

    /**
     * Gets the string representation of the current player's status
     * @return String of composed status
     */
    public String getStatus(){
        try {
            return calcService.getStatus(casterService.getAllCasters());
        }
        catch (Exception e){
            Log.error("Exception in getStatus: " + e);
            return "";
        }
    }

    public List<Caster> getAllCasters() {
        return casterService.getAllCasters();
    }

    public Caster getCaster(int casterId) {
        return casterService.getCaster(casterId);
    }

    public void updatePlayer(int id, String username, String password, int currentPoints, int level, int casterType){
        playerService.updatePlayer(id, username, password, currentPoints, level, casterType);
    }

    public void deletePlayer(int id){
        playerService.deletePlayer(id);
    }

    public Spell getSpell(String spellName) {
        return spellService.getSpell(spellName);
    }
}
