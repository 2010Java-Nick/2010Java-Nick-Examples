package SpellPointTracker.services;

import SpellPointTracker.pojos.Caster;
import SpellPointTracker.pojos.Spell;

import java.util.List;

import org.apache.log4j.Logger;

public class CasterServiceImpl implements CasterService {

    private static Logger Log = Logger.getLogger("casterServiceLog");

    @Override
    public Caster getCaster(int casterId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int[] getCastersSpells(int casterId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getMaxPoints(int casterId, int level) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getMaxSpellLevel(int casterId, int level) {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
