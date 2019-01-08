package pumpmyskycore.player;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import fr.pumpmyplotcore.exceptions.PlayerAlreadyHavePlotException;
import fr.pumpmyplotcore.exceptions.PlayerAlreadyInvitedPlotException;
import fr.pumpmyplotcore.exceptions.PlayerDoesNotHavePlotException;
import fr.pumpmyplotcore.exceptions.PlayerDoesNotInvitedPlotException;
import fr.pumpmyplotcore.exceptions.PlotIsNotEmptyException;
import fr.pumpmyplotcore.exceptions.RestrictActionToPlotOwnerException;
import pumpmyskycore.utils.FakePlayer;
import pumpmyskycore.utils.TestPlotManager;

public class PlayerLeavePlotTest {

	@Test
	public void playerCorrectlyLeavingPlot() throws PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException, IOException, InvalidConfigurationException, PlotIsNotEmptyException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		UUID uuid = UUID.randomUUID();
		FakePlayer player = new FakePlayer(uuid);
		
		manager.playerCreatePlot(player);
		
		assertTrue(manager.playerHasPlot(player));
		
		boolean b = manager.playerLeavePlot(player);
		
		assertFalse(manager.playerHasPlot(player));
		assertTrue(b);
		
	}
	
	@Test
	public void playerCantLeavePlotIfDoesNotHaveOne() throws IOException, InvalidConfigurationException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		UUID uuid = UUID.randomUUID();
		FakePlayer player = new FakePlayer(uuid);
		
		Executable exec = () -> manager.playerLeavePlot(player);
		
		assertThrows(PlayerDoesNotHavePlotException.class, exec);
		
	}	
	
	@Test
	public void playerCantLeavePlotIfMemberNotEmpty() throws IOException, InvalidConfigurationException, PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException, RestrictActionToPlotOwnerException, PlayerAlreadyInvitedPlotException, PlayerDoesNotInvitedPlotException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		UUID uuid = UUID.randomUUID();
		FakePlayer player = new FakePlayer(uuid);
		
		UUID uuid1 = UUID.randomUUID();
		FakePlayer player1 = new FakePlayer(uuid1);
		
		manager.playerCreatePlot(player);
		
		manager.playerInvitePlot(player, player1);
		manager.playerAcceptInvitePlot(player1, player);
		
		Executable exec = () -> manager.playerLeavePlot(player);
		
		assertThrows(PlotIsNotEmptyException.class, exec);
		
	}
	
}
