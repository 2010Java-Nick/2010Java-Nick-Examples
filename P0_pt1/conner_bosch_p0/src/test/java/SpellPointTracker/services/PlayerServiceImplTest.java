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
import org.mockito.junit.MockitoJUnitRunner;
import SpellPointTracker.pojos.Player;

@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceImplTest {

	private PlayerServiceImpl playerService;
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
		player = new Player(0, username, password, 0, level, casterType);

		players = new ArrayList<>();
		players.add(player);
		noPlayers = new ArrayList<>();

		playerService = new PlayerServiceImpl();
		playerService.setPlayers(noPlayers);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createPlayerTest() {
		assertTrue("createPlayer returned False", playerService.createPlayer(username, password, 0, level, casterType));
		assertTrue("Player object was not properly added to collection", playerService.getPlayers().get(0).getUsername().equals(player.getUsername()));
	}

	@Test
	public void getPlayerTest() {
		playerService.setPlayers(players);
		assertTrue("Player was not retrieved", playerService.getPlayer(username, password).equals(player));

		playerService.setPlayers(noPlayers);
		Player empty = playerService.getPlayer(username, password);
		assertTrue("Object returned not null", empty == null);
	}
}
