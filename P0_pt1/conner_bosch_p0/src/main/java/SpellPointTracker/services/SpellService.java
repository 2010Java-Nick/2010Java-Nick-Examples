package SpellPointTracker.services;

import java.util.List;

import SpellPointTracker.pojos.Spell;

/**
 * Handles passing Spell objects between storage and the controller
 * and related methods
 */
public interface SpellService {

    public List<Spell> getAllSpells();

    public void setAllSpells(List<Spell> spells);

    public Spell getSpell(String spellName);

    public List<Spell> getSpells(int[] spellIds);
    
}
