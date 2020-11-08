package SpellPointTracker.daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import SpellPointTracker.pojos.Caster;
import SpellPointTracker.util.ConnectionUtil;

public class CasterDaoPostgres implements CasterDao {
    
    private PreparedStatement stmt;

    private static Logger Log = Logger.getLogger("daoLog");

    private ConnectionUtil connUtil;

    public CasterDaoPostgres(ConnectionUtil connectionUtil){
        super();
        connUtil = connectionUtil;
    }

    @Override
    public void createCaster(Caster caster) throws SQLException{
        //TODO Implement createCaster
    }

    @Override
    public Caster readCaster(int casterId) throws SQLException{
        //TODO Implement readCaster
        return null;
    }

    @Override
    public List<Caster> readAllCasters() throws SQLException{
        //TODO Implement readAllCasters
        return null;
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