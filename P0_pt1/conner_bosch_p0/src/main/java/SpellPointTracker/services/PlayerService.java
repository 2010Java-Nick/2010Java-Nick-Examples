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

    public Player getPlayer(int id);

    public boolean createPlayer(String username, String password, int currentPoints, int level, int casterType);

    public void updatePlayer (int id, String username, String password, int currentPoints, int level, int casterType);

    public void deletePlayer(int id, String username, String password, int currentPoints, int level, int casterType);
}
