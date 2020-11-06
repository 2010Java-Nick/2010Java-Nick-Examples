package SpellPointTracker.daos;

import java.util.List;

import SpellPointTracker.pojos.Spell;

public interface SpellDao {

    public void createSpell(Spell spell);

    public Spell readSpell(int spellId);

    public List<Spell> readAllSpells();

    public Spell updateSpell(int spellId, Spell spell);

    public void deleteSpell(Spell spell);
    
}