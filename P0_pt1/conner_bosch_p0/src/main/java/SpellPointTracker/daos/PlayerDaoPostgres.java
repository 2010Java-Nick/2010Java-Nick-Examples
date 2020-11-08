package SpellPointTracker.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        try {
            String sql = "INSERT INTO player VALUES "
                        +"(?, ?, ?, ?, ?, ?);";
            stmt = connUtil.createConnection().prepareStatement(sql);
            stmt.setInt(1, player.getId());
            stmt.setString(2, player.getUsername());
            stmt.setString(3, player.getPassword());
            stmt.setInt(4, player.getCurrentPoints());
            stmt.setInt(5, player.getCurrentLevel());
            stmt.setInt(6, player.getCasterType());

            System.out.println(Integer.toString(stmt.executeUpdate()) + " inserts made");

        } catch (SQLException e) {
            Log.warn("PlayerDaoPostgres.createPlayer threw SQLException: " + e);
            throw e;
        }
    }

    @Override
    public Player readPlayer(int playerId) throws SQLException{
        try {
            //Prep SQL for select statement
            String sql = "SELECT * FROM player "
                        + "WHERE player_id = ?;";

            stmt = connUtil.createConnection().prepareStatement(sql);
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
        //TODO Implement readAllPlayers
        return null;
    }

    @Override
    public Player updatePlayer (int playerId, Player player) throws SQLException{
        //TODO Implement updatePlayer
        return null;
    }

    @Override
    public void deletePlayer(Player player) throws SQLException{
        //TODO Implement deletePlayer
    }
}
