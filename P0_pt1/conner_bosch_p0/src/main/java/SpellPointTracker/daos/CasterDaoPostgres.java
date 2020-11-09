package SpellPointTracker.daos;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import SpellPointTracker.pojos.Caster;
import SpellPointTracker.util.ConnectionUtil;

public class CasterDaoPostgres implements CasterDao {
    
    private PreparedStatement stmt;
    private CallableStatement callStmt;

    private static Logger Log = Logger.getLogger("daoLog");

    private ConnectionUtil connUtil;

    public CasterDaoPostgres(ConnectionUtil connectionUtil){
        super();
        connUtil = connectionUtil;
    }

    @Override
    public void createCaster(Caster caster) throws SQLException{
        try(Connection conn = connUtil.createConnection()) {

            conn.setAutoCommit(false);
            Array ids = conn.createArrayOf("int4", caster.getSpellIds());
            stmt = conn.prepareStatement("INSERT INTO caster VALUES (?, ?, ?); ");
            stmt.setInt(1, caster.getId());
            stmt.setString(2, caster.getName());
            stmt.setBoolean(3, caster.getHalfCaster());
            stmt.executeUpdate();

            callStmt = conn.prepareCall("CALL insert_caster_spell_list(?, ?); ");
            callStmt.setInt(1, caster.getId());
            callStmt.setArray(2, ids);
            callStmt.execute();

            conn.commit();

        } catch (SQLException e) {
            Log.warn("CasterDaoPostgres.createCaster threw SQLException: " + e);
            throw e;
        }
    }

    @Override
    public Caster readCaster(int casterId) throws SQLException{

        try(Connection conn = connUtil.createConnection()) {

            //Prep SQL for select statement
            String sql = "SELECT c.caster_id, c.caster_name, c.half_caster, cs.spell_id FROM caster c "
                        + "INNER JOIN caster_spell cs " 
                        + "ON c.caster_id = cs.caster_id " 
                        + "WHERE c.caster_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, casterId);
            
            //Return result of SQL query
            ResultSet rs = stmt.executeQuery();
            rs.next();

            //Read result into variables
            Integer castId = rs.getInt(1);
            String castName = rs.getString(2);
            Boolean castHalf = rs.getBoolean(3);

            //Initialize spellIds container
            List<Integer> ids = new LinkedList<Integer>();

            //Add spellIds to list
            do {
                ids.add(rs.getInt(4));
            } while (rs.next());

            //Convert list to array
            Integer[] spellIds = ids.toArray(new Integer[ids.size()]);

            return new Caster(castId, castName, castHalf, spellIds);

        } catch (SQLException e) {
            Log.warn("CasterDaoPostgres.readCaster threw SQLException: " + e);
            throw e;
        }
    }

    @Override
    public List<Caster> readAllCasters() throws SQLException{
        List<Caster> casters = new ArrayList<>();

        try(Connection conn = connUtil.createConnection()) {
            //Prep SQL for select statement
            String sql = "SELECT c.caster_id, c.caster_name, c.half_caster, cs.spell_id FROM caster c "
                        + "LEFT JOIN caster_spell cs " 
                        + "ON c.caster_id = cs.caster_id " 
                        + "ORDER BY c.caster_id;";
            stmt = conn.prepareStatement(sql);

            //Return result of SQL query
            ResultSet rs = stmt.executeQuery();

            int currentCasterId = -1;
            String casterName = "";
            boolean casterHalf = false;

            //Loop through result set
            rs.next();
            do {
                List<Integer> ids = new LinkedList<>();
                //Read caster info from first row of that caster
                currentCasterId = rs.getInt(1);
                casterName = rs.getString(2);
                casterHalf = rs.getBoolean(3);

                //Read joined spell ids, pushing the pointer until new caster found
                do {
                    ids.add(rs.getInt(4));
                } while (rs.next() && currentCasterId == rs.getInt(1));

                //Convert list to array
                Integer[] spellIds = ids.toArray(new Integer[ids.size()]);

                //Add completed caster to all casters list
                casters.add(new Caster(currentCasterId, casterName, casterHalf, spellIds));

            } while (!rs.isAfterLast());
        } catch (SQLException e) {
            Log.warn("CasterDaoPostgres.readAllCasters threw SQLException: " + e);
            throw e;
        }
        return casters;
    }

    @Override
    public void updateCaster(Caster caster) throws SQLException{
        try (Connection conn = connUtil.createConnection()) {
            String sql = "UPDATE caster SET caster_name = ?, half_caster = ?" 
                        +"WHERE caster_id = ?;";
            
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, caster.getName());
            stmt.setBoolean(2, caster.getHalfCaster());
            stmt.setInt(3, caster.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            Log.warn("CasterDaoPostgres.updateCaster threw SQLException: " + e);
            throw e;
        }
    }

    @Override
    public void updateCasterSpells(int casterId, Integer[] spellIds) throws SQLException{
        try (Connection conn = connUtil.createConnection()) {
            conn.setAutoCommit(false);
            String sql = "SELECT spell_id FROM caster_spell WHERE caster_id = ?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, casterId);

            ResultSet rs = stmt.executeQuery();
            
            List<Integer> new_spells = Arrays.asList(spellIds);
            List<Integer> old_spells = new LinkedList<>();
            List<Integer> delete_spells = new LinkedList<>();
            List<Integer> insert_spells = new LinkedList<>();

            while (rs.next()) {
                old_spells.add(rs.getInt(1));
            }

            for (int oldS : old_spells) {
                if(!new_spells.contains(oldS)){
                    delete_spells.add(oldS);
                }
            }

            for (int newS : new_spells) {
                if(!old_spells.contains(newS)){
                    insert_spells.add(newS);
                }
            }

            sql = "DELETE FROM caster_spell WHERE caster_id = ? AND spell_id = ?;";
            stmt = conn.prepareStatement(sql);

            for (int oldS : delete_spells) {
                stmt.setInt(1, casterId);
                stmt.setInt(2, oldS);
                stmt.addBatch();
            }
            stmt.executeBatch();

            
            sql = "INSERT INTO caster_spell VALUES (?, ?);";
            stmt = conn.prepareStatement(sql);

            for (int newS : insert_spells) {
                stmt.setInt(1, casterId);
                stmt.setInt(2, newS);
                stmt.addBatch();
            }
            stmt.executeBatch();

            conn.commit();
        } catch (SQLException e) {
            Log.warn("CasterDaoPostgres.updateCasterSpells threw SQLException: " + e);
            throw e;
        }
    }

    @Override
    public void deleteCaster(Caster caster) throws SQLException{
        try (Connection conn = connUtil.createConnection()) {

            String sql = "DELETE FROM caster " 
                        +"WHERE caster_id = ?;";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, caster.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            Log.warn("CasterDaoPostgres.deleteCaster threw SQLException: " + e);
            throw e;
        }
    } 
}