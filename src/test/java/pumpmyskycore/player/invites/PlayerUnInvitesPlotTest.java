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

public class PlayerUnInvitesPlotTest {

	@Test
	public void playerCantUninviteNoPlot() throws IOException, InvalidConfigurationException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		FakePlayer invitor = new FakePlayer(UUID.randomUUID());		
		FakePlayer target = new FakePlayer(UUID.randomUUID());
		
		Executable exec = () -> manager.playerUninvitePlot(invitor, target);
		
		assertThrows(PlayerDoesNotHavePlotException.class, exec);
		
	}
	
	@Test
	public void playerOwnerCanUninvitePlot() throws IOException, InvalidConfigurationException, PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException, RestrictActionToPlotOwnerException, PlayerAlreadyInvitedPlotException, PlayerDoesNotInvitedPlotException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		FakePlayer invitor = new FakePlayer(UUID.randomUUID());		
		FakePlayer target = new FakePlayer(UUID.randomUUID());
		
		Plot plot = manager.playerCreatePlot(invitor);
		
		assertFalse(manager.getPlotInvites().isInvites(target.getUuid(), plot));
		
		manager.playerInvitePlot(invitor, target);
		
		assertTrue(manager.getPlotInvites().isInvites(target.getUuid(), plot));
		
		manager.playerUninvitePlot(invitor, target);
		
		assertFalse(manager.getPlotInvites().isInvites(target.getUuid(), plot));
		
	}
	
	@Test
	public void playerOwnerCantUninviteIfNotInvitedPlot() throws IOException, InvalidConfigurationException, PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException, RestrictActionToPlotOwnerException, PlayerAlreadyInvitedPlotException, PlayerDoesNotInvitedPlotException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		FakePlayer invitor = new FakePlayer(UUID.randomUUID());		
		FakePlayer target = new FakePlayer(UUID.randomUUID());
		
		manager.playerCreatePlot(invitor);
		
		Executable exec = () -> manager.playerUninvitePlot(invitor, target);
		
		assertThrows(PlayerDoesNotInvitedPlotException.class, exec);
		
	}
	
	@Test
	public void playerNotOwnerCantUninvitePlot() throws IOException, InvalidConfigurationException, PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException, RestrictActionToPlotOwnerException, PlayerAlreadyInvitedPlotException, PlayerDoesNotInvitedPlotException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		FakePlayer invitor = new FakePlayer(UUID.randomUUID());		
		FakePlayer target = new FakePlayer(UUID.randomUUID());
		
		Plot plot = manager.playerCreatePlot(invitor);
		
		assertFalse(manager.getPlotInvites().isInvites(target.getUuid(), plot));
		
		manager.playerInvitePlot(invitor, target);
		
		assertTrue(manager.getPlotInvites().isInvites(target.getUuid(), plot));
		
		manager.playerUninvitePlot(invitor, target);
		
		assertFalse(manager.getPlotInvites().isInvites(target.getUuid(), plot));
		
	}
	
}
