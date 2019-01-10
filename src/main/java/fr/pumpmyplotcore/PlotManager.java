package fr.pumpmyplotcore;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;

import fr.pumpmyplotcore.exceptions.InvalidePlotHomeLocationException;
import fr.pumpmyplotcore.exceptions.PlayerAlreadyHavePlotException;
import fr.pumpmyplotcore.exceptions.PlayerAlreadyInvitedPlotException;
import fr.pumpmyplotcore.exceptions.PlayerDoesNotHavePlotException;
import fr.pumpmyplotcore.exceptions.PlayerDoesNotInvitedPlotException;
import fr.pumpmyplotcore.exceptions.PlayerIsNotMemberPlotException;
import fr.pumpmyplotcore.exceptions.PlotIsNotEmptyException;
import fr.pumpmyplotcore.exceptions.RestrictActionToPlotOwnerException;

public abstract class PlotManager<T> implements IPlotManager<T>{

	public abstract class PlotManagerConstant {
		
		public static final int PLOT_SIZE = 4096; // 16 * 256 chunk per plot
		public static final int PLOT_SIDE_NUM = 80; 	// 80*80 = 6400 plots
		public static final String PLOT_FOLDER_NAME = "plots";
		public static final String PLOT_CHAT_PREFIX = "§6§l[§r§2Pump§eMy§aSky§r§6§l]";
		
		public static final String PLOT_INDEX_FILE_NAME = "plots.yml";
		public static final String PLOT_PURGER_FILE_NAME = "purge.yml";
		public static final String PLOT_INVITES_FILE_NAME = "invites.yml";
		
	}
	
	protected Path plotPath;
	protected PlotIndex plotIndex;
	protected PlotPurger plotPurger;
	protected PlotInvites plotInvites;
	
	public PlotManager(Path configPath) throws IOException, InvalidConfigurationException {
		
		this.plotPath = new File(configPath + File.separator + PlotManagerConstant.PLOT_FOLDER_NAME).toPath();		
		
		this.initPlotsFolder();
		this.plotIndex = PlotIndex.init(this.plotPath);
		this.plotPurger = PlotPurger.init(this.plotPath);
		this.plotInvites = PlotInvites.init(this);
		
	}

	private void initPlotsFolder() {
		
		File file = this.plotPath.toFile();
		
		if(!file.exists()) {
			file.mkdir();
		}
		
		for (int x = 1; x <= PlotManagerConstant.PLOT_SIDE_NUM ; x++) {
			
			File f = new File(this.plotPath + File.separator + x);
			
			if(f.isDirectory() & f.exists()) {
				continue;
			}
			
			f.mkdirs();
			
		}
		
	}
	
	public Plot playerCreatePlot(T player) throws PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException, IOException {
		
		if(this.playerHasPlot(player)) {
			
			throw new PlayerAlreadyHavePlotException(this.getMinecraftUUID(player), this.playerGetPlot(player));
			
		}
		
		PlotLocation freeLoc = this.plotIndex.createFirstFreeLocFile(this.plotPath);
		
		this.plotIndex.setPlotLocation(this.getMinecraftUUID(player), freeLoc);
		
		return Plot.create(this.plotPath,freeLoc,this.getMinecraftUUID(player));
		
	}
	
	public Plot playerGetPlot(T player) throws PlayerDoesNotHavePlotException {
		
		if(!this.playerHasPlot(player)) {
			
			throw new PlayerDoesNotHavePlotException(this.getMinecraftUUID(player));
			
		}
		
		return Plot.get(this.plotPath,this.plotIndex.getPlotLocation(this.getMinecraftUUID(player)));
		
	}
	
	public PlotHome playerGetHomePlot(T player) throws PlayerDoesNotHavePlotException {
		
		return this.playerGetPlot(player).getHome();
		
	}
	
	public boolean playerLeavePlot(T player) throws PlayerDoesNotHavePlotException, PlotIsNotEmptyException, IOException {
		
		if(!this.playerHasPlot(player)) {
			
			throw new PlayerDoesNotHavePlotException(this.getMinecraftUUID(player));
			
		}
		
		Plot plot = this.playerGetPlot(player);
		
		if(this.playerIsOwner(plot, player)) {
			
			if(plot.getMembersList().isEmpty()) {
				
				this.plotIndex.unsetPlotLocation(this.getMinecraftUUID(player));
				this.plotInvites.purgeInvites(plot);
				this.plotPurger.addPlot(plot);				
				
				plot.purge();			
				
				return true;
				
			}else {
				
				throw new PlotIsNotEmptyException(this.getMinecraftUUID(player),plot);
				
			}
			
		}else {
			
			this.plotIndex.unsetPlotLocation(this.getMinecraftUUID(player));
			
			return false;
			
		}	
		
	}
	
