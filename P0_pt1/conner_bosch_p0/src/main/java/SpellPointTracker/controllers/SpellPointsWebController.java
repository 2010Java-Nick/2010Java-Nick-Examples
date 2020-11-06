package SpellPointTracker.controllers;

import java.util.List;

import org.apache.log4j.Logger;

import io.javalin.http.Context;

public class SpellPointsWebController {

    private SpellPointsController control;

    private static Logger Log = Logger.getLogger("webControllerLog");

    public SpellPointsWebController(SpellPointsController control){
        this.control = control;
    }

    /**
     * Asks the user for name and password
     */
    public void promptLogin(Context ctx) {
        Log.info("Responding to login request");;

        //User input for username
        String username = ctx.formParam("username");
        
        //User input for password
        String password = ctx.formParam("password");

        if (!control.setCurrentPlayer(username, password)){
            Log.warn("Error logging user with name: " + username);
            ctx.html("Error logging user with name: " + username);
        }
        else {
            Log.info(username + " Logged in");
            ctx.html(username + " successfully logged in!");
        }
    }

    /**
     * Askes the user for name, password, level and casterType
     */
    public void promptUserCreate(Context ctx) {
        Log.info("Responding to create user request");;

        //User input for username
        String username = ctx.formParam("username");
        
        //User input for password
        String password = ctx.formParam("password");

        int level = 0;
        //User input for level
        try {
            level = Integer.parseInt(ctx.formParam("level"));
        }
        catch (NumberFormatException e){
            Log.error("NumberFormatException thrown in level read of promptUserCreate: " + e);
            ctx.html("Level input error, numerical input required");
        }

        //User input for Caster Type 0=Bard 1=Cleric 2=Druid 3=Paladin 4=Sorcerer 5=Warlock 6=Wizard
        int casterType = 0;
        try {
            casterType = Integer.parseInt(ctx.formParam("casterType"));
        }
        catch (NumberFormatException e){
            Log.error("NumberFormatException thrown in casterType read of promptUserCreate: " + e);
            ctx.html("Caster type input error, numerical input required");
        }

        //Attempt to create player with provided information
        if (!control.createNewPlayer(username, password, level, casterType)){
            Log.warn("Error in creating account with username:" + username);
            ctx.html("Error in creating account with username:" + username);
        }
        else {
            Log.info(username + " created");
            ctx.html(username + " successfully created!");
        }
    }

    /**
     * Returns html of the names of spells available to cast
     * @param ctx
     */
    public void getSpells(Context ctx){
        Log.info("Responding to getSpells request");

        List<String> listNames = control.getAvailableSpellNames();
        String names = "";
        int i = 0;
        for (String name : listNames){
            if (i < 10){
                names += name + ", ";
            }
            else {
                i = 0;
                names += name + "%n";
            }
        }
        Log.info("Returned " + listNames.size() + " names");
        ctx.html(names);
    }

    /**
     * Takes input on which spell user wants to cast and attempts to cast it.
     */
    public void castSpell(Context ctx){
        Log.info("Responding castSpell request");

        String spell = ctx.formParam("spell");
        
        try {
            if (control.castSpell(spell)) {
                Log.info("Casted spell " + spell);
                ctx.html("You have cast " + spell);
            } else {
                Log.info("castSpell " + spell + "returned false");
                ctx.html("There is no such " + spell + " spell");
            }
        } catch (Exception e) {
            Log.error("Error casting spell: " + spell + " Error: " + e);
            ctx.html("Error casting spell: " + spell);
        }
    }

    /**
     * Tells the controller to rest and reset the current spell points to max
     * @return True on success
     */
    public void rest(Context ctx){
        Log.info("Responding to rest request");

        try {
            control.rest();
            Log.info("Resting");
            ctx.html("You have rested");
        }catch (Exception e) {
            Log.error("Error while resting: " + e);
            ctx.html("Error while resting");
        }
    }
}