package pumpmyskycore.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import fr.pumpmyplotcore.Plot;
import fr.pumpmyplotcore.PlotHome;
import fr.pumpmyplotcore.exceptions.PlayerAlreadyHavePlotException;
import fr.pumpmyplotcore.exceptions.PlayerDoesNotHavePlotException;
import pumpmyskycore.utils.FakePlayer;
import pumpmyskycore.utils.TestPlotManager;

public class PlayerGetHomePlotTest {

	@Test
	public void playerCorrectlyGettingHomePlot() throws IOException, InvalidConfigurationException, PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		UUID uuid = UUID.randomUUID();
		FakePlayer player = new FakePlayer(uuid);
		
		Plot plot = manager.playerCreatePlot(player);
		
		assertEquals(manager.playerGetHomePlot(player), new PlotHome(plot.getHomeX(), plot.getHomeY(), plot.getHomeZ()));		
		
	}
	
	@Test
	public void playerCantGettingHomeIfNotHavePlot() throws PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException, IOException, InvalidConfigurationException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		UUID uuid = UUID.randomUUID();
		FakePlayer player = new FakePlayer(uuid);		
		
		Executable exec = () -> manager.playerGetHomePlot(player);
		
		assertThrows(PlayerDoesNotHavePlotException.class, exec);
		
	}
	
	
	
}
