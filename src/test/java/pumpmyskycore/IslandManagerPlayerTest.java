package pumpmyskycore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;
import org.junit.jupiter.api.Test;

import fr.pumpmyskycore.Island;
import fr.pumpmyskycore.exceptions.IslandIsNotEmptyException;
import fr.pumpmyskycore.exceptions.PlayerAlreadyHaveIslandException;
import fr.pumpmyskycore.exceptions.PlayerDoesNotHaveIslandException;
import pumpmyskycore.utils.FakePlayer;
import pumpmyskycore.utils.TestIslandManager;

public class IslandManagerPlayerTest {

	//@Test
	public void playerCorrectlyCreatingIsland() throws PlayerAlreadyHaveIslandException, PlayerDoesNotHaveIslandException, IOException, InvalidConfigurationException {
		
		TestIslandManager manager = TestIslandManager.initManager(getClass());
		
		UUID uuid = UUID.randomUUID();
		
		Island is = manager.playerCreateIsland(new FakePlayer(uuid));
		
		Executable closureContainingCodeToTest = () -> manager.playerCreateIsland(new FakePlayer(uuid));
		
		assertThrows(PlayerAlreadyHaveIslandException.class, closureContainingCodeToTest);
		
	}
	
}
