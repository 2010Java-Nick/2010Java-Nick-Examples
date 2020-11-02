package SpellPointTracker.views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.apache.log4j.Logger;

import SpellPointTracker.pojos.*;
import SpellPointTracker.controllers.SpellPointsController;

public class ConsoleUI implements UserInterface {

    private SpellPointsController control;
    private BufferedReader console;

    private static Logger Log = Logger.getLogger("consoleUILog");

    public ConsoleUI(SpellPointsController controller, BufferedReader source) {
        super();
        this.control = controller;
        this.console = source;
    }


    /**
     * Starts the user interface and then asks for user to log in.
     */
    @Override
    public void startInterface() {
        // TODO Starts user interface, prints welcome.
        //      Ask if they would like to login or create an account.
        //      calls this.promptLogin or this.promptUserCreate
/*         String input = "";

        try {
            input = console.readLine();
        } catch (IOException e){
            System.out.println("IOException thrown:" + e);
        }

        if (input == "Login") {
            this.promptLogin();
        }
        else if (input == "Create") {
            this.promptUserCreate();
        }
        else {
            System.out.println("Failed!");
        } */

    }

    /**
     * Ends the program closing all resources properly
     */
    @Override
    public void endInterface() {
        // TODO Ends program gracefully

    }

    /**
     * Standard running step of the program. Prompts user to pick between
     * casting a spell, taking a rest, or ending the program.
     */
    @Override
    public void promptAction() {
        // TODO Ask what the player would like to do?
        //      If cast spell, call this.castSpell
        //      If rest, call this.rest
        //      If end, call this.endInterface

    }

    /**
     * Asks the user for name and password
     * @return success or not
     */
    public boolean promptLogin() {
        // TODO Ask if they would like to login or create an account.
        //      Then prompts user for name and password,
        //      Send to controller.setCurrentPlayer
        //      if success call prompt action
        //      else recurse
        return false;
    }

    /**
     * Askes the user for name, password, level and casterType
     * @return success or not
     */
    public boolean promptUserCreate() {
        // TODO Create prompts user for name, password, level and casterType
        //      Sent to controller.createNewPlayer
        //      if success call promptLogin
        //      else recurse
        return false;
    }

    /**
     * 
     * @return
     */
    public boolean castSpell(){
        // TODO calls controller.getAvailableSpellNames and prints them
        // asks which spell to cast and sends it to controller.castSpell
        return false;
    }

    /**
     * 
     * @return
     */
    public void rest(){
        // TODO calls controller.rest

    }
}
