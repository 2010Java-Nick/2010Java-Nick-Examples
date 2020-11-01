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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Spell> getSpells(int[] spellIds) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
