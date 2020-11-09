package SpellPointTracker.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import SpellPointTracker.pojos.Player;
import SpellPointTracker.util.ConnectionUtil;

public class PlayerDaoPostgres implements PlayerDao {

    private PreparedStatement stmt;

    private static Logger Log = Logger.getLogger("daoLog");

    private ConnectionUtil connUtil;

    public PlayerDaoPostgres(ConnectionUtil connectionUtil){
        super();
        connUtil = connectionUtil;
    }

    @Override
    public void createPlayer(Player player) throws SQLException{

        try(Connection conn = connUtil.createConnection()) {

            if (player.getId() > 0) {
                String sql = "INSERT INTO player VALUES "
                            +"(?, ?, ?, ?, ?, ?);";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, player.getId());
                stmt.setString(2, player.getUsername());
                stmt.setString(3, player.getPassword());
                stmt.setInt(4, player.getCurrentPoints());
                stmt.setInt(5, player.getCurrentLevel());
                stmt.setInt(6, player.getCasterType());
            } else {
                String sql = "INSERT INTO player (username, passphrase, current_points, current_level, caster_id) VALUES "
                            +"(?, ?, ?, ?, ?);";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, player.getUsername());
                stmt.setString(2, player.getPassword());
                stmt.setInt(3, player.getCurrentPoints());
                stmt.setInt(4, player.getCurrentLevel());
                stmt.setInt(5, player.getCasterType());
            }


            stmt.executeUpdate();

        } catch (SQLException e) {
            Log.warn("PlayerDaoPostgres.createPlayer threw SQLException: " + e);
            throw e;
        }
    }

    @Override
    public Player readPlayer(int playerId) throws SQLException{
        try(Connection conn = connUtil.createConnection()) {

            String sql = "SELECT * FROM player "
                        + "WHERE player_id = ?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, playerId);

            //Return result of SQL query
            ResultSet rs = stmt.executeQuery();
            rs.next();

            //Read result into player
            Player player = new Player(rs.getInt(1), 
                                    rs.getString(2),
                                    rs.getString(3), 
                                    rs.getInt(4), 
                                    rs.getInt(5),
                                    rs.getInt(6));
            return player;
        } catch (SQLException e) {
            Log.warn("PlayerDaoPostgres.readPlayer threw SQLException: " + e);
            throw e;
        }
    }

    @Override
    public List<Player> readAllPlayers() throws SQLException{

        List<Player> players = new ArrayList<>();

        try(Connection conn = connUtil.createConnection()) {

            String sql = "SELECT * FROM player;";
            stmt = conn.prepareStatement(sql);

            //Return result of SQL query
            ResultSet rs = stmt.executeQuery();

            //Loop through result set
            while(rs.next()) {

                //Read result into player and add to list
                Player player = new Player(rs.getInt(1), 
                                        rs.getString(2),
                                        rs.getString(3), 
                                        rs.getInt(4), 
                                        rs.getInt(5),
                                        rs.getInt(6));
                players.add(player);
                
            }
        } catch (SQLException e) {
            Log.warn("PlayerDaoPostgres.readPlayer threw SQLException: " + e);
            throw e;
        }
        return players;
    }

    @Override
    public void updatePlayer(Player player) throws SQLException{

        try(Connection conn = connUtil.createConnection()) { 
            
            String sql  = "UPDATE player SET username = ?, passphrase = ?, " 
            + "current_points = ?, current_level = ?, caster_id = ? "
            + "WHERE player_id = ?;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, player.getUsername());
            stmt.setString(2, player.getPassword());
            stmt.setInt(3, player.getCurrentPoints());
            stmt.setInt(4, player.getCurrentLevel());
            stmt.setInt(5, player.getCasterType());
            stmt.setInt(6, player.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            Log.warn("PlayerDaoPostgres.updatePlayer threw SQLException: " + e);
            throw e;
        }
    }

    @Override
    public void deletePlayer(Player player) throws SQLException{
		try(Connection conn = connUtil.createConnection()) { 

		    String sql = "DELETE FROM player "
                    + "WHERE player_id = ?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, player.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            Log.warn("PlayerDaoPostgres.deletePlayer threw SQLException: " + e);
            throw e;
        }
    }
}
