package pumpmyskycore.player;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;
import fr.pumpmyskycore.Plot;
import fr.pumpmyskycore.exceptions.PlotIsNotEmptyException;
import fr.pumpmyskycore.exceptions.PlayerAlreadyHavePlotException;
import fr.pumpmyskycore.exceptions.PlayerDoesNotHavePlotException;
import pumpmyskycore.utils.FakePlayer;
import pumpmyskycore.utils.TestPlotManager;

public class PlotManagerPlayerBasicIslandActionTest {

	@Test
	public void playerIsOwner() throws IOException, InvalidConfigurationException, PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		FakePlayer player1 = new FakePlayer(UUID.randomUUID());		
		FakePlayer player2 = new FakePlayer(UUID.randomUUID());
		
		Plot is1 = manager.playerCreatePlot(player1);

		assertTrue(manager.playerIsOwner(player1));
		assertFalse(manager.playerIsOwner(player2));
		
		
		assertTrue(manager.playerIsOwner(is1,player1));
		assertFalse(manager.playerIsOwner(is1,player2));
		
	}
	
	@Test
	public void playerCorrectlyCreatingIsland() throws PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException, IOException, InvalidConfigurationException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		UUID uuid = UUID.randomUUID();
		
		Plot is = manager.playerCreatePlot(new FakePlayer(uuid));
		
		assertEquals(is.getOwner(), uuid.toString());
		
		assertTrue(manager.getPlotIndex().contains(uuid));
		assertEquals(manager.getPlotIndex().getPlotLocation(uuid).getX(), is.getIdX());		
		assertEquals(manager.getPlotIndex().getPlotLocation(uuid).getZ(), is.getIdZ());		
		
	}
	
	@Test
	public void playerCorrectlyLeavingsland() throws IOException, InvalidConfigurationException, PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException, PlotIsNotEmptyException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		UUID uuid = UUID.randomUUID();
		FakePlayer player = new FakePlayer(uuid);
		
		manager.playerCreatePlot(player);
		
		assertTrue(manager.playerHasPlot(player));
		
		manager.playerLeavePlot(player);
		
		assertFalse(manager.playerHasPlot(player));
		
	}
	
	@Test
	public void playerHasIslandReturnCorrectValue() throws IOException, InvalidConfigurationException, PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		UUID uuid = UUID.randomUUID();
		FakePlayer player = new FakePlayer(uuid);
		
		assertFalse(manager.playerHasPlot(player));
		
		manager.playerCreatePlot(player);
		
		assertTrue(manager.playerHasPlot(player));		
		
	}
	
	@Test
	public void playerGetIslandReturnCorrectValue() throws IOException, InvalidConfigurationException, PlayerDoesNotHavePlotException, PlayerAlreadyHavePlotException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		UUID uuid = UUID.randomUUID();
		FakePlayer player = new FakePlayer(uuid);
		
		Plot is = manager.playerCreatePlot(new FakePlayer(uuid));
		
		Plot gettedIsland = manager.playerGetPlot(player);
		
		assertEquals(is.getID(), gettedIsland.getID());
		
	}
	
}
