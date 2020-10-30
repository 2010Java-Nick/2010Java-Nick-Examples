package SpellPointTracker.services;

import SpellPointTracker.pojos.Spell;

public interface SpellService {

    public Spell getSpell(int spellId);

    public Spell[] getSpells(int[] spellIds);
    
}
