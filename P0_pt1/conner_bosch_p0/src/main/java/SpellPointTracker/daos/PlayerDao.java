package SpellPointTracker.daos;

import java.util.List;

import SpellPointTracker.pojos.Player;

public interface PlayerDao {

    public void createPlayer(Player player);

    public Player readPlayer(int playerId);

    public List<Player> readAllPlayers();

    public Player updatePlayer (int playerId, Player player);

    public void deletePlayer(Player player);
    
}
