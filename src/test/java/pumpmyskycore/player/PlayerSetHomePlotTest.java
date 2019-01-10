package pumpmyskycore.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import fr.pumpmyplotcore.Plot;
import fr.pumpmyplotcore.PlotHomeLocation;
import fr.pumpmyplotcore.PlotManager.PlotManagerConstant;
import fr.pumpmyplotcore.exceptions.InvalidePlotHomeLocationException;
import fr.pumpmyplotcore.exceptions.PlayerAlreadyHavePlotException;
import fr.pumpmyplotcore.exceptions.PlayerAlreadyInvitedPlotException;
import fr.pumpmyplotcore.exceptions.PlayerDoesNotHavePlotException;
import fr.pumpmyplotcore.exceptions.PlayerDoesNotInvitedPlotException;
import fr.pumpmyplotcore.exceptions.RestrictActionToPlotOwnerException;
import pumpmyskycore.utils.FakePlayer;
import pumpmyskycore.utils.TestPlotManager;

public class PlayerSetHomePlotTest {

	@Test
	public void playerOwnerCorrectlySetHomePlot() throws IOException, InvalidConfigurationException, PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException, RestrictActionToPlotOwnerException, InvalidePlotHomeLocationException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		FakePlayer player = new FakePlayer(UUID.randomUUID());
		
		manager.playerCreatePlot(player);		
		manager.playerSetHomePlot(player, new PlotHomeLocation(10 + PlotManagerConstant.PLOT_SIZE, 10, 10 + PlotManagerConstant.PLOT_SIZE));
		
		Plot plot = manager.playerGetPlot(player);
				
		assertEquals(plot.getHomeX(), 10 + PlotManagerConstant.PLOT_SIZE);
		assertEquals(plot.getHomeY(), 10);
		assertEquals(plot.getHomeZ(), 10 + PlotManagerConstant.PLOT_SIZE);
		
	}
	
	@Test
	public void playerCantSetHomeIfNoPlot() throws IOException, InvalidConfigurationException, PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException, RestrictActionToPlotOwnerException, InvalidePlotHomeLocationException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		FakePlayer player = new FakePlayer(UUID.randomUUID());
	
		Executable exec = () -> manager.playerSetHomePlot(player, new PlotHomeLocation(10 + PlotManagerConstant.PLOT_SIZE, 10, 10 + PlotManagerConstant.PLOT_SIZE));
		
		assertThrows(PlayerDoesNotHavePlotException.class, exec);
		
	}
	
	@Test
	public void playerNoOwnerCantSetHomePlot() throws IOException, InvalidConfigurationException, PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException, RestrictActionToPlotOwnerException, InvalidePlotHomeLocationException, PlayerAlreadyInvitedPlotException, PlayerDoesNotInvitedPlotException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		FakePlayer player = new FakePlayer(UUID.randomUUID());
		FakePlayer player1 = new FakePlayer(UUID.randomUUID());
		
		manager.playerCreatePlot(player);
		manager.playerInvitePlot(player, player1);
		manager.playerAcceptInvitePlot(player1, player);
		
		Executable exec = () -> manager.playerSetHomePlot(player1, new PlotHomeLocation(10 + PlotManagerConstant.PLOT_SIZE, 10, 10 + PlotManagerConstant.PLOT_SIZE));
		
		assertThrows(RestrictActionToPlotOwnerException.class, exec);
		
	}
	
	@Test
	public void playerOwnerCantSetHomeOutPlot() throws IOException, InvalidConfigurationException, PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException, RestrictActionToPlotOwnerException, InvalidePlotHomeLocationException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		FakePlayer player = new FakePlayer(UUID.randomUUID());
		
		manager.playerCreatePlot(player);			
		
		Executable exec = () -> manager.playerSetHomePlot(player, new PlotHomeLocation(10, 10, 11));
		
		assertThrows(InvalidePlotHomeLocationException.class, exec);
		
		exec = () -> manager.playerSetHomePlot(player, new PlotHomeLocation(110 + PlotManagerConstant.PLOT_SIZE, 700, 110 + PlotManagerConstant.PLOT_SIZE));
		
		assertThrows(InvalidePlotHomeLocationException.class, exec);
		
	}
	
}
