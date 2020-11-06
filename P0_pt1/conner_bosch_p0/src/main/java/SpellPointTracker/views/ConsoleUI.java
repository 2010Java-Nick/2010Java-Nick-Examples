package SpellPointTracker.views;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import SpellPointTracker.controllers.SpellPointsController;

/**
 * Runs console commands and retrieves user input for the program
 * All logic passes to a SpellPointsController for evaluation.
 */
public class ConsoleUI implements UserInterface {

    private SpellPointsController control;
    private BufferedReader console;

    private static Logger Log = LogManager.getLogger("consoleUILog");

    public ConsoleUI(SpellPointsController controller, BufferedReader source) {
        super();
        this.control = controller;
        this.console = source;
    }


    /**
     * Starts the user interface and then asks for user to log in.
     */
    @Override
    public boolean startInterface() {
        // Starts user interface, prints welcome statment
        System.out.println("Welcome to the Spell Points Tracker");
        System.out.println("Would you like to Login? or Create an account?");
        Log.info("Interface booted up");

        //Handles user input
        String input = "";
        try {
            input = console.readLine();
        } catch (IOException e){
            Log.error("IOException thrown in startInterface: " + e);
            System.out.println("IOException thrown:" + e);
        }

        switch (input) {
            case "Login":
            return this.promptLogin();

            case "Create":
            return this.promptUserCreate();

            case "End":
            this.endInterface();
            return false;

            default:
            System.out.println("Please type 'Login', 'Create', or 'End'");
            return false;
        }
    }

    /**
     * Ends the program closing all resources properly
     */
    @Override
    public void endInterface() {
        Log.info("User Ended session, last user:" + control.getStatus());
        System.exit(0);
    }

    /**
     * Standard running step of the program. Prompts user to pick between
     * casting a spell, taking a rest, or ending the program.
     */
    @Override
    public boolean promptAction() {
        // Ask what the player would like to do?
        String status = control.getStatus();
        System.out.println(status);
        System.out.println("Would you like to 'Cast', 'Rest' or 'End'?");

        String input = "";
        try {
            input = console.readLine();
        } catch (IOException e){
            Log.error("IOException thrown in promptAction: " + e);
            System.out.println("IOException thrown:" + e);
        }

        switch (input) {
            case "Cast":
            this.castSpell();
            return false;

            case "Rest":
            this.rest();
            return false;

            case "End":
            this.endInterface();
            return true;

            default:
            System.out.println("Please type 'Cast', 'Rest', or 'End'");
            return false;
        }
    }

    /**
     * Asks the user for name and password
     * @return success or not
     */
    public boolean promptLogin() {
        // Prompts user for name and password,
        System.out.println("Please enter a username and password...");
        System.out.println("Username: ");

        //User input for username
        String username = "";
        try {
            username = console.readLine();
        } 
        catch (IOException e){
            Log.error("IOException thrown in username read of promptLogin: " + e);
            System.out.println("IOException thrown:" + e);
        }

        //User input for password
        System.out.println("Password: ");
        String password = "";
        try {
            password = console.readLine();
        } 
        catch (IOException e){
            Log.error("IOException thrown in password read of promptLogin: " + e);
            System.out.println("IOException thrown:" + e);
        }
        if (!control.setCurrentPlayer(username, password)){
            System.out.println("Login error, please try again");
            Log.warn("Error logging user with name" + username);
            return false;
        }
        else {
            Log.info(username + " Logged in");
            return true;
        }
    }

    /**
     * Askes the user for name, password, level and casterType
     * @return success or not
     */
    public boolean promptUserCreate() {
        // Create prompts user for name, password, level and casterType
        System.out.println("Please enter a username, password, your current level, and Caster type...");
        System.out.println("Username: ");

        //User input for username
        String username = "";
        try {
            username = console.readLine();
        } 
        catch (IOException e){
            Log.error("IOException thrown in username read of promptUserCreate: " + e);
            System.out.println("IOException thrown:" + e);
        }

        //User input for password
        System.out.println("Password: ");
        String password = "";
        try {
            password = console.readLine();
        } 
        catch (IOException e){
            Log.error("IOException thrown in password read of promptUserCreate: " + e);
            System.out.println("IOException thrown:" + e);
        }

        //User input for level
        System.out.println("Level: ");
        int level = 0;

        try {
            level = Integer.parseInt(console.readLine());
        } 
        catch (IOException e){
            Log.error("IOException thrown in level read of promptUserCreate: " + e);
            System.out.println("IOException thrown:" + e);
        } 
        catch (NumberFormatException e){
            Log.error("NumberFormatException thrown in level read of promptUserCreate: " + e);
            System.out.println("Level input needs to be a number");
            return false;
        }

        //User input for Caster Type
        System.out.println("Caster Type: (0=Bard 1=Cleric 2=Druid 3=Paladin 4=Sorcerer 5=Warlock 6=Wizard)");
        int casterType = 0;

        try {
            casterType = Integer.parseInt(console.readLine());
        } 
        catch (IOException e){
            Log.error("IOException thrown in caster read of promptUserCreate: " + e);
            System.out.println("IOException thrown:" + e);
        } 
        catch (NumberFormatException e){
            Log.error("NumberFormatException thrown in caster read of promptUserCreate: " + e);
            System.out.println("Caster input needs to be a number");
            return false;
        }

        if (!control.createNewPlayer(username, password, level, casterType)){
            System.out.println("Error in creating account, please try again");
            Log.warn("Error in creating account with username:" + username);
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Lists spells player can currently cast, asks for input on
     * which spell user wants to cast and attempts to cast it.
     * @return True on success
     */
    public boolean castSpell(){
        // Calls controller.getAvailableSpellNames and prints them

        System.out.println("The spells available to cast are: ");
        List<String> names = control.getAvailableSpellNames();
        int i = 0;
        for (String name : names){
            if (i < 10){
                System.out.print(name + ", ");
            }
            else {
                i = 0;
                System.out.println(name);
            }
        }
        System.out.println();
        System.out.println("Which spell would you like to cast?");
        String spell = "";
        try {
            spell = console.readLine();
        } catch (IOException e){
            Log.error("IOException thrown in spell read of castSpell: " + e);
            System.out.println("IOException thrown:" + e);
        }

        if (names.contains(spell)){
            return control.castSpell(spell);
        }
        System.out.println("Please write the spell exactly as you read it");
        return false;
    }

    /**
     * Tells the controller to rest and reset the current spell points to max
     * @return True on success
     */
    public boolean rest(){
        try {
            System.out.println("Resting...");
            control.rest();
            return true;
        }catch (Exception e) {
            Log.error("Exception while resting: " + e);
            return false;
        }
    }
}
