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
    private static AdminController admin = new AdminController(casterService, playerService, spellService);
    private static SpellPointsController control = new SpellPointsController(casterService, playerService, spellService, calcService);
    private static SpellPointsWebController webControl = new SpellPointsWebController(control);
    private static WebAdminController webAdmin = new WebAdminController(admin);
    
    private static Javalin app;
    private static Scanner scan = new Scanner(System.in);;
    
    public static void main(String[] args) {
        try {
            app = Javalin.create().start(8113);

            app.post("/game-management/user/login", ctx -> webControl.promptLogin(ctx));
            app.post("/game-management/user/create", ctx -> webControl.promptUserCreate(ctx));
            app.get("/game-management/user/spells/available", ctx -> webControl.getSpells(ctx));
            app.post("/game-management/user/spell/cast", ctx -> webControl.castSpell(ctx));
            app.get("game-management/user/rest", ctx -> webControl.rest(ctx));
            app.get("game-management/user/status", ctx -> webControl.getStatus(ctx));

            app.get("/data-management/players", ctx -> webAdmin.getPlayers(ctx));
            app.get("/data-management/player/:id", ctx -> webAdmin.getPlayer(ctx));
            app.put("/data-management/player/:id", ctx -> webControl.updatePlayer(ctx));
            app.post("/data-management/player/:id", ctx -> webControl.promptUserCreate(ctx));
            app.delete("/data-management/player/:id", ctx -> webControl.deletePlayer(ctx));

            app.get("/data-management/casters", ctx -> webControl.getAllCasters(ctx));
            app.get("/data-management/caster/:id", ctx -> webControl.getCaster(ctx));
            app.post("/data-management/caster/:id", ctx -> webAdmin.postCaster(ctx));
            app.delete("/data-management/caster/:id", ctx -> webAdmin.deleteCaster(ctx));

            app.get("/data-management/spells", ctx -> webAdmin.getSpells(ctx));
            app.get("/data-management/spell/:name", ctx -> webControl.getSpell(ctx));
            app.post("/data-management/spell/:id", ctx -> webAdmin.postSpell(ctx));
            app.delete("/data-management/spell/:id", ctx -> webAdmin.deleteSpell(ctx));

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
