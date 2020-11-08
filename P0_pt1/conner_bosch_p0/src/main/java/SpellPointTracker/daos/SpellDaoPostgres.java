package SpellPointTracker.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        try {
            String sql = "INSERT INTO spell VALUES "
                        +"(?, ?, ?);";
            stmt = connUtil.createConnection().prepareStatement(sql);
            stmt.setInt(1, spell.getId());
            stmt.setString(2, spell.getName());
            stmt.setInt(3, spell.getLevel());

            stmt.executeUpdate();
        } catch (SQLException e) {
            Log.warn("SpellDaoPostgres.createSpell threw SQLException: " + e);
            throw e;
        }
    }

    @Override
    public Spell readSpell(int spellId) throws SQLException{
        try {
            //Prep SQL for select statement
            String sql = "SELECT * FROM spell "
                        + "WHERE spell_id = ?;";

            stmt = connUtil.createConnection().prepareStatement(sql);
            stmt.setInt(1, spellId);

            //Return result of SQL query
            ResultSet rs = stmt.executeQuery();
            rs.next();

            //Read result into spell
            Spell spell = new Spell(rs.getInt(1), 
                                    rs.getString(2),
                                    rs.getInt(3));
            return spell;
        } catch (SQLException e) {
            Log.warn("SpellDaoPostgres.readSpell threw SQLException: " + e);
            throw e;
        }
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