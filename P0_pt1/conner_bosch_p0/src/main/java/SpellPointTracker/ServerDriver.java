package SpellPointTracker;

import java.util.Scanner;

import SpellPointTracker.controllers.*;
import SpellPointTracker.services.*;
import SpellPointTracker.util.ConnectionUtil;
import io.javalin.Javalin;

public class ServerDriver {
    private static ConnectionUtil connUtil = new ConnectionUtil();
    private static CalculatorService calcService = new CalculatorService();
    private static CasterService casterService = new CasterServicePostgres(connUtil);
    private static PlayerService playerService = new PlayerServicePostgres(connUtil);
    private static SpellService spellService = new SpellServicePostgres(connUtil);
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
