package pumpmyskycore.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;
import org.junit.jupiter.api.Test;

import fr.pumpmyskycore.Plot;
import fr.pumpmyskycore.exceptions.PlotIsNotEmptyException;
import fr.pumpmyskycore.exceptions.PlayerAlreadyHavePlotException;
import fr.pumpmyskycore.exceptions.PlayerDoesNotHavePlotException;
import pumpmyskycore.utils.FakePlayer;
import pumpmyskycore.utils.TestIslandManager;

public class IslandManagerPlayerBasicIslandActionTest {

	@Test
	public void playerIsOwner() throws IOException, InvalidConfigurationException, PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException {
		
		TestIslandManager manager = TestIslandManager.initManager(this.getClass());
		
		FakePlayer player1 = new FakePlayer(UUID.randomUUID());		
		FakePlayer player2 = new FakePlayer(UUID.randomUUID());
		
		Plot is1 = manager.playerCreateIsland(player1);

		assertTrue(manager.playerIsOwner(player1));
		assertFalse(manager.playerIsOwner(player2));
		
		
		assertTrue(manager.playerIsOwner(is1,player1));
		assertFalse(manager.playerIsOwner(is1,player2));
		
	}
	
	@Test
	public void playerCorrectlyCreatingIsland() throws PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException, IOException, InvalidConfigurationException {
		
		TestIslandManager manager = TestIslandManager.initManager(this.getClass());
		
		UUID uuid = UUID.randomUUID();
		
		Plot is = manager.playerCreateIsland(new FakePlayer(uuid));
		
		assertEquals(is.getOwner(), uuid.toString());
		
		assertTrue(manager.getIslandIndex().contains(uuid));
		assertEquals(manager.getIslandIndex().getIslandLocation(uuid).getX(), is.getIdX());		
		assertEquals(manager.getIslandIndex().getIslandLocation(uuid).getZ(), is.getIdZ());		
		
	}
	
	@Test
	public void playerCorrectlyLeavingsland() throws IOException, InvalidConfigurationException, PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException, PlotIsNotEmptyException {
		
		TestIslandManager manager = TestIslandManager.initManager(this.getClass());
		
		UUID uuid = UUID.randomUUID();
		FakePlayer player = new FakePlayer(uuid);
		
		manager.playerCreateIsland(player);
		
		assertTrue(manager.playerHasIsland(player));
		
		manager.playerLeaveIsland(player);
		
		assertFalse(manager.playerHasIsland(player));
		
	}
	
	@Test
	public void playerHasIslandReturnCorrectValue() throws IOException, InvalidConfigurationException, PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException {
		
		TestIslandManager manager = TestIslandManager.initManager(this.getClass());
		
		UUID uuid = UUID.randomUUID();
		FakePlayer player = new FakePlayer(uuid);
		
		assertFalse(manager.playerHasIsland(player));
		
		manager.playerCreateIsland(player);
		
		assertTrue(manager.playerHasIsland(player));		
		
	}
	
	@Test
	public void playerGetIslandReturnCorrectValue() throws IOException, InvalidConfigurationException, PlayerDoesNotHavePlotException, PlayerAlreadyHavePlotException {
		
		TestIslandManager manager = TestIslandManager.initManager(this.getClass());
		
		UUID uuid = UUID.randomUUID();
		FakePlayer player = new FakePlayer(uuid);
		
		Plot is = manager.playerCreateIsland(new FakePlayer(uuid));
		
		Plot gettedIsland = manager.playerGetIsland(player);
		
		assertEquals(is.getID(), gettedIsland.getID());
		
	}
	
}
