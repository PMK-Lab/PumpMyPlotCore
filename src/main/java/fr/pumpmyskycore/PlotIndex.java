package fr.pumpmyskycore;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import fr.pumpmyskycore.PlotManager.PlotManagerConstant;

public class PlotIndex {
	
	public final static String ISLAND_STRING = "islands.";
	
	public static PlotIndex init(Path indexPath) throws IOException, InvalidConfigurationException {
		
		File file = new File(indexPath + File.separator + PlotManagerConstant.ISLAND_INDEX_FILE_NAME);			
		if(!file.exists()) {
			file.createNewFile();
		}
		
		FileConfiguration fileConf = new YamlConfiguration();
		fileConf.load(file);
		
		return new PlotIndex(file,fileConf);
	}

	private File file;
	private FileConfiguration fileConf;

	private PlotIndex(File f, FileConfiguration fc) throws IOException {
		
		this.file = f;
		this.fileConf = fc;
		
		this.init();
		
	}
	
	public PlotLocation createFirstFreeLocFile(Path islandPath) throws IOException {
		
		for (int x = 1; x <= PlotManagerConstant.ISLAND_SIDE_NUM ; x++) {
			
			for (int z = 1; z <= PlotManagerConstant.ISLAND_SIDE_NUM ; z++) {
				
				PlotLocation loc = new PlotLocation(x,z);
				File f = new File(islandPath + File.separator + loc.toPath());
				
				if(f.exists()) {
					continue;
				}
				
				
				f.createNewFile();
				return loc;
				
			}
			
		}
		
		System.out.println("NOT ENOUGH ISLANDS !!!!!!!!!!!!§");
		return null;
		
	}
	
	private void init() throws IOException {
		
		this.fileConf.createSection("islands");
		this.save();
		
	}
	
	private void save() throws IOException {
		
		this.fileConf.save(this.file);
		
	}

	public File getFile() {
		return file;
	}

	public FileConfiguration getYaml() {
		return this.fileConf;
	}
	
	public void setIslandLocation(UUID minecraftUUID, PlotLocation loc) throws IOException {
		
		this.fileConf.set(ISLAND_STRING + minecraftUUID + PlotLocation.X_STRING, loc.getX());
		this.fileConf.set(ISLAND_STRING + minecraftUUID + PlotLocation.Z_STRING, loc.getZ());
		
		this.save();
		
	}
	
	public void unsetIslandLocation(UUID minecraftUUID) throws IOException {
		
		this.fileConf.set(ISLAND_STRING + minecraftUUID + PlotLocation.X_STRING, null);
		this.fileConf.set(ISLAND_STRING + minecraftUUID + PlotLocation.Z_STRING, null);
		this.fileConf.set(ISLAND_STRING + minecraftUUID, null);		
		
		this.save();
		
	}
	
	public PlotLocation getIslandLocation(UUID minecraftUUID) {
		
		int x = this.fileConf.getInt(ISLAND_STRING + minecraftUUID + PlotLocation.X_STRING);
		int y = this.fileConf.getInt(ISLAND_STRING + minecraftUUID + PlotLocation.Z_STRING);
		
		return new PlotLocation(x,y);
		
	}

	public boolean contains(UUID minecraftUUID) {
		
		return this.fileConf.contains(ISLAND_STRING + minecraftUUID.toString());
		
	}
	
}
