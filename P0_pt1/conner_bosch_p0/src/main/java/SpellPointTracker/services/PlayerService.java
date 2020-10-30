package SpellPointTracker.services;

import SpellPointTracker.pojos.Player;

public interface PlayerService {

    public Player getPlayer(String username, String password);

    public void createPlayer(String username, String password, int level, int casterType);

}
