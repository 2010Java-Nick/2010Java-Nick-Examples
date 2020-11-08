package SpellPointTracker.services;

import java.util.List;

import SpellPointTracker.pojos.Caster;

/**
 * Handles passing Caster objects between storage and the controller
 * and related methods
 */
public interface CasterService {
    
    public List<Caster> getAllCasters();

    public Caster getCaster(int casterId);

    public Integer[] getCastersSpells(int casterId);

    public int getMaxPoints(int casterId, int level);

    public int getMaxSpellLevel(int casterId, int level);
}
