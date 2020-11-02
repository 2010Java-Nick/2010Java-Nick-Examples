package SpellPointTracker.services;

import SpellPointTracker.pojos.Spell;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class SpellServiceImpl implements SpellService {

    private static Logger Log = Logger.getLogger("spellServiceLog");

    List<Spell> spellCollection;

    public SpellServiceImpl(){
        super();
        this.spellCollection = new ArrayList<Spell>();
        
        Spell cureWounds = new Spell(0, "Cure Wounds", 1, 2);
        Spell suggestion = new Spell(1, "Suggestion", 2, 3);
        Spell truePolymorph = new Spell(2, "True Polymorph", 9, 13);
        Spell bless = new Spell(3, "Bless", 1, 2);
        Spell animateDead = new Spell(4, "Animate Dead", 3, 5);
        Spell trueResurrection = new Spell(5, "True Resurrection", 9, 13);
        spellCollection.add(cureWounds);
        spellCollection.add(suggestion);
        spellCollection.add(truePolymorph);
        spellCollection.add(bless);
        spellCollection.add(animateDead);
        spellCollection.add(trueResurrection);
    }

    public List<Spell> getAllSpells(){

        return this.spellCollection;

    }

    public void setAllSpells(List<Spell> spells){
        this.spellCollection = spells;
    }

    @Override
    public Spell getSpell(String spellName) {
        for (Spell s : spellCollection){
            if (s.getName().equals(spellName)){
                return s;
            }
        }
        return null;
    }

    @Override
    public List<Spell> getSpells(int[] spellIds) {
        List<Spell> returnSpells = new ArrayList<>();
        for (int id : spellIds){
            for (Spell s : spellCollection){
                if (s.getId() == id) {
                    returnSpells.add(s);
                    break;
                }
            }
        }
        return returnSpells;
    }
    
}
