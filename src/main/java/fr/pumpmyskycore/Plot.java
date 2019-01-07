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

import fr.pumpmyskycore.PlotManager.PlotManagerConstant;
import fr.pumpmyskycore.exceptions.PlotLocationParsingException;

public class Plot {
	
	public static Plot create(Path path,PlotLocation freeLoc, UUID uniqueId) throws IOException {
		
		File file = new File(path + File.separator + freeLoc.toPath());
		FileConfiguration fileConf = YamlConfiguration.loadConfiguration(file);
		
		fileConf.set("island.owner",uniqueId.toString());
		
		fileConf.set("island.name","mon ile" + freeLoc.getX() + "|" + freeLoc.getZ());
		
		fileConf.set("island.id.x", freeLoc.getX());
		fileConf.set("island.id.z", freeLoc.getZ());
		
		fileConf.set("island.home.x", (freeLoc.getX() * PlotManagerConstant.ISLAND_SIZE) - (PlotManagerConstant.ISLAND_SIZE/2) + 0.5);
		fileConf.set("island.home.y", 60);
		fileConf.set("island.home.z", (freeLoc.getZ() * PlotManagerConstant.ISLAND_SIZE) - (PlotManagerConstant.ISLAND_SIZE/2) + 0.5);
		
		fileConf.set("island.members", new ArrayList<String>());
		
		fileConf.save(file);
		
		Plot island = new Plot(file);
		island.load();
		
		return island;
		
	}
	
	public static Plot get(Path path,PlotLocation islandLocation) {
		
		File file = new File(path + File.separator + islandLocation.toPath());
		
		Plot island = new Plot(file);
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
	private int idZ;
	
	private List<String> membersList;
	
	public Plot(File f) {
		
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
		this.idZ = yaml.getInt("island.id.z");
		
		this.membersList = yaml.getStringList("island.members");
		
		this.fileYaml = yaml;
		
	}

	public void addMember(UUID uniqueId) {
		this.membersList.add(uniqueId.toString());		
	}
	
	public boolean containesMember(UUID uniqueID) {
		
		return this.getMembersList().contains(uniqueID.toString());
		
	}
	
	public void removeMember(UUID uniqueId) {
		
		for (Iterator<String> string = membersList.iterator(); string.hasNext();) {
			
			if(string.next().equals(uniqueId.toString())) {
				string.remove();
			}
			
		}
		
	}
	
	public void purge() throws IOException {
		
		this.fileYaml.set("island.owner", "remover");
		this.fileYaml.save(this.file);
		
	}

	public void save() throws IOException {
		
		this.fileYaml.set("island.name", this.name);
		
		this.fileYaml.set("island.home.x", this.homeX);
		this.fileYaml.set("island.home.y", this.homeY);
		this.fileYaml.set("island.home.z", this.homeZ);
		
		this.fileYaml.set("island.members", this.membersList);
		
		this.fileYaml.save(this.file);
		
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof Plot & this.getID().equals(((Plot) obj).getID())) {
			
			return true;
			
		}else {
			
			return false;
			
		}
		
	}
	
	public String getID() {
		return this.idX + "_" + this.idZ;
	}
	
	public PlotLocation toLocation() {
		
		try {
			return PlotLocation.parseFromString(this.getID());
		} catch (PlotLocationParsingException e) {
			e.printStackTrace();
			return null;
		}
		
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

	public int getIdZ() {
		return this.idZ;
	}

	public List<String> getMembersList() {
		return this.membersList;
	}
	
	public String getName() {
		return this.name;
	}

	public void setHome(PlotHome loc) {
		
		if((loc.getX() > ((this.idX + 1) * PlotManagerConstant.ISLAND_SIZE) && loc.getX() < (this.idX * PlotManagerConstant.ISLAND_SIZE)) && (loc.getZ() > ((this.idZ + 1) * PlotManagerConstant.ISLAND_SIZE) && loc.getZ() < (this.idZ * PlotManagerConstant.ISLAND_SIZE))){
			
			
			
		}
		
	}

}
