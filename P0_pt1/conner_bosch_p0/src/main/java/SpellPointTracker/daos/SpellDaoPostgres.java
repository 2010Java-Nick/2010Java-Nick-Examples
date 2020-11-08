package SpellPointTracker.daos;

import java.sql.SQLException;
import java.util.List;

import SpellPointTracker.pojos.Spell;

public class SpellDaoPostgres implements SpellDao {

    @Override
    public void createSpell(Spell spell) throws SQLException{
        //TODO Implement createSpell
    }

    @Override
    public Spell readSpell(int spellId) throws SQLException{
        //TODO Implement readSpell
        return null;
    }

    @Override
    public List<Spell> readAllSpells() throws SQLException{
        //TODO Implement readAllSpells
        return null;
    }

    @Override
    public Spell updateSpell(Spell spell) throws SQLException{
        //TODO Implement updateSpell        
        return null;
    }

    @Override
    public void deleteSpell(Spell spell) throws SQLException{
        //TODO Implement deleteSpell
    }
    
}