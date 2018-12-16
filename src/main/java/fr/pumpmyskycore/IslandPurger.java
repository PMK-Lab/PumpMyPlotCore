package fr.pumpmyskycore;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.pumpmyskycore.IslandManager.IslandManagerConstant;

public class IslandPurger {

	public final static String ISLAND_STRING = "islands";
	
	public static IslandPurger init(Path indexPath) throws IOException, InvalidConfigurationException {
		
		File file = new File(indexPath + File.separator + IslandManagerConstant.ISLAND_PURGER_FILE_NAME);			
		if(!file.exists()) {
			file.createNewFile();
		}
		
		FileConfiguration fileConf = new YamlConfiguration();
		fileConf.load(file);
		
		return new IslandPurger(file,fileConf);
	}

	private File file;
	private FileConfiguration fileConf;

	private IslandPurger(File f, FileConfiguration fc) throws IOException {
		
		this.file = f;
		this.fileConf = fc;
		
		this.init();
		
	}
	
	private void init() throws IOException {
		
		this.fileConf.createSection("islands");
		this.save();
		
	}
	
	private void save() throws IOException {
		
		this.fileConf.save(this.file);
		
	}
	
	public boolean contains(Island island) {
		
		return this.fileConf.getStringList(ISLAND_STRING).contains(island.getID());
		
	}
	
	public void addIsland(Island island) throws IOException {
		
		List<String> list = this.fileConf.getStringList(ISLAND_STRING);
		
		list.add(island.getID());
		
		this.fileConf.set(ISLAND_STRING, list);
		
		this.save();		
		
	}	

	public File getFile() {
		return file;
	}

	public FileConfiguration getYaml() {
		return this.fileConf;
	}
	
}
