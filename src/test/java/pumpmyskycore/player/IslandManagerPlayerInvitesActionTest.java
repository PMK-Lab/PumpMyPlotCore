package pumpmyskycore.player;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;
import org.junit.jupiter.api.Test;

import fr.pumpmyskycore.Plot;
import fr.pumpmyskycore.exceptions.PlayerAlreadyHavePlotException;
import fr.pumpmyskycore.exceptions.PlayerAlreadyInvited;
import fr.pumpmyskycore.exceptions.PlayerDoesNotHavePlotException;
import fr.pumpmyskycore.exceptions.PlayerDoesNotInvited;
import fr.pumpmyskycore.exceptions.RestrictActionToPlotOwnerException;
import pumpmyskycore.utils.FakePlayer;
import pumpmyskycore.utils.TestIslandManager;

public class IslandManagerPlayerInvitesActionTest {

	@Test
	public void playerInviteIsland() throws IOException, InvalidConfigurationException, PlayerDoesNotHavePlotException, RestrictActionToPlotOwnerException, PlayerAlreadyInvited, PlayerAlreadyHavePlotException {
		
		TestIslandManager manager = TestIslandManager.initManager(this.getClass());
		
		FakePlayer invitor = new FakePlayer(UUID.randomUUID());		
		FakePlayer target = new FakePlayer(UUID.randomUUID());
		
		Plot island = manager.playerCreateIsland(invitor);
		
		assertFalse(manager.getIslandInvites().isInvites(target.getUuid(), island));
		
		manager.playerInviteIsland(invitor, target);
		
		assertTrue(manager.getIslandInvites().isInvites(target.getUuid(), island));
		
	}
	
	@Test
	public void playerUninviteIsland() throws IOException, InvalidConfigurationException, PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException, RestrictActionToPlotOwnerException, PlayerAlreadyInvited, PlayerDoesNotInvited {
		
		TestIslandManager manager = TestIslandManager.initManager(this.getClass());
		
		FakePlayer invitor = new FakePlayer(UUID.randomUUID());		
		FakePlayer target = new FakePlayer(UUID.randomUUID());
		
		Plot island = manager.playerCreateIsland(invitor);
		
		assertFalse(manager.getIslandInvites().isInvites(target.getUuid(), island));
		
		manager.playerInviteIsland(invitor, target);
		
		assertTrue(manager.getIslandInvites().isInvites(target.getUuid(), island));
		
		manager.playerUninviteIsland(invitor, target);
		
		assertFalse(manager.getIslandInvites().isInvites(target.getUuid(), island));
		
	}
	
	public void playerAcceptInviteIsland() {
		
		
		
	}
	
	public void playerRefuseInviteIsland() {
		
	}
	
}
