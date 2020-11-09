package SpellPointTracker.daos;

import java.sql.SQLException;
import java.util.List;

import SpellPointTracker.pojos.Caster;

public interface CasterDao {

    public void createCaster(Caster caster) throws SQLException;

    public Caster readCaster(int casterId) throws SQLException;

    public List<Caster> readAllCasters() throws SQLException;

    public void updateCaster(Caster caster) throws SQLException;

    public void updateCasterSpells(int casterId, Integer[] spellIds) throws SQLException;

    public void deleteCaster(Caster caster) throws SQLException;
    
}