	public void playerInvitePlot(T invitor, T invited) throws PlayerDoesNotHavePlotException, RestrictActionToPlotOwnerException, PlayerAlreadyInvitedPlotException, IOException {
		
		Plot plot = this.playerGetPlot(invitor);
		
		if(this.playerIsOwner(plot,invitor)) {
			
			this.plotInvites.addInvites(this.getMinecraftUUID(invited), plot);			
			
		}else {
			
			throw new RestrictActionToPlotOwnerException(plot,this.getMinecraftUUID(invitor));
			
		}
		
	}
	
	public void playerUninvitePlot(T uninvitor, T uninvited) throws PlayerDoesNotHavePlotException, RestrictActionToPlotOwnerException, PlayerDoesNotInvitedPlotException, IOException {
		
		Plot plot = this.playerGetPlot(uninvitor);
		
		if(this.playerIsOwner(plot,uninvitor)) {
			
			this.plotInvites.removeInvites(this.getMinecraftUUID(uninvited), plot);			
			
		}else {
			
			throw new RestrictActionToPlotOwnerException(plot,this.getMinecraftUUID(uninvitor));
			
		}
		
	}
	
	public void playerAcceptInvitePlot(T joiner, T inviter) throws PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException, PlayerDoesNotInvitedPlotException, IOException {
		
		if(this.playerHasPlot(joiner)) {
			
			throw new PlayerAlreadyHavePlotException(this.getMinecraftUUID(joiner), this.playerGetPlot(joiner));
			
		}else {			
			
			Plot plotInvitor = this.playerGetPlot(inviter);
			UUID joinerUUID = this.getMinecraftUUID(joiner);
			
			if(this.plotInvites.isInvites(joinerUUID, plotInvitor)) {
				
				// accept & remove invites		
				this.plotInvites.removeInvites(joinerUUID, plotInvitor);
				
				// join plot
				this.plotIndex.setPlotLocation(this.getMinecraftUUID(joiner), plotInvitor.toLocation());	
				plotInvitor.addMember(this.getMinecraftUUID(joiner));
				plotInvitor.save();
				
			}else {
			
				throw new PlayerDoesNotInvitedPlotException(joinerUUID, plotInvitor);
				
			}		
			
		}
		
	}
	
	public void playerRefuseInvitePlot(T refuser, T inviter) throws PlayerDoesNotHavePlotException, PlayerDoesNotInvitedPlotException, IOException {
		
		Plot plotInvitor = this.playerGetPlot(inviter);
		UUID refuserUUID = this.getMinecraftUUID(refuser);
		
		if(this.plotInvites.isInvites(refuserUUID, plotInvitor)) {
			
			// delete			
			this.plotInvites.removeInvites(refuserUUID, plotInvitor);
			
		}else {
		
			throw new PlayerDoesNotInvitedPlotException(refuserUUID, plotInvitor);
			
		}		
		
	}
	
	public void playerKickPlot(T owner, T target) throws PlayerDoesNotHavePlotException, RestrictActionToPlotOwnerException, PlayerIsNotMemberPlotException, IOException {
		
		Plot plot = this.playerGetPlot(owner);	
		
		if(this.playerIsOwner(owner)) {
			
			if(plot.containesMember(this.getMinecraftUUID(target))) {
				
				plot.removeMember(this.getMinecraftUUID(target));
				plot.save();
				
				this.plotIndex.unsetPlotLocation(this.getMinecraftUUID(target));
				
			}else {
				
				throw new PlayerIsNotMemberPlotException(this.getMinecraftUUID(target),plot);
				
			}
			
		}else {
			
			throw new RestrictActionToPlotOwnerException(plot,this.getMinecraftUUID(owner));
			
		}
		
	}
	
	public void playerSetHomePlot(T setter, PlotHome loc) throws PlayerDoesNotHavePlotException, RestrictActionToPlotOwnerException, IOException, InvalidePlotHomeLocationException {
		
		Plot plot = this.playerGetPlot(setter);
		
		if(this.playerIsOwner(plot,setter)) {
			
			plot.setHome(loc);
			plot.save();
			
		}else {
			
			throw new RestrictActionToPlotOwnerException(plot,this.getMinecraftUUID(setter));
			
		}
		
	}	
	
	public boolean playerIsOwner(Plot plot, T player) {
		
		if(plot.getOwner().equals(this.getMinecraftUUID(player).toString())) {
			
			return true;
			
		}
		
		return false;
		
	}
	
	public boolean playerIsOwner(T player) {
		
		try {
			
			Plot plot = this.playerGetPlot(player);
			
			return this.playerIsOwner(plot, player);
			
		} catch (PlayerDoesNotHavePlotException e) {
			
			return false;
			
		}
		
	}
	
	public boolean playerHasPlot(T player) {
		
		return this.plotIndex.contains(this.getMinecraftUUID(player));
		
	}

	public Plot getPlot(PlotLocation parseFromString) {
		
		return Plot.get(this.plotPath, parseFromString);
		
	}

	public Path getPlotPath() {
		return plotPath;
	}

	public PlotIndex getPlotIndex() {
		return plotIndex;
	}

	public PlotPurger getPlotPurger() {
		return plotPurger;
	}

	public PlotInvites getPlotInvites() {
		return plotInvites;
	}
	
}
