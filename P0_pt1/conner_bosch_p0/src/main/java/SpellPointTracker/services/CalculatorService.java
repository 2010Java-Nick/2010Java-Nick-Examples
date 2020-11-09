package SpellPointTracker.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import SpellPointTracker.pojos.*;

/**
 * A service that handles calculations requiring more than one object type
 * Holds the currentPlayer as well as a list of spells that player is able to be cast.
 */
public class CalculatorService {

    private static Logger Log = Logger.getLogger("calculatorLog");

    private Player currentPlayer;
    private int[] spellCosts = new int[] {0, 2, 3, 5, 6, 7, 9, 10, 11, 13};

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean setCurrentPlayer(Player player){
        this.currentPlayer = player;
        return true;
    }

    /**
     * returns a list of strings containing the name of the spell objects currently able to be casted.
     * This is determined by the currentPlayer's casterType, currentLevel, and currentPoints
     * @return List<String> of spells available to cast.
     */
    public List<String> getCastersSpells(int maxLevel, List<Spell> castersSpells) {

        try {
            List<String> spells = new ArrayList<>();
            int points = currentPlayer.getCurrentPoints();

            for (Spell spell : castersSpells){

                //Spell doesn't cost more than available points and is of a castable level
                if (!(spellCosts[spell.getLevel()] > points) && !(spell.getLevel() > maxLevel)){

                    spells.add(spell.getName());
                }
            }
            return spells;
        } catch (Exception e) {
            Log.error("Exception in getCastersSpells: " + e);
            return null;
        }
    }

    /**
     * Deducts the appropriate amount of points from the currentPlayer
     * @param spell the spell to be cast
     * @return True on success
     */
    public boolean castSpell(Spell spell){

        try {
            int newPoints = currentPlayer.getCurrentPoints();

            if (spellCosts[spell.getLevel()] <= newPoints) {

                newPoints -= spellCosts[spell.getLevel()];
                currentPlayer.setCurrentPoints(newPoints);
                return true;
            }
            return false;
        } catch (Exception e){
            Log.error("Exception in castSpell: " + e);
            return false;
        }
    }
    
    /**
     * @return a string of information pertaining to the currentPlayer
     * Formated like: Player 1: daveTheGamer Level 2 Bard -- Available Spell Points: 20
     */
    public String getStatus(List<Caster> allCasters){

        try {
            String casterName = "";
            for (Caster c : allCasters){
                if(c.getId() == currentPlayer.getCasterType()){
                    casterName = c.getName();
                }
            }

            return "Player " + currentPlayer.getId() + ": " + currentPlayer.getUsername() + 
                    " Level " + currentPlayer.getCurrentLevel() +" "+ casterName + " -- " + 
                    "Available Spell Points: " + currentPlayer.getCurrentPoints();
        } catch (Exception e) {
            Log.error("Exception in getStatus: " + e);
            return "";
        }
    }

    /**
     * Resets currentPlayer's spell points to max based on level.
     */
    public void rest(int maxPoints){
        try {
            currentPlayer.setCurrentPoints(maxPoints);
        } catch (Exception e) {
            Log.error("Exception in rest: " + e);
        }
    }
}
