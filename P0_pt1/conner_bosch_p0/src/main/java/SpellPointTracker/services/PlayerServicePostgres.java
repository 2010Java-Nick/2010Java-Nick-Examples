package SpellPointTracker.services;

import java.util.List;

import SpellPointTracker.pojos.Player;

public class PlayerServicePostgres implements PlayerService {

    @Override
    public boolean createPlayer(String username, String password, int currentPoints, int level, int casterType) {
        // TODO impliment createPlayer
        return false;
    }

    @Override
    public Player getPlayer(String username, String password) {
        // TODO impliment getPlayer
        return null;
    }

    @Override
    public List<Player> getPlayers() {
        // TODO impliment getPlayers
        return null;
    }

    public Player updatePlayer (int playerId, Player player){
        //TODO implement updatePlayer
        return null;
    }

    public void deletePlayer(Player player){
        //TODO implement deletePlayer
    }
}