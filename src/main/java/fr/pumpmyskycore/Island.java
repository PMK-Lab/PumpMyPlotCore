package fr.pumpmyskycore;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.pumpmyskycore.IslandManager.IslandManagerConstant;

public class Island {
	
	public static Island create(Path path,IslandLocation freeLoc, UUID uniqueId) {
		
		File file = new File(path + File.separator + freeLoc.toPath());
		FileConfiguration fileConf = YamlConfiguration.loadConfiguration(file);
		
		fileConf.createSection("island");
		
		fileConf.set("island.owner",uniqueId.toString());
		
		fileConf.createSection("island.spawn");
		
		fileConf.set("island.x", freeLoc.getX());
		fileConf.set("island.z", freeLoc.getY());
		
		fileConf.set("island.spawn.x", (Integer.parseInt(freeLoc.getX()) * IslandManagerConstant.ISLAND_SIZE) + (IslandManagerConstant.ISLAND_SIZE) + 0.5);
		fileConf.set("island.spawn.y", 60);
		fileConf.set("island.spawn.z", (Integer.parseInt(freeLoc.getY()) * IslandManagerConstant.ISLAND_SIZE) + (IslandManagerConstant.ISLAND_SIZE) + 0.5);
		
		fileConf.set("island.players", new ArrayList<String>());
		
		
		Island island = new Island(file);
		island.load();
		
		return island;
		
	}
	
	private void load(File f) {
		
		
		
		
	}
	

	public void add(UUID uniqueId) {
		// TODO Auto-generated method stub
		
	}
	
	public void remove(UUID uniqueId) {
		// TODO Auto-generated method stub
		
	}
	
	public Island() {
		
		
		
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}



}
