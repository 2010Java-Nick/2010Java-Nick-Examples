package SpellPointTracker.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
//import org.apache.log4j.Logger;

import SpellPointTracker.pojos.Player;

@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceImplTest {

	// private static Logger Log = Logger.getLogger("playerServiceLog");

	private PlayerService playerService;
	private String username;
	private String password;
	private int level;
	private int casterType;
	private Player player;
	private List<Player> players;
	private List<Player> noPlayers;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		username = "daveTheGamer";
		password = "password1!";
		level = 2;
		casterType = 0;
		player = new Player(1, username, password, 20, level, casterType);

		players = new ArrayList<>();
		players.add(player);
		noPlayers = new ArrayList<>();

		playerService = new PlayerServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createPlayerTest() {
		assertTrue("createPlayer returned False", playerService.createPlayer(username, password, level, casterType));
		assertTrue("Player object was not properly added to collection",
				playerService.getPlayers().get(0).equals(player));

		playerService.setPlayers(noPlayers);
		assertFalse("createPlayer returned True for false data", playerService.createPlayer("", "", 0, 10));
		assertTrue("false Player was added to collection", playerService.getPlayers().size() == 0);
	}

	public void getPlayerTest() {
		playerService.setPlayers(players);
		assertTrue("Player was not retrieved", playerService.getPlayer(username, password).equals(player));

		playerService.setPlayers(noPlayers);
		Player empty = playerService.getPlayer(username, password);
		assertTrue("Object returned not null", empty.equals(null));
	}
}
