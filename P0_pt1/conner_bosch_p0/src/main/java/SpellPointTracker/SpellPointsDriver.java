package SpellPointTracker;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import SpellPointTracker.controllers.SpellPointsController;
import SpellPointTracker.services.*;
import SpellPointTracker.views.ConsoleUI;

/**
 * Main driving class of the program. Loops user until ended.
 */
public class SpellPointsDriver {

    static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
    static CasterService casterService = new CasterServiceImpl();
    static PlayerService playerService = new PlayerServiceImpl();
    static SpellService spellService = new SpellServiceImpl();
    static CalculatorService calcService = new CalculatorService();


    static SpellPointsController control = new SpellPointsController(casterService, playerService, spellService, calcService);
    static ConsoleUI program = new ConsoleUI(control, console);

    static Boolean pass = false;

    public static void main(String[] args) {

        while(!pass){
            pass = program.startInterface();
        }
        pass = false;

        while(!pass){
            pass = program.promptAction();
        }
    }    
}
