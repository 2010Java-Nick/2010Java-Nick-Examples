package SpellPointTracker.services;

import SpellPointTracker.pojos.Spell;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * A class that holds the different spells as well as the information for
 * retrieval.
 */
public class SpellServiceImpl implements SpellService {

    private static Logger Log = Logger.getLogger("serviceLog");

    List<Spell> spellCollection;

    public SpellServiceImpl() {
        super();
        this.initData();
    }

    public List<Spell> getAllSpells() {
        return this.spellCollection;
    }

    public void setAllSpells(List<Spell> spells) {
        this.spellCollection = spells;
    }

    /**
     * Looks for a spell with a matching spell name and returns it
     * 
     * @param spellName to be retrieved
     * @return spell object
     */
    @Override
    public Spell getSpell(String spellName) {
        for (Spell s : spellCollection) {
            if (s.getName().equals(spellName)) {
                return s;
            }
        }
        Log.info("Spell " + spellName + " was not found.");
        return null;
    }

    /**
     * Looks for spells whos id matches the inputed ids and returns a list of those
     * spells
     * 
     * @param spellIds to retrieve
     * @return spells that match
     */
    @Override
    public List<Spell> getSpells(Integer[] spellIds) {
        try {
            List<Spell> returnSpells = new ArrayList<>();
            for (int id : spellIds) {
                for (Spell s : spellCollection) {
                    if (s.getId() == id) {
                        returnSpells.add(s);
                        break;
                    }
                }
            }
            return returnSpells;
        } catch (Exception e) {
            Log.error("Exception thrown in getSpells. Exception: " + e);
            return null;
        }
    }

    /**
     * Initializes hard coded data for use
     */
    private void initData() {
        this.spellCollection = new ArrayList<Spell>();

        Spell cureWounds = new Spell(0, "Cure Wounds", 1);
        Spell suggestion = new Spell(1, "Suggestion", 2);
        Spell truePolymorph = new Spell(2, "True Polymorph", 9);
        Spell bless = new Spell(3, "Bless", 1);
        Spell animateDead = new Spell(4, "Animate Dead", 3);
        Spell trueResurrection = new Spell(5, "True Resurrection", 9);
        spellCollection.add(cureWounds);
        spellCollection.add(suggestion);
        spellCollection.add(truePolymorph);
        spellCollection.add(bless);
        spellCollection.add(animateDead);
        spellCollection.add(trueResurrection);
    }

    @Override
    public void createSpell(String name, int level) {
    }

    @Override
    public void deleteSpell(Spell spell) {
    }

    @Override
    public void updateSpell(Spell spell) {
    }
}
