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
	
	public static IslandIndex init(Path indexPath) throws IOException, InvalidConfigurationException {
		
		File file = new File(indexPath + File.separator + IslandConstant.ISLAND_INDEX_FILE_NAME);			
		if(!file.exists()) {
			file.createNewFile();
		}
		
		FileConfiguration fileConf = new YamlConfiguration();
		fileConf.load(file);
		
		return new IslandIndex(file,fileConf);
	}

	private File file;
	private FileConfiguration fileConf;

	public IslandIndex(File f, FileConfiguration fc) throws IOException {
		
		this.file = f;
		this.fileConf = fc;
		
		this.init();
		
	}
	
	private void init() throws IOException {
		
		this.fileConf.createSection("islands");
		this.save();
		
	}

	public File getFile() {
		return file;
	}

	public FileConfiguration getYaml() {
		return this.fileConf;
	}

	public boolean contains(UUID minecraftUUID) {
		
		
	}
	
}
