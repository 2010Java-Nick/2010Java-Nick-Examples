package SpellPointTracker.daos;

import java.sql.SQLException;
import java.util.List;

import SpellPointTracker.pojos.Player;

public interface PlayerDao {

    public void createPlayer(Player player) throws SQLException;

    public Player readPlayer(int playerId) throws SQLException;

    public List<Player> readAllPlayers() throws SQLException;

    public void updatePlayer (Player player) throws SQLException;

    public void deletePlayer(Player player) throws SQLException;
    
}
