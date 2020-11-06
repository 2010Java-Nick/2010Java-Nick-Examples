package SpellPointTracker.services;

import java.util.List;

import SpellPointTracker.pojos.Caster;

public class CasterServicePostgres implements CasterService {

    @Override
    public List<Caster> getAllCasters() {
        // TODO impliment getAllCasters
        return null;
    }

    @Override
    public Caster getCaster(int casterId) {
        // TODO impliment getCaster
        return null;
    }

    @Override
    public int[] getCastersSpells(int casterId) {
        // TODO impliment getCasterSpells
        return null;
    }

    @Override
    public int getMaxPoints(int casterId, int level) {
        // TODO impliment getMaxPoints
        return 0;
    }

    @Override
    public int getMaxSpellLevel(int casterId, int level) {
        // TODO impliment getMaxSpellLevel
        return 0;
    }
    
    public void createCaster(int id, String name, int[] spellIds){
        // TODO impliment createCaster
    }

    public Caster updateCaster(int casterId, Caster caster){
        // TODO impliment updateCaster
        return null;
    }

    public void deleteCaster(Caster caster){
        // TODO implement deleteCaster
    }
}