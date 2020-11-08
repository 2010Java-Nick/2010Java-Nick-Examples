package SpellPointTracker.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        try(Connection conn = connUtil.createConnection()) {
            String sql = "INSERT INTO spell VALUES "
                        +"(?, ?, ?);";
            stmt = conn.prepareStatement(sql);
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
        try(Connection conn = connUtil.createConnection()) {
            //Prep SQL for select statement
            String sql = "SELECT * FROM spell "
                        + "WHERE spell_id = ?;";

            stmt = conn.prepareStatement(sql);
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
        List<Spell> spells = new ArrayList<>();

        try(Connection conn = connUtil.createConnection()) {
            //Prep SQL for select statement
            String sql = "SELECT * FROM spell;";
            stmt = conn.prepareStatement(sql);

            //Return result of SQL query
            ResultSet rs = stmt.executeQuery();

            //Loop through result set
            while(rs.next()) {

                //Read result into player and add to list
                Spell spell = new Spell(rs.getInt(1), 
                                        rs.getString(2),
                                        rs.getInt(3));
                spells.add(spell);
                
            }
        } catch (SQLException e) {
            Log.warn("SpellDaoPostgres.readSpell threw SQLException: " + e);
            throw e;
        }
        return spells;
    }

    @Override
    public void updateSpell(Spell spell) throws SQLException{
        try (Connection conn = connUtil.createConnection()) {
            String sql = "UPDATE spell SET spell_name = ?, spell_level = ? " 
                        + "WHERE spell_id = ?;";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, spell.getName());
            stmt.setInt(2, spell.getLevel());
            stmt.setInt(3, spell.getId());

            stmt.executeUpdate();
        
        } catch (SQLException e) {
            Log.warn("SpellDaoPostgres.updateSpell threw SQLException: " + e);
            throw e;
        }
    }

    @Override
    public void deleteSpell(Spell spell) throws SQLException{
        try (Connection conn = connUtil.createConnection()) {

            String sql = "DELETE FROM spell "
                        + "WHERE spell_id = ?;";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, spell.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            Log.warn("SpellDaoPostgres.deleteSpell threw SQLException: " + e);
            throw e;
        }
    }
    
}