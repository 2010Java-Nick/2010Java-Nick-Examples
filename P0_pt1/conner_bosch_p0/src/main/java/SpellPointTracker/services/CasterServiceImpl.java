package SpellPointTracker.services;

import SpellPointTracker.pojos.Caster;
import SpellPointTracker.pojos.Spell;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class CasterServiceImpl implements CasterService {

    private static Logger Log = Logger.getLogger("casterServiceLog");

    List<Caster> casterCollection = new ArrayList<Caster>();
    int[] levelToPoints = new int[] {0, 4, 6, 14, 17, 27, 32, 38, 44, 57, 64, 73, 73, 73, 83, 83, 94, 94, 10, 114, 123, 133};
    int[] levelToSpell = new int[] {0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 9, 9};

    public List<Caster> getAllCasters(){

        return this.casterCollection;

    }

    public void setAllCasters(List<Caster> casters){
        this.casterCollection = casters;
    }

    @Override
    public Caster getCaster(int casterId) {
        try {
            return casterCollection.get(casterId);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public int[] getCastersSpells(int casterId) {
        try {
            return casterCollection.get(casterId).getSpellIds();
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public int getMaxPoints(int casterId, int level) {
        switch (casterId){
            case 0, 1, 2, 4, 6:
                return levelToPoints[level];

            case 3, 5:
                return levelToPoints[level/2];

            default:
                return 0;
        }
    }

    @Override
    public int getMaxSpellLevel(int casterId, int level) {
        switch (casterId){
            case 0, 1, 2, 4, 6:
                return levelToSpell[level];

            case 3, 5:
                return levelToSpell[level/2];

            default:
                return 0;
        }
    }
    
}
