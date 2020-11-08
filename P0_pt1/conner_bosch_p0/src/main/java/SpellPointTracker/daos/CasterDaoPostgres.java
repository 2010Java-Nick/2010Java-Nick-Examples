package SpellPointTracker.daos;

import java.sql.SQLException;
import java.util.List;

import SpellPointTracker.pojos.Caster;

public class CasterDaoPostgres implements CasterDao {

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
    public Caster updateCaster(Caster caster) throws SQLException{
        //TODO Implement updateCaster
        return null;
    }

    @Override
    public void deleteCaster(Caster caster) throws SQLException{
        //TODO Implement deleteCaster
    } 
}