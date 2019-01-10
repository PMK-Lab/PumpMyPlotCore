package pumpmyskycore.player.invites;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import fr.pumpmyplotcore.exceptions.PlotIsNotEmptyException;
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
	
	@Test
	public void acceptInvitesTest() throws IOException, InvalidConfigurationException, PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException, RestrictActionToPlotOwnerException, PlayerAlreadyInvitedPlotException, PlayerDoesNotInvitedPlotException, PlotIsNotEmptyException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		FakePlayer player1 = new FakePlayer(UUID.randomUUID());		
		FakePlayer player2 = new FakePlayer(UUID.randomUUID());
		FakePlayer player3 = new FakePlayer(UUID.randomUUID());
		
		manager.playerCreatePlot(player1);
		manager.playerInvitePlot(player1, player2);
		manager.playerAcceptInvitePlot(player2, player1);

		assertTrue(manager.playerHasPlot(player2));
		
		manager.playerCreatePlot(player3);
		manager.playerInvitePlot(player3, player2);
		
		assertTrue(manager.getPlotInvites().isInvites(manager.playerGetPlot(player3), player2.getUuid()));
		
		
		Executable exec = () -> manager.playerAcceptInvitePlot(player2, player3);
		
		assertThrows(PlayerAlreadyHavePlotException.class, exec);
		
		manager.playerLeavePlot(player2);
		
		assertFalse(manager.playerHasPlot(player2));		
		
		manager.playerAcceptInvitePlot(player2, player3);
		
		assertTrue(manager.playerHasPlot(player2));		
		
	}
	
	
	
}
