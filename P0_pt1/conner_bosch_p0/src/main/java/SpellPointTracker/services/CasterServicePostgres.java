package SpellPointTracker.services;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import SpellPointTracker.daos.CasterDaoPostgres;
import SpellPointTracker.pojos.Caster;
import SpellPointTracker.util.ConnectionUtil;

public class CasterServicePostgres implements CasterService {

    private static Logger Log = Logger.getLogger("serviceLog");

    private CasterDaoPostgres casterDao;
    private int[] levelToPoints;
    private int[] levelToSpell;

    public CasterServicePostgres(ConnectionUtil connectionUtil) {
        super();
        casterDao = new CasterDaoPostgres(connectionUtil);
        this.levelToPoints = new int[] {0, 4, 6, 14, 17, 27, 32, 38, 44, 57, 64, 73, 73, 73, 83, 83, 94, 94, 10, 114, 123, 133};
        this.levelToSpell = new int[] {0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 9, 9};
    }

    @Override
    public List<Caster> getAllCasters() {
        try {
            List<Caster> casters = casterDao.readAllCasters();
            Log.info("Retrieved all casters from database.");
            return casters;

        } catch (SQLException e) {
            Log.warn("SQLException thrown when calling casterDao for all casters.");
            return null;
        }
    }

    @Override
    public Caster getCaster(int casterId) {
        try {
            Caster caster = casterDao.readCaster(casterId);
            Log.info("Caster " + caster.getName() + " was retrieved from database.");
            return caster;

        } catch (SQLException e) {
            Log.warn("SQLException thrown when retrieving caster: " + casterId + " from database.");
            return null;
        }
    }

    @Override
    public Integer[] getCastersSpells(int casterId) {
        try {
            Caster caster = casterDao.readCaster(casterId);
            Log.info("Caster " + caster.getName() + " was retrieved from database for their spells.");
            return caster.getSpellIds();
        } catch (SQLException e) {
            Log.warn("SQLException thrown when retrieving caster: " + casterId + " from database for their spells.");
            return null;
        }
    }

    @Override
    public int getMaxPoints(int casterId, int level) {
        try {
            Caster caster = casterDao.readCaster(casterId);
            Log.info("Caster " + caster.getName() + " was retrieved from database for max points.");

            if(caster.getHalfCaster()) {

               return  levelToPoints[level/2];

            } else {

                return levelToPoints[level];
            }
        } catch (SQLException e) {
            Log.warn("SQLException thrown when retrieving caster: " + casterId + " from database for max points.");
            return -1;
        }
    }

    @Override
    public int getMaxSpellLevel(int casterId, int level) {
        try {
            Caster caster = casterDao.readCaster(casterId);
            Log.info("Caster " + caster.getName() + " was retrieved from database for max spell level.");

            if(caster.getHalfCaster()) {

                return levelToSpell[level/2];
                
            } else {

                return levelToSpell[level];
            }
        } catch (SQLException e) {
            Log.warn("SQLException thrown when retrieving caster: " + casterId + " from database for max spell level.");
            return -1;
        }
    }
    
    @Override
    public void createCaster(int id, String name, boolean halfCaster, Integer[] spellIds){

        Caster caster = new Caster(id, name, halfCaster, spellIds);

        try {
            casterDao.createCaster(caster);
            Log.info("Caster " + caster.getId() + " was entered into the database.");
        }
         catch (SQLException e) {
            Log.warn("Caster was not able to be entered into the database: " + caster.toString());

        }
    }

    @Override
    public void updateCaster(int id, String name, boolean halfCaster, Integer[] spellIds){

        Caster caster = new Caster(id, name, halfCaster, spellIds);
        Caster currentCaster = this.getCaster(id);

        if(!caster.getSpellIds().equals(currentCaster.getSpellIds())){
            try{
                casterDao.updateCasterSpells(id, spellIds);
                Log.info("Caster spell ids were updated: " + caster.getName());
            } catch (SQLException e) {
                Log.warn("Caster " + caster.getName() + " spells were not able to be updated: " + e);
            }
        }

        currentCaster.setSpellIds(caster.getSpellIds()); 
        
        if(!currentCaster.equals(caster)){
            try {
                casterDao.updateCaster(caster);
                Log.info("Caster was updated: " + caster.toString());

            } catch (SQLException e) {
                Log.warn("Caster " + caster.getName() + " was not able to be updated: " + e);
            }
        }
    }

    @Override
    public void deleteCaster(int id, String name, boolean halfCaster, Integer[] spellIds){

        Caster caster = new Caster(id, name, halfCaster, spellIds);

        try {
            casterDao.deleteCaster(caster);
            Log.info("Caster " + caster.toString() + " was deleted from database.");

        } catch (SQLException e) {
            Log.warn("Caster was not able to be deleted: " + caster.toString());
        }
    }
}