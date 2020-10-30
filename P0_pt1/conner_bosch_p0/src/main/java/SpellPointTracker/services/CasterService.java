package SpellPointTracker.services;

import SpellPointTracker.pojos.Caster;
import SpellPointTracker.pojos.Spell;

/**
 * Handles passing Caster objects between storage and the controller
 */
public interface CasterService {

    public Caster getCaster(int casterId);

    public Spell[] getCastersSpells(int casterId);

    public int getMaxPoints(int casterId, int level);

    public int getMaxSpellLevel(int casterId, int level);
}
