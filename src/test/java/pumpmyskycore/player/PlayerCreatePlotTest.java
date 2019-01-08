package pumpmyskycore.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import fr.pumpmyplotcore.Plot;
import fr.pumpmyplotcore.exceptions.PlayerAlreadyHavePlotException;
import fr.pumpmyplotcore.exceptions.PlayerDoesNotHavePlotException;
import pumpmyskycore.utils.FakePlayer;
import pumpmyskycore.utils.TestPlotManager;

public class PlayerCreatePlotTest {

	@Test
	public void playerCorrectlyCreatingPlot() throws PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException, IOException, InvalidConfigurationException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		UUID uuid = UUID.randomUUID();
		FakePlayer player = new FakePlayer(uuid);
		
		Plot plot = manager.playerCreatePlot(player);
		
		assertEquals(plot.getOwner(), uuid.toString());
		
		assertTrue(manager.getPlotIndex().contains(uuid));
		assertEquals(manager.getPlotIndex().getPlotLocation(uuid).getX(), plot.getIdX());		
		assertEquals(manager.getPlotIndex().getPlotLocation(uuid).getZ(), plot.getIdZ());		
		
	}
	
	@Test
	public void playerCantCreatePlotIfAlreadyHaveOne() throws PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException, IOException, InvalidConfigurationException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		UUID uuid = UUID.randomUUID();
		FakePlayer player = new FakePlayer(uuid);		
		
		Plot plot = manager.playerCreatePlot(player);
		
		Executable exec = () -> manager.playerCreatePlot(player);
		
		assertThrows(PlayerAlreadyHavePlotException.class, exec);
		
	}
	
	
	
}
