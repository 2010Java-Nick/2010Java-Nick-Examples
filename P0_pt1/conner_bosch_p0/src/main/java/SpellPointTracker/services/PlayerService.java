package SpellPointTracker.services;

import java.util.List;

import SpellPointTracker.pojos.Player;

public interface PlayerService {

    public List<Player> getPlayers();

    public void setPlayers(List<Player> players);

    public Player getPlayer(String username, String password);

    public boolean createPlayer(String username, String password, int level, int casterType);

}
