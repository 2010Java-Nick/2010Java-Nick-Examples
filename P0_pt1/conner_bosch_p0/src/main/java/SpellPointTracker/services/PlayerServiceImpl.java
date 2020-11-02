package SpellPointTracker.services;

import SpellPointTracker.pojos.Player;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

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
     * 
     */
    @Override
    public boolean createPlayer(String username, String password, int level, int casterType) {
        try {
            Player player = new Player(playerCollection.size(), username, password, 0, level, casterType);
            playerCollection.add(player);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 
     */
    @Override
    public Player getPlayer(String username, String password){
        for (Player player : playerCollection) {
            if (player.getUsername().equals(username) && player.getPassword().equals(password)){
                return player;
            }
        }
        return null;
    }    
}
