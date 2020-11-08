package SpellPointTracker.daos;

import java.sql.SQLException;
import java.util.List;

import SpellPointTracker.pojos.Spell;

public interface SpellDao {

    public void createSpell(Spell spell) throws SQLException;

    public Spell readSpell(int spellId) throws SQLException;

    public List<Spell> readAllSpells() throws SQLException;

    public Spell updateSpell(int spellId, Spell spell) throws SQLException;

    public void deleteSpell(Spell spell) throws SQLException;
    
}