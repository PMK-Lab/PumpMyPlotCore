package fr.pumpmyskycore;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.pumpmyskycore.PlotManager.PlotManagerConstant;
import fr.pumpmyskycore.exceptions.PlotLocationParsingException;
import fr.pumpmyskycore.exceptions.PlayerAlreadyInvited;
import fr.pumpmyskycore.exceptions.PlayerDoesNotInvited;

public class PlotInvites {
	
	public final static String INVITES_STRING = "invites.";
	
	public static PlotInvites init(PlotManager<?> manager) throws IOException, InvalidConfigurationException {
		
		File file = new File(manager.plotPath + File.separator + PlotManagerConstant.PLOT_INVITES_FILE_NAME);			
		if(!file.exists()) {
			file.createNewFile();
		}
		
		FileConfiguration fileConf = new YamlConfiguration();
		fileConf.load(file);
		
		return new PlotInvites(manager,file,fileConf);
		
	}

	private File file;
	private FileConfiguration fileConf;
	private PlotManager<?> manager;

	private PlotInvites(PlotManager<?> manager, File f, FileConfiguration fc) throws IOException {
		
		this.file = f;
		this.fileConf = fc;
		
		this.manager = manager;
		
		this.init();
		
	}
	
	private void init() throws IOException {
		
		this.fileConf.createSection("invites");
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

	public boolean contains(UUID minecraftUUID) {
		
		return this.fileConf.contains(INVITES_STRING + minecraftUUID.toString());
		
	}
	
	private void setPlayerInvites(UUID uuid , List<Plot> invites) throws IOException {
		
		List<String> islandsID = new ArrayList<String>();
		
		for (Plot island : invites) {
			
			islandsID.add(island.getID());
			
		}
		
		this.fileConf.set(INVITES_STRING + uuid.toString(), islandsID);
		this.save();
		
	}
	
	public List<Plot> getPlayerInvites(UUID uuid){
		
		if(this.contains(uuid)) {
			
			List<Plot> invites = new ArrayList<Plot>();
			
			for (String plotID : this.fileConf.getStringList(INVITES_STRING + uuid.toString())) {
				
				try {
					invites.add(manager.getPlot(PlotLocation.parseFromString(plotID)));
				} catch (PlotLocationParsingException e) {
					e.printStackTrace();
					continue;
				}
				
			}
			
			return invites;
			
		}
		
		return new ArrayList<>();		
		
	}
	
	public boolean isInvites(UUID uuid, Plot plot) {
		
		return this.getPlayerInvites(uuid).contains(plot);		
		
	}
	
	public void addInvites(UUID uuid, Plot plot) throws PlayerAlreadyInvited, IOException {
		
		if(this.isInvites(uuid, plot)) {
			
			throw new PlayerAlreadyInvited(uuid,plot);
			
		}
		
		List<Plot> invites = this.getPlayerInvites(uuid);
		
		invites.add(plot);
		
		this.setPlayerInvites(uuid, invites);
		
	}
	
	public void removeInvites(UUID uuid, Plot plot) throws PlayerDoesNotInvited, IOException {
		
		if(!this.isInvites(uuid, plot)) {
			
			throw new PlayerDoesNotInvited(uuid,plot);
			
		}
		
		List<Plot> invites = this.getPlayerInvites(uuid);
		
		for (Iterator<Plot> iterators = invites.iterator(); iterators.hasNext();) {
			
			if(iterators.next().equals(plot)) {
				iterators.remove();
				break;
			}
			
		}
		
		this.setPlayerInvites(uuid, invites);
		
	}
	
}
