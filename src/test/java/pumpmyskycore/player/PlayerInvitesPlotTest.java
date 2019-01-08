package pumpmyskycore.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;
import org.junit.jupiter.api.Test;

import fr.pumpmyplotcore.Plot;
import fr.pumpmyplotcore.exceptions.PlayerAlreadyHavePlotException;
import fr.pumpmyplotcore.exceptions.PlayerAlreadyInvited;
import fr.pumpmyplotcore.exceptions.PlayerDoesNotHavePlotException;
import fr.pumpmyplotcore.exceptions.PlayerDoesNotInvited;
import fr.pumpmyplotcore.exceptions.RestrictActionToPlotOwnerException;
import pumpmyskycore.utils.FakePlayer;
import pumpmyskycore.utils.TestPlotManager;

public class PlayerInvitesPlotTest {

	@Test
	public void playerOwnerCanInviteIsland() throws IOException, InvalidConfigurationException, PlayerDoesNotHavePlotException, RestrictActionToPlotOwnerException, PlayerAlreadyInvited, PlayerAlreadyHavePlotException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		FakePlayer invitor = new FakePlayer(UUID.randomUUID());		
		FakePlayer target = new FakePlayer(UUID.randomUUID());
		
		Plot island = manager.playerCreatePlot(invitor);
		
		assertFalse(manager.getPlotInvites().isInvites(target.getUuid(), island));
		
		manager.playerInvitePlot(invitor, target);
		
		assertTrue(manager.getPlotInvites().isInvites(target.getUuid(), island));
		
	}
	
	@Test
	public void playerOwnerCanUninviteIsland() throws IOException, InvalidConfigurationException, PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException, RestrictActionToPlotOwnerException, PlayerAlreadyInvited, PlayerDoesNotInvited {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		FakePlayer invitor = new FakePlayer(UUID.randomUUID());		
		FakePlayer target = new FakePlayer(UUID.randomUUID());
		
		Plot island = manager.playerCreatePlot(invitor);
		
		assertFalse(manager.getPlotInvites().isInvites(target.getUuid(), island));
		
		manager.playerInvitePlot(invitor, target);
		
		assertTrue(manager.getPlotInvites().isInvites(target.getUuid(), island));
		
		manager.playerUninvitePlot(invitor, target);
		
		assertFalse(manager.getPlotInvites().isInvites(target.getUuid(), island));
		
	}
	
	@Test
	public void playerAcceptInviteIsland() throws IOException, InvalidConfigurationException, PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException, RestrictActionToPlotOwnerException, PlayerAlreadyInvited, PlayerDoesNotInvited {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		FakePlayer invitor = new FakePlayer(UUID.randomUUID());		
		FakePlayer target = new FakePlayer(UUID.randomUUID());
		
		Plot plot = manager.playerCreatePlot(invitor);		
		manager.playerInvitePlot(invitor, target);
		
		manager.playerAcceptInvitePlot(target, invitor);
		
		Plot targetPlot = manager.playerGetPlot(target);
		
		assertEquals(plot, targetPlot);
		assertTrue(targetPlot.containesMember(manager.getMinecraftUUID(target)));
		
	}
	
	public void playerRefuseInviteIsland() {
		
	}
	
}
