package SpellPointTracker.services;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import SpellPointTracker.daos.SpellDaoPostgres;
import SpellPointTracker.pojos.Spell;
import SpellPointTracker.util.ConnectionUtil;

public class SpellServicePostgres implements SpellService {

    private static Logger Log = Logger.getLogger("serviceLog");

    private SpellDaoPostgres spellDao;
    private List<Spell> allSpells;

    public SpellServicePostgres(ConnectionUtil connectionUtil){
        super();
        spellDao = new SpellDaoPostgres(connectionUtil);
        initializeAllSpellsList();
    }

    @Override
    public List<Spell> getAllSpells() {
        try {
            List<Spell> allSpells = spellDao.readAllSpells();
            Log.info("Retrieved " + allSpells.size() + " spells from database.");
            return allSpells;

        } catch (SQLException e) {
            Log.warn("Retrieval of all spells was unsuccessful: " + e);
            return null;
        }
    }

    @Override
    public Spell getSpell(String spellName) {
        for (Spell s : allSpells) {
            if (s.getName().equals(spellName)){
                Log.info("Retrieved spell " + spellName + " from local cache.");
                return s;
            }
        }
        initializeAllSpellsList();
        return null;
    }

    @Override
    public List<Spell> getSpells(Integer[] spellIds) {
        List<Spell> returnSpells = new LinkedList<>();
        for (int id : spellIds) {
            for (Spell s : allSpells) {
                if (s.getId() == id) {
                    returnSpells.add(s);
                    break;
                }
            }
        }
        Log.info("Returned " + returnSpells.size() + " specific spells.");
        return returnSpells;
}

    public void createSpell(String name, int level){
        
        Spell spell = new Spell(0, name, level);
        try {
            spellDao.createSpell(spell);
            Log.info("Spell " + name + " was created.");
        } catch (SQLException e) {
            Log.warn("Spell " + name + " was not able to be created: " + e);
        }
    }

    public void updateSpell(Spell spell){
        try {
            spellDao.updateSpell(spell);
            Log.info("Spell was updated " + spell.toString());

        } catch (SQLException e) {
            Log.warn("Spell was not able to be updated " + spell.toString());
        }
    }

    public void deleteSpell(Spell spell){
        try {
            spellDao.deleteSpell(spell);
            Log.info("Spell was deleted: " + spell.toString());
        } catch (SQLException e) {
            Log.warn("Spell: " + spell.toString() + " was not able to be deleted: " + e);
        }
    }

    private void initializeAllSpellsList() {
        this.allSpells = this.getAllSpells();
    }
}
