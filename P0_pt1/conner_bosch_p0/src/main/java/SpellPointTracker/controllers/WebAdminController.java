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
            html += "<p>" + p.toString() + ",</p> ";
        }
        ctx.html(html);
    }

    public void getPlayer(Context ctx){
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Player player = admin.getPlayer(id);
            if (player == null) {
                ctx.html("Player " + id + " was not found");
                Log.info("Player " + id + " was not found");
            }
            ctx.html(player.toString());

        } catch (NumberFormatException e) {
            Log.warn("NumFormException in getPlayer, input: " + ctx.pathParam("id") + " Exception: " + e);
            ctx.html("Input must be a number.");
        }
    }

    public void createPlayer(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            String username = ctx.formParam("username");
            String password = ctx.formParam("password");
            int currentPoints = Integer.parseInt(ctx.formParam("points"));
            int level = Integer.parseInt(ctx.formParam("level"));
            int casterId  = Integer.parseInt(ctx.formParam("caster_id"));

            admin.createPlayer(id, username, password, currentPoints, level, casterId);

            Log.info("Player " + username + " was successfully created.");
            ctx.html("Player " + username + " was successfully created.");

        } catch (NumberFormatException e) {
            Log.warn("NumFormException in createPlayer input. Exception: " + e);
            ctx.html("Input must be a number.");
        }
    }

    public void postCaster(Context ctx){
        try {
        int id = Integer.parseInt(ctx.pathParam("id"));
        String name = ctx.formParam("name");
        boolean halfCaster = Boolean.parseBoolean(ctx.formParam("half_caster"));
        String[] spells = ctx.formParam("spell_ids").replace("[", "").replace("]", "").split(", ");
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
            allSpells += "<p>" + s + ", </p>";
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
            Log.info("Created spell "+ name +" successfully");
            ctx.html("Created spell "+ name +" successfully");
        } catch (NumberFormatException e) {
            Log.warn("Error in creating spell: " + e);
            ctx.html("Error in creating spell: " + e);
        }

    }

    public void deleteSpell(Context ctx){
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            admin.deleteSpell(id);
            Log.info("Deleted spell "+ id +" successfully");
            ctx.html("Deleted spell "+ id +" successfully");
        } catch (NumberFormatException e) {
            Log.warn("Error in deleteing spell: " + e);
            ctx.html("Error in deleteing spell: " + e);
        }
    }

    public void updateCaster(Context ctx){

        int id = -1;

        try {
            id = Integer.parseInt(ctx.pathParam("id"));
            String name = ctx.formParam("name");
            boolean halfCaster = Boolean.parseBoolean(ctx.formParam("half_caster"));
            
            String[] spells = ctx.formParam("spell_ids").replace("[", "").replace("]", "").split(", ");
            Integer[] spellIds = new Integer[spells.length];
            
            for (int i = 0; i < spells.length; i++){
                spellIds[i] = Integer.parseInt(spells[i]); 
            }

            admin.updateCaster(id, name, halfCaster, spellIds);

            Log.info("Updated caster "+ name +" successfully");
            ctx.html("Updated caster "+ name +" successfully");

        } catch (NumberFormatException e) {
            Log.warn("Error in updating caster: " + id + " Exception: " + e);
            ctx.html("Error in updating caster: " + id + " Exception: " + e);
        }
    }

    public void updateSpell(Context ctx) {

        int id = -1;

        try {
            id = Integer.parseInt(ctx.pathParam("id"));
            String name = ctx.formParam("name");
            int level = Integer.parseInt(ctx.formParam("level"));

            admin.updateSpell(id, name, level);

            Log.info("Updated spell "+ name +" successfully");
            ctx.html("Updated spell "+ name +" successfully");
        } catch (NumberFormatException e) {
            Log.warn("Error in updating spell: " + id + " Exception: " + e);
            ctx.html("Error in updating spell: " + id + " Exception: " + e);
        }
    }
}