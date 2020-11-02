package SpellPointTracker.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import SpellPointTracker.pojos.*;

public class CalculatorService {

    private static Logger Log = Logger.getLogger("calculatorServiceLog");

    private Player currentPlayer;
    private List<Spell> castersSpells;

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean setCurrentPlayer(Player player){
        this.currentPlayer = player;
        return true;
    }

    /**
     * returns a list of strings containing the name of the spell objects able to be casted.
     * This is determined by the currentPlayer's casterType, currentLevel, and currentPoints
     * @return List<String> of spells available to cast.
     */
    public List<String> getCastersSpells(int maxLevel) {
        List<String> spells = new ArrayList<>();
        int points = currentPlayer.getCurrentPoints();
        for (Spell spell : castersSpells){
            if (!(spell.getCost() > points) && !(spell.getLevel() > maxLevel)){
                spells.add(spell.getName());
            }
        }
        return spells;
    }

    public void setCastersSpells(List<Spell> spells){
        this.castersSpells = spells;
    }

    /**
     * Deducts the appropriate amount of points from the currentPlayer
     * @param spell the spell to be cast
     * @return
     */
    public boolean castSpell(Spell spell){
        try {
            int newPoints = currentPlayer.getCurrentPoints();
            if (spell.getCost() < newPoints) {
                newPoints -= spell.getCost();
                currentPlayer.setCurrentPoints(newPoints);
                return true;
            }
            return false;
        } catch (Exception e){
            return false;
        }
    }
    
    /**
     * @return a string of information pertaining to the currentPlayer
     */
    public String getStatus(){
        String[] casterNames = new String[]{"Bard", "Cleric", "Druid", "Paladin", "Sorcerer", "Warlock", "Wizard"};
        int type = currentPlayer.getCasterType();
        return "Player " + currentPlayer.getId() + ": " + currentPlayer.getUsername() + 
                " Level " + currentPlayer.getCurrentLevel() +" "+ casterNames[type] + "%n" + 
                "Available Spell Points: " + currentPlayer.getCurrentPoints();
    }

    /**
     * Resets currentPlayer's spell points to max based on level.
     */
    public void rest(int maxPoints){
        currentPlayer.setCurrentPoints(maxPoints);
    }
}
