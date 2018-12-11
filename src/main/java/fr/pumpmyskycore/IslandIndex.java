package fr.pumpmyskycore;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import fr.pumpmyskycore.IslandManager.IslandConstant;

public class IslandIndex {
	
	public static IslandIndex init(Path indexPath) throws IOException {
		
		File file = new File(indexPath + File.separator + IslandConstant.ISLAND_INDEX_FILE_NAME);			
		if(!file.exists()) {
			file.createNewFile();
		}
		
		return new IslandIndex(indexPath);
	}
	
	private IslandIndex(Path indexPath) {
		
		
		
	}
	
}
