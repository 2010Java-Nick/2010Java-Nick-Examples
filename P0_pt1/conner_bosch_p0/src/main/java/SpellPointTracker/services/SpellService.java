package SpellPointTracker.services;

import java.util.List;

import SpellPointTracker.pojos.Spell;

public interface SpellService {

    public Spell getSpell(int spellId);

    public List<Spell> getSpells(int[] spellIds);
    
}
