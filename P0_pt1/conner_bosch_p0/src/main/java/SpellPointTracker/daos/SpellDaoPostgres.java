package SpellPointTracker.daos;

import java.util.List;

import SpellPointTracker.pojos.Spell;

public class SpellDaoPostgres implements SpellDao{

    @Override
    public void createSpell(Spell spell){
        //TODO Implement createSpell
    }

    @Override
    public Spell readSpell(int spellId){
        //TODO Implement readSpell
        return null;
    }

    @Override
    public List<Spell> readAllSpells(){
        //TODO Implement readAllSpells
        return null;
    }

    @Override
    public Spell updateSpell(int spellId, Spell spell){
        //TODO Implement updateSpell        
        return null;
    }

    @Override
    public void deleteSpell(Spell spell){
        //TODO Implement deleteSpell
    }
    
}