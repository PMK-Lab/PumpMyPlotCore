package pumpmyskycore.utils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;

import fr.pumpmyskycore.IslandManager;

public class IslandTestManager extends IslandManager<FakePlayer> {

	public IslandTestManager(Path configPath) throws IOException, InvalidConfigurationException {
		super(configPath);
	}

	@Override
	public UUID getMinecraftUUID(FakePlayer player) {
		
		return player.getUuid();
		
	}

}
