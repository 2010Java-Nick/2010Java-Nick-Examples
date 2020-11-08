package SpellPointTracker.daos;

import java.sql.PreparedStatement;
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
        //TODO Implement readPlayer
        return null;
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
