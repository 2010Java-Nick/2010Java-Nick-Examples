package SpellPointTracker.services;

import java.util.List;

import SpellPointTracker.pojos.Player;

/**
 * Handles passing Player objects between storage and the controller
 * and related methods
 */
public interface PlayerService {

    public List<Player> getPlayers();

    public Player getPlayer(String username, String password);

    public boolean createPlayer(String username, String password, int currentPoints, int level, int casterType);

}
