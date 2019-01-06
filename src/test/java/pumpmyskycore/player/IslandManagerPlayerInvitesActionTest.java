package pumpmyskycore.player;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;
import org.junit.jupiter.api.Test;

import fr.pumpmyskycore.Island;
import fr.pumpmyskycore.exceptions.PlayerAlreadyHaveIslandException;
import fr.pumpmyskycore.exceptions.PlayerAlreadyInvited;
import fr.pumpmyskycore.exceptions.PlayerDoesNotHaveIslandException;
import fr.pumpmyskycore.exceptions.PlayerDoesNotInvited;
import fr.pumpmyskycore.exceptions.RestrictActionToOwnerIslandException;
import pumpmyskycore.utils.FakePlayer;
import pumpmyskycore.utils.TestIslandManager;

public class IslandManagerPlayerInvitesActionTest {

	@Test
	public void playerInviteIsland() throws IOException, InvalidConfigurationException, PlayerDoesNotHaveIslandException, RestrictActionToOwnerIslandException, PlayerAlreadyInvited, PlayerAlreadyHaveIslandException {
		
		TestIslandManager manager = TestIslandManager.initManager(this.getClass());
		
		FakePlayer invitor = new FakePlayer(UUID.randomUUID());		
		FakePlayer target = new FakePlayer(UUID.randomUUID());
		
		Island island = manager.playerCreateIsland(invitor);
		
		assertFalse(manager.getIslandInvites().isInvites(target.getUuid(), island));
		
		manager.playerInviteIsland(invitor, target);
		
		assertTrue(manager.getIslandInvites().isInvites(target.getUuid(), island));
		
	}
	
	@Test
	public void playerUninviteIsland() throws IOException, InvalidConfigurationException, PlayerAlreadyHaveIslandException, PlayerDoesNotHaveIslandException, RestrictActionToOwnerIslandException, PlayerAlreadyInvited, PlayerDoesNotInvited {
		
		TestIslandManager manager = TestIslandManager.initManager(this.getClass());
		
		FakePlayer invitor = new FakePlayer(UUID.randomUUID());		
		FakePlayer target = new FakePlayer(UUID.randomUUID());
		
		Island island = manager.playerCreateIsland(invitor);
		
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
