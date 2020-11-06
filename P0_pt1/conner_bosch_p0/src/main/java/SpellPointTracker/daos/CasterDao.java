package SpellPointTracker.daos;

import java.util.List;

import SpellPointTracker.pojos.Caster;

public interface CasterDao {

    public void createCaster(Caster caster);

    public Caster readCaster(int casterId);

    public List<Caster> readAllCasters();

    public Caster updateCaster(int casterId, Caster caster);

    public void deleteCaster(Caster caster);
    
}