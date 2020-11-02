package SpellPointTracker.services;

import SpellPointTracker.pojos.Player;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * A class that holds the different players as well as the information
 * for creation and validation. Methods are those pertaining to the players.
 */
public class PlayerServiceImpl implements PlayerService {

    private static Logger Log = Logger.getLogger("playerServiceLog");

    List<Player> playerCollection = new ArrayList<Player>();

    public List<Player> getPlayers(){
        return this.playerCollection;
    }

    public void setPlayers(List<Player> players){
        this.playerCollection = players;
    }

    /**
     * Creates a player object and adds it to the internal collection
     * @param username
     * @param password
     * @param currentPoints
     * @param level
     * @param casterType 0=Bard 1=Cleric 2=Druid 3=Paladin 4=Sorcerer 5=Warlock 6=Wizard
     * @return true if operations was successful
     */
    @Override
    public boolean createPlayer(String username, String password, int currentPoints, int level, int casterType) {
        try {
            Player player = new Player(playerCollection.size(), username, password, currentPoints, level, casterType);
            playerCollection.add(player);
            return true;
        } catch (Exception e) {
            Log.error("Error in createPlayer. Username: " + username + "Exception: " + e);
            return false;
        }
    }

    /**
     * Attempts to get a player with username and password matching input.
     * @param username
     * @param password
     * @return null if none found
     */
    @Override
    public Player getPlayer(String username, String password){
        for (Player player : playerCollection) {
            if (player.getUsername().equals(username) && player.getPassword().equals(password)){
                return player;
            }
        }
        Log.info("User " + username + "was not found/logged in.");
        return null;
    }    
}
