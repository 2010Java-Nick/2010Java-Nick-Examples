package SpellPointTracker;

import java.util.Scanner;

import SpellPointTracker.controllers.*;
import SpellPointTracker.services.*;
import io.javalin.Javalin;

public class ServerDriver {
    private static CalculatorService calcService = new CalculatorService();
    private static CasterService casterService = new CasterServiceImpl();
    private static PlayerService playerService = new PlayerServiceImpl();
    private static SpellService spellService = new SpellServiceImpl();
    private static SpellPointsController control = new SpellPointsController(casterService, playerService, spellService, calcService);
    private static SpellPointsWebController webControl = new SpellPointsWebController(control);
    
    private static Javalin app;
    private static Scanner scan = new Scanner(System.in);;
    
    public static void main(String[] args) {
        try {
            app = Javalin.create().start(8113);
            app.get("/hello", ctx -> ctx.html("Hello World"));
            app.post("/login", ctx -> webControl.promptLogin(ctx));
            app.post("/createUser", ctx -> webControl.promptUserCreate(ctx));
            app.get("/availableSpells", ctx -> webControl.getSpells(ctx));
            app.post("/castSpell", ctx -> webControl.castSpell(ctx));
            app.get("/rest", ctx -> webControl.rest(ctx));

            String input = "";
            do {
                input = scan.nextLine();
            } while (input != "End");
        } catch (Exception e) {
            scan.close();
            app.stop();
        }
        finally {
            scan.close();
            app.stop();
        }
    }
}
