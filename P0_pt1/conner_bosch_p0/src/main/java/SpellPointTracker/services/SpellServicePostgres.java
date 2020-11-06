package SpellPointTracker.services;

import java.util.List;

import SpellPointTracker.pojos.Spell;

public class SpellServicePostgres implements SpellService {

    @Override
    public List<Spell> getAllSpells() {
        // TODO impliment getAllSpells
        return null;
    }

    @Override
    public Spell getSpell(String spellName) {
        // TODO impliment getSpell
        return null;
    }

    @Override
    public List<Spell> getSpells(int[] spellIds) {
        // TODO impliment getSpells
        return null;
    }

    public void createSpell(){
        // TODO impliment createSpell
    }

    public Spell updateSpell(int spellId, Spell spell){
        // TODO impliment updateSpell
        return null;
    }

    public void deleteSpell(Spell spell){
        // TODO impliment deleteSpell
    }
}
