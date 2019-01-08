package pumpmyskycore.player.invites;

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
import fr.pumpmyplotcore.exceptions.PlayerAlreadyInvitedPlotException;
import fr.pumpmyplotcore.exceptions.PlayerDoesNotHavePlotException;
import fr.pumpmyplotcore.exceptions.PlayerDoesNotInvitedPlotException;
import fr.pumpmyplotcore.exceptions.RestrictActionToPlotOwnerException;
import pumpmyskycore.utils.FakePlayer;
import pumpmyskycore.utils.TestPlotManager;

public class PlayerAcceptInvitesPlotTest {

	@Test
	public void playerAcceptInvitePlot() throws IOException, InvalidConfigurationException, PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException, RestrictActionToPlotOwnerException, PlayerAlreadyInvitedPlotException, PlayerDoesNotInvitedPlotException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		FakePlayer invitor = new FakePlayer(UUID.randomUUID());		
		FakePlayer target = new FakePlayer(UUID.randomUUID());
		
		Plot plot = manager.playerCreatePlot(invitor);		
		manager.playerInvitePlot(invitor, target);
		
		manager.playerAcceptInvitePlot(target, invitor);
		
		Plot targetPlot = manager.playerGetPlot(target);
		
		assertEquals(plot, targetPlot);
		assertTrue(targetPlot.containesMember(manager.getMinecraftUUID(target)));
		assertEquals(manager.getPlotIndex().getPlotLocation(manager.getMinecraftUUID(target)), targetPlot.toLocation());
		
	}
	
	@Test
	public void playerCantAcceptInviteFromInvalideInviterPlot() throws IOException, InvalidConfigurationException, PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException, RestrictActionToPlotOwnerException, PlayerAlreadyInvitedPlotException, PlayerDoesNotInvitedPlotException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		FakePlayer invitor = new FakePlayer(UUID.randomUUID());		
		FakePlayer target = new FakePlayer(UUID.randomUUID());
		
		Executable exec = () -> manager.playerAcceptInvitePlot(target, invitor);
		
		assertThrows(PlayerDoesNotHavePlotException.class, exec);
		
	}
	
	
	@Test
	public void playerCantAcceptInviteIfNotInvitedPlot() throws IOException, InvalidConfigurationException, PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException, PlayerDoesNotInvitedPlotException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		FakePlayer invitor = new FakePlayer(UUID.randomUUID());		
		FakePlayer target = new FakePlayer(UUID.randomUUID());
		
		manager.playerCreatePlot(invitor);
		
		Executable exec = () -> manager.playerAcceptInvitePlot(target, invitor);
	
		assertThrows(PlayerDoesNotInvitedPlotException.class, exec);
		
	}
	
	@Test
	public void playerCantAcceptInviteIfHavePlot() throws IOException, InvalidConfigurationException, PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException, RestrictActionToPlotOwnerException, PlayerAlreadyInvitedPlotException, PlayerDoesNotInvitedPlotException {		
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		FakePlayer invitor = new FakePlayer(UUID.randomUUID());		
		FakePlayer target = new FakePlayer(UUID.randomUUID());
		
		manager.playerCreatePlot(invitor);		
		manager.playerInvitePlot(invitor, target);
		
		manager.playerAcceptInvitePlot(target, invitor);
		
		Executable exec = () -> manager.playerAcceptInvitePlot(target, invitor);
		
		assertThrows(PlayerAlreadyHavePlotException.class, exec);
		
	}
	
	
	
}
