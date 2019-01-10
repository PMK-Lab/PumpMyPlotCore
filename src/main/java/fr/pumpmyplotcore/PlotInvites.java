package fr.pumpmyplotcore;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.pumpmyplotcore.PlotManager.PlotManagerConstant;
import fr.pumpmyplotcore.exceptions.PlayerAlreadyInvitedPlotException;
import fr.pumpmyplotcore.exceptions.PlayerDoesNotInvitedPlotException;

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
	
	public boolean isInvites(Plot plot,UUID uuid) {
		
		return this.getPlotInvites(plot).contains(uuid.toString());		
		
	}
	
	public List<String> getPlotInvites(Plot plot){
		
		return this.fileConf.getStringList(INVITES_STRING + plot.getID());
		
	}
	
	private void setPlotInvites(Plot plot,List<String> list) throws IOException {
		
		this.fileConf.set(INVITES_STRING + plot.getID(), list);
		this.fileConf.save(this.file);
		
	}
	
	public void addInvites(Plot plot, UUID uuid) throws PlayerAlreadyInvitedPlotException, IOException {
		
		if(this.isInvites(plot, uuid)) {
			
			throw new PlayerAlreadyInvitedPlotException(uuid,plot);
			
		}
		
		List<String> invites = this.getPlotInvites(plot);
		
		invites.add(uuid.toString());
		
		this.setPlotInvites(plot, invites);
		
	}
	
	public void removeInvites(Plot plot,UUID uuid) throws PlayerDoesNotInvitedPlotException, IOException {
		
		if(!this.isInvites(plot, uuid)) {
			
			throw new PlayerDoesNotInvitedPlotException(uuid,plot);
			
		}
		
		List<String> invites = this.getPlotInvites(plot);
		
		for (Iterator<String> iterators = invites.iterator(); iterators.hasNext();) {
			
			if(iterators.next().equals(uuid.toString())) {
				iterators.remove();
				break;
			}
			
		}
		
		this.setPlotInvites(plot, invites);
		
	}
	

	public File getFile() {
		return file;
	}

	public FileConfiguration getYaml() {
		return this.fileConf;
	}

	public boolean contains(Plot plot) {
		
		return this.fileConf.contains(INVITES_STRING + plot.getID());
		
	}
	
/*	public List<Plot> getPlayerInvites(UUID uuid){
		
		for (String key : this.fileConf.getKeys(true)) {
			
			System.out.println(key);
			
		}	
		
		return new ArrayList<Plot>();
		
	}*/

	public void purgeInvites(Plot plot) throws IOException {
		
		this.fileConf.set(INVITES_STRING + plot.getID(), null);
		this.save();
		
	}
	
}
