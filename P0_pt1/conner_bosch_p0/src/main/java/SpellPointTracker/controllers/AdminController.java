package SpellPointTracker.controllers;

import java.util.List;

import SpellPointTracker.pojos.*;
import SpellPointTracker.services.*;

public class AdminController {
    
    private CasterService casterService;
    private PlayerService playerService;
    private SpellService spellService;

    public AdminController(CasterService casterService, PlayerService playerService, SpellService spellService){
        this.casterService = casterService;
        this.playerService = playerService;
        this.spellService = spellService;
    }

    public List<Player> getAllPlayers(){
        return playerService.getPlayers();
    }

    public Player getPlayer(int id){
        return playerService.getPlayer(id);
    }

    public void createPlayer(int id, String username, String password, int currentPoints, int level, int casterId){
        playerService.createPlayer(id, username, password, currentPoints, level, casterId);
    }

    public void createCaster(int id, String name, boolean halfCaster, Integer[] spellIds){
        casterService.createCaster(id, name, halfCaster, spellIds);
    }

    public void updateCaster(int id, String name, boolean halfCaster, Integer[] spellIds){
        casterService.updateCaster(id, name, halfCaster, spellIds);
    }

    public List<Spell> getAllSpells(){
        return spellService.getAllSpells();
    }

    public void deleteCaster(int id){
        casterService.deleteCaster(id);
    }

    public void createSpell(String name, int level){
        spellService.createSpell(name, level);
    }

    public void createSpell(int id, String name, int level){
        spellService.createSpell(id, name, level);
    }

    public void updateSpell(int id, String name, int level){
        spellService.updateSpell(id, name, level);
    }

    public void deleteSpell(int id){
        spellService.deleteSpell(id);
    }

}
