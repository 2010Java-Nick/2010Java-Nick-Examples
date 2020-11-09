package SpellPointTracker.services;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import SpellPointTracker.daos.PlayerDaoPostgres;
import SpellPointTracker.pojos.Player;
import SpellPointTracker.util.ConnectionUtil;

public class PlayerServicePostgres implements PlayerService {

    private static Logger Log = Logger.getLogger("serviceLog");

    private PlayerDaoPostgres playerDao;

    public PlayerServicePostgres(ConnectionUtil connectionUtil) {
        super();
        playerDao = new PlayerDaoPostgres(connectionUtil);
    }

    @Override
    public boolean createPlayer(String username, String password, int currentPoints, int level, int casterType) {
        try {
            Player player = new Player(0, username, password, currentPoints, level, casterType);
            playerDao.createPlayer(player);
            Log.info("Player " + username + " was entered into the database.");
            return true;

        } catch (SQLException e) {
            Log.warn("Player " + username + " was not able to be entered into database: " + e);
            return false;
        }
    }

    @Override
    public Player getPlayer(String username, String password) {
        List<Player> players = this.getPlayers();

        for (Player p : players) {
            if (p.getUsername().equals(username) && p.getPassword().equals(password)){
                Log.info("Player " + username + " successfully retrieved.");
                return p;
            }
        }
        Log.warn("Player " + username + " username and password not found.");
        return null;
    }

    @Override
    public Player getPlayer(int id) {
        List<Player> players = this.getPlayers();

        for (Player p : players) {
            if (p.getId() == id){
                Log.info("Player " + p.getUsername() + " successfully retrieved.");
                return p;
            }
        }
        Log.warn("Player with id: " + id + " not found.");
        return null;
    }

    @Override
    public List<Player> getPlayers() {
        try {
            List<Player> players = playerDao.readAllPlayers();
            Log.info("Retrieved "+ players.size() +" players from database.");
            return players;

        } catch (SQLException e){
            Log.warn("Retrieval of all players was unsuccessful: " + e);
            return null;
        }
    }

    @Override
    public void updatePlayer (int id, String username, String password, int currentPoints, int level, int casterType){
        
        Player player = new Player(id, username, password, currentPoints, level, casterType);

        try {
            playerDao.updatePlayer(player);
            Log.info("Player " + player.getUsername() + " was updated with new info.");
        
        } catch (SQLException e) {
            Log.warn("Player " + player.getUsername() + " was not able to be updated: " + e);
        }
    }

    @Override
    public void deletePlayer(int id, String username, String password, int currentPoints, int level, int casterType){
        
        Player player = new Player(id, username, password, currentPoints, level, casterType);

        try {
            playerDao.deletePlayer(player);
            Log.info("Player " + player.getUsername() + " was deleted.");
        } catch (SQLException e) {
            Log.warn("Player " + player.getUsername() + " was not deleted: " + e);
        }
    }
}