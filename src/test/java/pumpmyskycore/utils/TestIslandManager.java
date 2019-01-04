package pumpmyskycore.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;

import fr.pumpmyskycore.IslandManager;

public class TestIslandManager extends IslandManager<FakePlayer> {

	public TestIslandManager(Path configPath) throws IOException, InvalidConfigurationException {
		super(configPath);
	}

	@Override
	public UUID getMinecraftUUID(FakePlayer player) {
		
		return player.getUuid();
		
	}

}
