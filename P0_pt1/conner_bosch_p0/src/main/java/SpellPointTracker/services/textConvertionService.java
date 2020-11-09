package SpellPointTracker.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import SpellPointTracker.controllers.AdminController;
import SpellPointTracker.util.ConnectionUtil;

public class textConvertionService {
    private static File file = new File("src\\main\\resources\\spellList.txt");
    private static Scanner scan;
    private static ConnectionUtil connUtil = new ConnectionUtil();
    private static CasterService casterService = new CasterServicePostgres(connUtil);
    private static PlayerService playerService = new PlayerServicePostgres(connUtil);
    private static SpellService spellService = new SpellServicePostgres(connUtil);
    private static AdminController admin = new AdminController(casterService, playerService, spellService);

    public static void main(String[] args) {

        String line;
        String levelString = "Cantrip 1st 2nd 3rd 4th 5th 6th 7th 8th 9th"; 
        int level = 0;
        List<Integer> bardSpells = new LinkedList<>();
        List<Integer> clericSpells = new LinkedList<>();
        List<Integer> druidSpells = new LinkedList<>();
        List<Integer> paladinSpells = new LinkedList<>();
        List<Integer> rangerSpells = new LinkedList<>();
        List<Integer> sorcererSpells = new LinkedList<>();
        List<Integer> warlockSpells = new LinkedList<>();
        List<Integer> wizardSpells = new LinkedList<>();
        List<Integer> artificerSpells = new LinkedList<>();
        int spellId = 1;
        try {
            scan = new Scanner(file);
            while (scan.hasNextLine()){
                line = scan.nextLine();

                if (levelString.contains(line)){
                    level = evaluateSpellLevel(line);
                }
                else {
                    String name = line.replaceAll("\\(.*\\)", "");
                    String[] casters = line.replace(name, "").replace("(", "").replace(")", "").replace(" ", "").split(",");
                    System.out.println(name + "is a level " + level + " spell");
                    System.out.print("This spell is on " );
                    for (String s : casters){
                        switch (s) {
                            case "bard" :
                                bardSpells.add(spellId);
                            break;

                            case "cleric" :
                                clericSpells.add(spellId);
                            break;

                            case "druid" :
                                druidSpells.add(spellId);
                            break;

                            case "paladin" :
                                paladinSpells.add(spellId);
                            break;

                            case "ranger" :
                                rangerSpells.add(spellId);
                            break;

                            case "sorcerer" :
                                sorcererSpells.add(spellId);
                            break;

                            case "warlock" :
                                warlockSpells.add(spellId);
                            break;

                            case "wizard" :
                                wizardSpells.add(spellId);
                            break;

                            case "artificer" :
                                artificerSpells.add(spellId);
                            break;
                        }
                    }
                    System.out.println("list");
                    admin.createSpell(spellId, name, level);
                    spellId++;
                }
            }

            //admin.updateCaster(1, "Bard", false, bardSpells.toArray(new Integer[bardSpells.size()]));
            admin.updateCaster(2, "Cleric", false, clericSpells.toArray(new Integer[clericSpells.size()]));
            //admin.updateCaster(3, "Druid", false, druidSpells.toArray(new Integer[druidSpells.size()]));
            //admin.updateCaster(4, "Paladin", false, paladinSpells.toArray(new Integer[paladinSpells.size()]));
            //admin.updateCaster(5, "Ranger", false, rangerSpells.toArray(new Integer[rangerSpells.size()]));
            //admin.updateCaster(6, "Sorcerer", false, sorcererSpells.toArray(new Integer[sorcererSpells.size()]));
            //admin.updateCaster(7, "Warlock", false, warlockSpells.toArray(new Integer[warlockSpells.size()]));
            //admin.updateCaster(8, "Wizard", false, wizardSpells.toArray(new Integer[wizardSpells.size()]));
            admin.updateCaster(9, "Artificer", false, artificerSpells.toArray(new Integer[artificerSpells.size()]));

        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        } finally {
            scan.close();
        }
    }

    static public int evaluateSpellLevel(String word){

        switch (word){
            case "Cantrip":
            return 0;

            case "1st":
            return 1;

            case "2nd":
            return 2;

            case "3rd":
            return 3;

            case "4th":
            return 4;

            case "5th":
            return 5;

            case "6th":
            return 6;

            case "7th":
            return 7;

            case "8th":
            return 8;

            case "9th":
            return 9;

            default:
            return -1;
        }
    }
}
