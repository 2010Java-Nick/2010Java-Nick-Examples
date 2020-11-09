package SpellPointTracker.daos;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
                        + "INNER JOIN caster_spell cs " 
                        + "ON c.caster_id = cs.caster_id ";
            stmt = conn.prepareStatement(sql);

            //Return result of SQL query
            ResultSet rs = stmt.executeQuery();

            int currentCasterId = -1;
            String casterName = "";
            boolean casterHalf = false;
            List<Integer> ids = new LinkedList<>();

            //Loop through result set
            while(rs.next()) {

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
            }
        } catch (SQLException e) {
            Log.warn("CasterDaoPostgres.readAllCasters threw SQLException: " + e);
            throw e;
        }
        return casters;
    }

    @Override
    public void updateCaster(Caster caster) throws SQLException{
        //TODO Implement updateCaster
    }

    @Override
    public void deleteCaster(Caster caster) throws SQLException{
        //TODO Implement deleteCaster
    } 
}