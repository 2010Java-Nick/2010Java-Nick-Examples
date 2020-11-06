package SpellPointTracker.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class textConvertionService {
    static File file = new File("src\\main\\resources\\spellList.txt");
    static Scanner scan;
    public static void main(String[] args) {

        String line;
        String levelString = "Cantrip 1st 2nd 3rd 4th 5th 6th 7th 8th 9th"; 
        int l = 0;
        
        try {
            scan = new Scanner(file);
            while (scan.hasNextLine()){
                line = scan.nextLine();
                if (levelString.contains(line)){
                    l = evaluateSpellLevel(line);
                }
                else {
                    String name = line.replaceAll("\\(.*\\)", "");
                    String[] casters = line.replace(name, "").replace("(", "").replace(")", "").replace(" ", "").split(",");
                    System.out.println(name + "is a level " + l + " spell");
                    System.out.print("This spell is on " );
                    for (String s : casters){
                        System.out.print(s + ", ");
                    }
                    System.out.println("list");
                }
            }
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
