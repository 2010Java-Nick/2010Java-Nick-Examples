package SpellPointTracker.services;

import java.util.List;

import org.apache.log4j.Logger;

import SpellPointTracker.pojos.*;
import SpellPointTracker.controllers.SpellPointsController;

public class CalculatorService {

    private static Logger Log = Logger.getLogger("calculatorServiceLog");

    private Player currentPlayer;
    private List<Spell> castersSpells;

    public Player getCurrentPlayer() {
        return null;
    }

    public boolean setCurrentPlayer(Player player){
        return false;
    }

    /**
     * returns a list of strings containing the name of the spell objects able to be casted.
     * This is determined by the currentPlayer's casterType, currentLevel, and currentPoints
     * @return List<String> of spells available to cast.
     */
    public List<String> getAvailableSpells() {
        return null;
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
        return false;
    }
    
    /**
     * @return a string of information pertaining to the currentPlayer
     */
    public String getStatus(){
        return "";
    }

    /**
     * Resets currentPlayer's spell points to max based on level.
     */
    public void rest(int maxPoints){

    }
}
