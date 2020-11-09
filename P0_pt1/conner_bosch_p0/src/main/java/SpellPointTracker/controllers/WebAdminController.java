package SpellPointTracker.controllers;

import java.util.List;

import org.apache.log4j.Logger;

import SpellPointTracker.pojos.*;
import io.javalin.http.Context;

public class WebAdminController {

    private AdminController admin;

    private static Logger Log = Logger.getLogger("controllerLog");

    public WebAdminController(AdminController admin){
        this.admin = admin;
    }

    public void getPlayers(Context ctx){
        List<Player> players = admin.getAllPlayers();
        String html = "Players: ";

        for(Player p : players) {
            html += p.toString() + ", ";
        }
        ctx.html(html);
    }

    public void getPlayer(Context ctx){
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Player player = admin.getPlayer(id);
            ctx.html(player.toString());

        } catch (NumberFormatException e) {
            Log.warn("NumFormException in getPlayer, input: " + ctx.pathParam("id") + " Exception: " + e);
        }
    }

    public void postCaster(Context ctx){
        try {
        int id = Integer.parseInt(ctx.pathParam("id"));
        String name = ctx.pathParam("name");
        boolean halfCaster = Boolean.parseBoolean(ctx.pathParam("half_caster"));
        String[] spells = ctx.pathParam("spell_ids").split(", ");
        Integer[] spellIds = new Integer[spells.length];
        
        for (int i = 0; i < spells.length; i++){
            spellIds[i] = Integer.parseInt(spells[i]); 
        }
        admin.createCaster(id, name, halfCaster, spellIds);

        Log.info("Caster " + name + " successfully added.");
        ctx.html("Caster " + name + " successfully added.");

    } catch (NumberFormatException e) {
            Log.warn("NumFormException in postCaster, Exception: " + e);
            ctx.html("NumFormException in postCaster, Exception: " + e);
        }
    }

    public void deleteCaster(Context ctx){
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            admin.deleteCaster(id);
            Log.info("Successfully deleted caster number: " + id);
            ctx.html("Successfully deleted caster number: " + id);
        } catch (NumberFormatException e) {
            Log.warn("NumFormException in deleteCaster, exception: " + e);
            ctx.html("NumFormException in deleteCaster, exception: " + e);
        }
    }

    public void getSpells(Context ctx){
        List<Spell> spells = admin.getAllSpells();

        String allSpells = "";

        for (Spell s : spells) {
            allSpells += s.getName() + ", ";
        }

        if (!allSpells.equals("")){
            Log.info("Retrieved all spells successfully");
            ctx.html("Retrieved all spells successfully: " + allSpells);
        } else {
            Log.warn("Error in retrieving spells");
            ctx.html("Error in retrieving spells");
        }
    }

    public void postSpell(Context ctx){
        try {
            String name = ctx.formParam("name");
            int level = Integer.parseInt(ctx.formParam("level"));
            admin.createSpell(name, level);
            Log.info("Retrieved spell "+ name +" successfully");
            ctx.html("Retrieved spell "+ name +" successfully");
        } catch (NumberFormatException e) {
            Log.warn("Error in retrieving spell: " + e);
            ctx.html("Error in retrieving spell: " + e);
        }

    }

    public void deleteSpell(Context ctx){
        try {
            int id = Integer.parseInt("id");
            admin.deleteSpell(id);
            Log.info("Deleted spell "+ id +" successfully");
            ctx.html("Deleted spell "+ id +" successfully");
        } catch (NumberFormatException e) {
            Log.warn("Error in deleteing spell: " + e);
            ctx.html("Error in deleteing spell: " + e);
    }
}
