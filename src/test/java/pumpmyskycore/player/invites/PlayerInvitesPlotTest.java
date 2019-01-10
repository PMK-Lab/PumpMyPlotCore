package pumpmyskycore.player.invites;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import fr.pumpmyplotcore.Plot;
import fr.pumpmyplotcore.exceptions.PlayerAlreadyHavePlotException;
import fr.pumpmyplotcore.exceptions.PlayerAlreadyInvitedPlotException;
import fr.pumpmyplotcore.exceptions.PlayerDoesNotHavePlotException;
import fr.pumpmyplotcore.exceptions.PlayerDoesNotInvitedPlotException;
import fr.pumpmyplotcore.exceptions.RestrictActionToPlotOwnerException;
import pumpmyskycore.utils.FakePlayer;
import pumpmyskycore.utils.TestPlotManager;

public class PlayerInvitesPlotTest {

	@Test
	public void playerCantInviteNoPlot() throws IOException, InvalidConfigurationException, PlayerDoesNotHavePlotException, RestrictActionToPlotOwnerException, PlayerAlreadyInvitedPlotException, PlayerAlreadyHavePlotException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		FakePlayer invitor = new FakePlayer(UUID.randomUUID());		
		FakePlayer target = new FakePlayer(UUID.randomUUID());
		
		Executable exec = () -> manager.playerInvitePlot(invitor, target);
		
		assertThrows(PlayerDoesNotHavePlotException.class, exec);
		
	}
	
	@Test
	public void playerOwnerCanInvitePlot() throws IOException, InvalidConfigurationException, PlayerDoesNotHavePlotException, RestrictActionToPlotOwnerException, PlayerAlreadyInvitedPlotException, PlayerAlreadyHavePlotException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		FakePlayer invitor = new FakePlayer(UUID.randomUUID());		
		FakePlayer target = new FakePlayer(UUID.randomUUID());
		
		Plot plot = manager.playerCreatePlot(invitor);
		
		assertFalse(manager.getPlotInvites().isInvites(plot,target.getUuid()));
		
		manager.playerInvitePlot(invitor, target);
		
		assertTrue(manager.getPlotInvites().isInvites(plot,target.getUuid()));
		
	}
	
	@Test
	public void playerOwnerCantInviteAgainPlot() throws IOException, InvalidConfigurationException, PlayerDoesNotHavePlotException, RestrictActionToPlotOwnerException, PlayerAlreadyInvitedPlotException, PlayerAlreadyHavePlotException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		FakePlayer invitor = new FakePlayer(UUID.randomUUID());		
		FakePlayer target = new FakePlayer(UUID.randomUUID());
		
		manager.playerCreatePlot(invitor);		
		manager.playerInvitePlot(invitor, target);
		
		Executable exec = () -> manager.playerInvitePlot(invitor, target);
		
		assertThrows(PlayerAlreadyInvitedPlotException.class, exec);
		
	}
	
	@Test
	public void playerNotOwnerCantInvitePlot() throws IOException, InvalidConfigurationException, PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException, RestrictActionToPlotOwnerException, PlayerAlreadyInvitedPlotException, PlayerDoesNotInvitedPlotException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		UUID uuid = UUID.randomUUID();
		FakePlayer player = new FakePlayer(uuid);
		
		UUID uuid1 = UUID.randomUUID();
		FakePlayer player1 = new FakePlayer(uuid1);
		
		UUID uuid2 = UUID.randomUUID();
		FakePlayer player2 = new FakePlayer(uuid2);
		
		manager.playerCreatePlot(player);
		
		manager.playerInvitePlot(player, player1);
		manager.playerAcceptInvitePlot(player1, player);
		
		Executable exec = () -> manager.playerInvitePlot(player1, player2);
		
		assertThrows(RestrictActionToPlotOwnerException.class, exec);
		
	}
	
}
