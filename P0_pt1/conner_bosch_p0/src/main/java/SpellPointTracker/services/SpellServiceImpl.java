package SpellPointTracker.services;

import SpellPointTracker.pojos.Spell;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class SpellServiceImpl implements SpellService {

    private static Logger Log = Logger.getLogger("spellServiceLog");

    List<Spell> spellCollection = new ArrayList<Spell>();

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
