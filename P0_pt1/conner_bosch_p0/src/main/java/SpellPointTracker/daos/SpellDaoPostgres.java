package SpellPointTracker.daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import SpellPointTracker.pojos.Spell;
import SpellPointTracker.util.ConnectionUtil;

public class SpellDaoPostgres implements SpellDao {

    private PreparedStatement stmt;

    private static Logger Log = Logger.getLogger("daoLog");

    private ConnectionUtil connUtil;

    public SpellDaoPostgres(ConnectionUtil connectionUtil) {
        super();
        connUtil = connectionUtil;
    }

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