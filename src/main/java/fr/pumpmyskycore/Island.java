package fr.pumpmyskycore;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.pumpmyskycore.IslandManager.IslandManagerConstant;

public class Island {
	
	public static Island create(Path path,IslandLocation freeLoc, UUID uniqueId) throws IOException {
		
		File file = new File(path + File.separator + freeLoc.toPath());
		FileConfiguration fileConf = YamlConfiguration.loadConfiguration(file);
		
		fileConf.set("island.owner",uniqueId.toString());
		
		fileConf.set("island.name","mon ile" + freeLoc.getX() + "|" + freeLoc.getY());
		
		fileConf.set("island.id.x", freeLoc.getX());
		fileConf.set("island.id.z", freeLoc.getY());
		
		fileConf.set("island.home.x", (freeLoc.getX() * IslandManagerConstant.ISLAND_SIZE) - (IslandManagerConstant.ISLAND_SIZE/2) + 0.5);
		fileConf.set("island.home.y", 60);
		fileConf.set("island.home.z", (freeLoc.getY() * IslandManagerConstant.ISLAND_SIZE) - (IslandManagerConstant.ISLAND_SIZE/2) + 0.5);
		
		fileConf.set("island.members", new ArrayList<String>());
		
		fileConf.save(file);
		
		Island island = new Island(file);
		island.load();
		
		return island;
		
	}
	
	public static Island get(Path path,IslandLocation islandLocation) {
		
		File file = new File(path + File.separator + islandLocation.toPath());
		
		Island island = new Island(file);
		island.load();
		
		return island;
		
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private File file;
	private YamlConfiguration fileYaml;
	
	private String owner;
	
	private String name;
	
	private double homeX;
	private double homeY;
	private double homeZ;
	
	private int idX;
	private int idY;
	
	private List<String> membersList;
	
	public Island(File f) {
		
		this.file = f;
		
	}
	
	private void load() {
		
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(this.file);
			
		this.owner = yaml.getString("island.owner");
		
		this.name = yaml.getString("island.name");
				
		this.homeX = yaml.getDouble("island.home.x");
		this.homeY = yaml.getDouble("island.home.y");
		this.homeZ = yaml.getDouble("island.home.z");
		
		this.idX = yaml.getInt("island.id.x");
		this.idY = yaml.getInt("island.id.y");
		
		this.membersList = yaml.getStringList("island.members");
		
		this.fileYaml = yaml;
		
	}

	public void addMember(UUID uniqueId) {
		this.membersList.add(uniqueId.toString());		
	}
	
	public void removeMember(UUID uniqueId) {
		
		for (Iterator<String> string = membersList.iterator(); string.hasNext();) {
			
			if(string.next().equals(uniqueId.toString())) {
				string.remove();
			}
			
		}
		
	}
	
	public void purge() {
		
		
		
	}

	public void save() throws IOException {
		
		this.fileYaml.set("island.name", this.name);
		
		this.fileYaml.set("island.home.x", this.homeX);
		this.fileYaml.set("island.home.y", this.homeY);
		this.fileYaml.set("island.home.z", this.homeZ);
		
		this.fileYaml.set("island.members", this.membersList);
		
		this.fileYaml.save(this.file);
		
	}
	
	public String getID() {
		return this.idX + "_" + this.idY;
	}

	public File getFile() {
		return this.file;
	}

	public YamlConfiguration getFileConf() {
		return this.fileYaml;
	}

	public String getOwner() {
		return this.owner;
	}

	public double getHomeX() {
		return this.homeX;
	}

	public double getHomeY() {
		return this.homeY;
	}

	public double getHomeZ() {
		return this.homeZ;
	}

	public int getIdX() {
		return this.idX;
	}

	public int getIdY() {
		return this.idY;
	}

	public List<String> getMembersList() {
		return this.membersList;
	}
	
	public String getName() {
		return this.name;
	}

}
