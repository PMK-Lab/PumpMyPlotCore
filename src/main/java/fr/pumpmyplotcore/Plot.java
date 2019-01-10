package fr.pumpmyplotcore;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.pumpmyplotcore.PlotManager.PlotManagerConstant;
import fr.pumpmyplotcore.exceptions.InvalidePlotHomeLocationException;
import fr.pumpmyplotcore.exceptions.PlotLocationParsingException;

public class Plot {
	
	public static Plot create(Path path,PlotLocation freeLoc, UUID uniqueId) throws IOException {
		
		File file = new File(path + File.separator + freeLoc.toPath());
		FileConfiguration fileConf = YamlConfiguration.loadConfiguration(file);
		
		fileConf.set("plot.owner",uniqueId.toString());
		
		fileConf.set("plot.name","mon ile" + freeLoc.getX() + "|" + freeLoc.getZ());
		
		fileConf.set("plot.id.x", freeLoc.getX());
		fileConf.set("plot.id.z", freeLoc.getZ());
		
		fileConf.set("plot.home.x", (freeLoc.getX() * PlotManagerConstant.PLOT_SIZE) - (PlotManagerConstant.PLOT_SIZE/2) + 0.5);
		fileConf.set("plot.home.y", 60);
		fileConf.set("plot.home.z", (freeLoc.getZ() * PlotManagerConstant.PLOT_SIZE) - (PlotManagerConstant.PLOT_SIZE/2) + 0.5);
		
		fileConf.set("plot.members", new ArrayList<String>());
		
		fileConf.save(file);
		
		Plot plot = new Plot(file);
		plot.load();
		
		return plot;
		
	}
	
	public static Plot get(Path path,PlotLocation plotLocation) {
		
		File file = new File(path + File.separator + plotLocation.toPath());
		
		Plot plot = new Plot(file);
		plot.load();
		
		return plot;
		
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
			
		this.owner = yaml.getString("plot.owner");
		
		this.name = yaml.getString("plot.name");
				
		this.homeX = yaml.getDouble("plot.home.x");
		this.homeY = yaml.getDouble("plot.home.y");
		this.homeZ = yaml.getDouble("plot.home.z");
		
		this.idX = yaml.getInt("plot.id.x");
		this.idZ = yaml.getInt("plot.id.z");
		
		this.membersList = yaml.getStringList("plot.members");
		
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
		
		this.fileYaml.set("plot.owner", "remover");
		this.fileYaml.save(this.file);
		
	}

	public void save() throws IOException {
		
		this.fileYaml.set("plot.owner", this.owner);
		
		this.fileYaml.set("plot.name", this.name);
		
		this.fileYaml.set("plot.home.x", this.homeX);
		this.fileYaml.set("plot.home.y", this.homeY);
		this.fileYaml.set("plot.home.z", this.homeZ);
		
		this.fileYaml.set("plot.members", this.membersList);
		
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
	
	public void setName(String name) {
		
		this.name = name;
		
	}
	
	public void setOwner(UUID uuid) {
		
		this.owner = uuid.toString();
		
	}
	
	public void resetName() {
		
		this.name = "mon ile" + this.idX + "|" + this.idZ;
		
	}

	public void resetHome() {
		
		this.homeX = ((this.idX * PlotManagerConstant.PLOT_SIZE) - (PlotManagerConstant.PLOT_SIZE/2) + 0.5);
		this.homeY = 60;
		this.homeZ = ((this.getIdZ() * PlotManagerConstant.PLOT_SIZE) - (PlotManagerConstant.PLOT_SIZE/2) + 0.5);
		
	}
	
	public void setHome(PlotHome loc) throws InvalidePlotHomeLocationException {
		
		if(loc.getX() > ((this.idX + 1) * PlotManagerConstant.PLOT_SIZE) || loc.getX() < (this.idX * PlotManagerConstant.PLOT_SIZE)) {
			
			throw new InvalidePlotHomeLocationException(this,loc);
			
		}
		
		if(loc.getZ() > ((this.idZ + 1) * PlotManagerConstant.PLOT_SIZE) || loc.getZ() < (this.idZ * PlotManagerConstant.PLOT_SIZE)) {
			
			throw new InvalidePlotHomeLocationException(this,loc);
			
		}
		
		if(loc.getY() > 256 || loc.getY() < 0) {
			
			throw new InvalidePlotHomeLocationException(this,loc);
			
		}			
			
		this.homeX = loc.getX();
		this.homeY = loc.getY();
		this.homeZ = loc.getZ();
		
	}

	public PlotHome getHome() {
		
		return new PlotHome(this.homeX, this.homeY, this.homeZ);
		
	}

}
