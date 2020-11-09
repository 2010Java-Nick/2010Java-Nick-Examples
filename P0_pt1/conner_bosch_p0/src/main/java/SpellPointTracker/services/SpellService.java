package SpellPointTracker.services;

import java.util.List;

import SpellPointTracker.pojos.Spell;

/**
 * Handles passing Spell objects between storage and the controller
 * and related methods
 */
public interface SpellService {

    public List<Spell> getAllSpells();

    public Spell getSpell(String spellName);

    public List<Spell> getSpells(Integer[] spellIds);

    public void createSpell(String name, int level);
    
    public void updateSpell(Spell spell);

    public void deleteSpell(Spell spell);
}
