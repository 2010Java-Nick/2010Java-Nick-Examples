package SpellPointTracker.services;

import SpellPointTracker.pojos.Player;
import org.apache.log4j.Logger;

public class PlayerServiceImpl implements PlayerService {

    private static Logger Log = Logger.getLogger("playerServiceLog");

    /**
     * 
     */
    @Override
    public boolean createPlayer(String username, String password, int level, int casterType) {
        // TODO Auto-generated method stub
        return false;

    }

    /**
     * 
     */
    @Override
    public Player getPlayer(String username, String password) {
        // TODO Auto-generated method stub
        // raises "PlayerNotFoundException"
        return null;
    }    
}
