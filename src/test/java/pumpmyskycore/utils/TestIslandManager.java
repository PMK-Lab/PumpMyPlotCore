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
	
	public static TestIslandManager initManager(Class<?> c) throws IOException, InvalidConfigurationException {
	
		File file = new File(c.getSimpleName() + "_" + System.currentTimeMillis());
		
		file.mkdir();
			
		return new TestIslandManager(file);	
		
	}

	public File getFile() {
		return file;
	}

}
