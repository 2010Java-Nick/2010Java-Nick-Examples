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

    public void getCasters(Context ctx){
        
    }

    public void getCaster(Context ctx){
        
    }

    public void postCaster(Context ctx){
        
    }

    public void deleteCaster(Context ctx){
        
    }

    public void getSpells(Context ctx){
        
    }

    public void getSpell(Context ctx){

    }

    public void postSpell(Context ctx){
        
    }

    public void deleteSpell(Context ctx){
        
    }

}
