package SpellPointTracker.services;

import SpellPointTracker.pojos.Caster;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * A class that holds the different types of casters as well as the information
 * about those casters spell casting ability. Methods are those pertaining to
 * the casters.
 */
public class CasterServiceImpl implements CasterService {

    private static Logger Log = Logger.getLogger("serviceLog");

    List<Caster> casterCollection = new ArrayList<Caster>();
    int[] levelToPoints;
    int[] levelToSpell;

    public CasterServiceImpl() {
        super();
        this.initData();
    }

    public List<Caster> getAllCasters() {

        return this.casterCollection;

    }

    public void setAllCasters(List<Caster> casters) {
        this.casterCollection = casters;
    }

    @Override
    public Caster getCaster(int casterId) {
        try {
            return casterCollection.get(casterId);
        } catch (IndexOutOfBoundsException e) {
            Log.error("IndexOutOfBoundException in getCaster. Caster ID: " + casterId + "Exception: " + e);
            return null;
        }
    }

    /**
     * Gets an array of ints representing the spellId's of the inputed caster
     * 
     * @return int[] spellIds
     */
    @Override
    public Integer[] getCastersSpells(int casterId) {
        try {
            return casterCollection.get(casterId).getSpellIds();
        } catch (IndexOutOfBoundsException e) {
            Log.error("IndexOutOfBoundException in getCastersSpells. Caster ID: " + casterId + "Exception: " + e);
            return null;
        }
    }

    /**
     * Determines the max spell points of a given caster at a given level
     * 
     * @param casterID
     * @param level
     * @return int maxPoints
     */
    @Override
    public int getMaxPoints(int casterId, int level) {
        switch (casterId) {
            case 0, 1, 2, 4, 6:
                return levelToPoints[level];

            case 3, 5:
                return levelToPoints[level / 2];

            default:
                Log.error("Error in getMaxPoints. CasterID: " + casterId + "Level: " + level);
                return 0;
        }
    }

    /**
     * Gets the max spell level able to be cast by a certain caster at a certain
     * level
     * 
     * @param casterId caster type
     * @param level    level of caster
     * @return int of max spell level
     */
    @Override
    public int getMaxSpellLevel(int casterId, int level) {
        switch (casterId) {
            case 0, 1, 2, 4, 6:
                return levelToSpell[level];

            case 3, 5:
                return levelToSpell[level / 2];

            default:
                Log.error("Error in getMaxSpellLevel. CasterID: " + casterId + "Level: " + level);
                return 0;
        }
    }

    /**
     * Initializes hard coded data for use
     */
    private void initData() {
        this.levelToPoints = new int[] { 0, 4, 6, 14, 17, 27, 32, 38, 44, 57, 64, 73, 73, 73, 83, 83, 94, 94, 10, 114,
                123, 133 };
        this.levelToSpell = new int[] { 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 9, 9 };
        Caster bard = new Caster(0, "Bard", false, new Integer[] { 0, 1, 2 });
        Caster cleric = new Caster(1, "Cleric", false, new Integer[] { 0, 3, 4, 5 });
        this.casterCollection.add(bard);
        this.casterCollection.add(cleric);
    }

    @Override
    public void createCaster(int id, String name, boolean halfCaster, Integer[] spellIds) {

    }

    @Override
    public void deleteCaster(int id) {

    }

    @Override
    public void updateCaster(int id, String name, boolean halfCaster, Integer[] spellIds) {

    }
}
