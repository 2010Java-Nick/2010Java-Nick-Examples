package SpellPointTracker.views;

import org.apache.log4j.Logger;

import SpellPointTracker.pojos.*;
import SpellPointTracker.controllers.SpellPointsController;

public class ConsoleUI implements UserInterface {

    private SpellPointsController control;

    private static Logger Log = Logger.getLogger("consoleUILog");

    public ConsoleUI(SpellPointsController controller) {
        super();
        this.control = controller;
    }


    /**
     * Starts the user interface and then asks for user to log in.
     */
    @Override
    public void startInterface() {
        // TODO Starts user interface, prints welcome.
        //      Ask if they would like to login or create an account.
        //      calls this.promptLogin or this.promptUserCreate

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
    private boolean promptLogin() {
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
    private boolean promptUserCreate() {
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
    private boolean castSpell(){
        // TODO calls controller.getAvailableSpellNames and prints them
        // asks which spell to cast and sends it to controller.castSpell
        return false;
    }

    /**
     * 
     * @return
     */
    private void rest(){
        // TODO calls controller.rest

    }
}
