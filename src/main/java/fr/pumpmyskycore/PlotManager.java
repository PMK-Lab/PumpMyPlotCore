package fr.pumpmyskycore;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;

import fr.pumpmyskycore.exceptions.PlotIsNotEmptyException;
import fr.pumpmyskycore.exceptions.PlayerAlreadyHavePlotException;
import fr.pumpmyskycore.exceptions.PlayerAlreadyInvited;
import fr.pumpmyskycore.exceptions.PlayerDoesNotHavePlotException;
import fr.pumpmyskycore.exceptions.PlayerDoesNotInvited;
import fr.pumpmyskycore.exceptions.PlayerIsNotMemberPlotException;
import fr.pumpmyskycore.exceptions.RestrictActionToPlotOwnerException;

public abstract class PlotManager<T> implements IPlotManager<T>{

	public abstract class PlotManagerConstant {
		
		public static final int ISLAND_SIZE = 4096; // 16 * 256 chunk per island
		public static final int ISLAND_SIDE_NUM = 80; 	// 80*80 = 6400 islands
		public static final String ISLAND_FOLDER_NAME = "islands";
		public static final String ISLAND_CHAT_PREFIX = "§6§l[§r§2Pump§eMy§aSky§r§6§l]";
		
		public static final String ISLAND_INDEX_FILE_NAME = "islands.yml";
		public static final String ISLAND_PURGER_FILE_NAME = "purge.yml";
		public static final String ISLAND_INVITES_FILE_NAME = "invites.yml";
		
	}
	
	protected Path islandPath;
	protected PlotIndex islandIndex;
	protected PlotPurger islandPurger;
	protected PlotInvites islandInvites;
	
	public PlotManager(Path configPath) throws IOException, InvalidConfigurationException {
		
		this.islandPath = new File(configPath + File.separator + PlotManagerConstant.ISLAND_FOLDER_NAME).toPath();		
		
		this.initIslandFolder();
		this.islandIndex = PlotIndex.init(this.islandPath);
		this.islandPurger = PlotPurger.init(this.islandPath);
		this.islandInvites = PlotInvites.init(this);
		
	}

	private void initIslandFolder() {
		
		File file = this.islandPath.toFile();
		
		if(!file.exists()) {
			file.mkdir();
		}
		
		for (int x = 1; x <= PlotManagerConstant.ISLAND_SIDE_NUM ; x++) {
			
			File f = new File(this.islandPath + File.separator + x);
			
			if(f.isDirectory() & f.exists()) {
				continue;
			}
			
			f.mkdirs();
			
		}
		
	}
	
	public Plot playerCreateIsland(T player) throws PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException, IOException {
		
		if(this.playerHasIsland(player)) {
			
			throw new PlayerAlreadyHavePlotException(this.getMinecraftUUID(player), this.playerGetIsland(player));
			
		}
		
		PlotLocation freeLoc = this.islandIndex.createFirstFreeLocFile(this.islandPath);
		
		this.islandIndex.setIslandLocation(this.getMinecraftUUID(player), freeLoc);
		
		return Plot.create(this.islandPath,freeLoc,this.getMinecraftUUID(player));
		
	}
	
	public Plot playerGetIsland(T player) throws PlayerDoesNotHavePlotException {
		
		if(!this.playerHasIsland(player)) {
			
			throw new PlayerDoesNotHavePlotException(this.getMinecraftUUID(player));
			
		}
		
		return Plot.get(this.islandPath,this.islandIndex.getIslandLocation(this.getMinecraftUUID(player)));
		
	}
	
	public boolean playerLeaveIsland(T player) throws PlayerDoesNotHavePlotException, PlotIsNotEmptyException, IOException {
		
		if(!this.playerHasIsland(player)) {
			
			throw new PlayerDoesNotHavePlotException(this.getMinecraftUUID(player));
			
		}
		
		Plot island = this.playerGetIsland(player);
		
		if(this.getMinecraftUUID(player).toString().equals(island.getOwner())) {
			
			if(island.getMembersList().isEmpty()) {
				
				this.islandIndex.unsetIslandLocation(this.getMinecraftUUID(player));
				
				island.purge();
				
				this.islandPurger.addIsland(island);
				
				return true;
				
			}else {
				
				throw new PlotIsNotEmptyException(this.getMinecraftUUID(player),island);
				
			}
			
		}else {
			
			this.islandIndex.unsetIslandLocation(this.getMinecraftUUID(player));
			
			return false;
			
		}	
		
	}
	
	public void playerInviteIsland(T invitor, T invited) throws PlayerDoesNotHavePlotException, RestrictActionToPlotOwnerException, PlayerAlreadyInvited, IOException {
		
		Plot island = this.playerGetIsland(invitor);
		
		if(this.playerIsOwner(invitor)) {
			
			this.islandInvites.addInvites(this.getMinecraftUUID(invited), island);			
			
		}else {
			
			throw new RestrictActionToPlotOwnerException(island,this.getMinecraftUUID(invitor));
			
		}
		
	}
	
	public void playerUninviteIsland(T uninvitor, T uninvited) throws PlayerDoesNotHavePlotException, RestrictActionToPlotOwnerException, PlayerDoesNotInvited, IOException {
		
		Plot island = this.playerGetIsland(uninvitor);
		
		if(this.playerIsOwner(uninvitor)) {
			
			this.islandInvites.removeInvites(this.getMinecraftUUID(uninvited), island);			
			
		}else {
			
			throw new RestrictActionToPlotOwnerException(island,this.getMinecraftUUID(uninvitor));
			
		}
		
	}
	
	public void playerAcceptInviteIsland(T joiner, T inviter) throws PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException, PlayerDoesNotInvited, IOException {
		
		if(this.playerHasIsland(joiner)) {
			
			throw new PlayerAlreadyHavePlotException(this.getMinecraftUUID(joiner), this.playerGetIsland(joiner));
			
		}else {			
			
			Plot islandInvitor = this.playerGetIsland(inviter);
			UUID joinerUUID = this.getMinecraftUUID(joiner);
			
			if(this.islandInvites.isInvites(joinerUUID, islandInvitor)) {
				
				// accept & remove invites		
				this.islandInvites.removeInvites(joinerUUID, islandInvitor);
				
				// join islands
				this.islandIndex.setIslandLocation(this.getMinecraftUUID(joiner), islandInvitor.toLocation());	
				islandInvitor.addMember(this.getMinecraftUUID(joiner));
				islandInvitor.save();
				
			}else {
			
				throw new PlayerDoesNotInvited(joinerUUID, islandInvitor);
				
			}		
			
		}
		
	}
	
	public void playerRefuseInviteIsland(T refuser, T inviter) throws PlayerDoesNotHavePlotException, PlayerDoesNotInvited, IOException {
		
		Plot islandInvitor = this.playerGetIsland(inviter);
		UUID refuserUUID = this.getMinecraftUUID(refuser);
		
		if(this.islandInvites.isInvites(refuserUUID, islandInvitor)) {
			
			// delete			
			this.islandInvites.removeInvites(refuserUUID, islandInvitor);
			
		}else {
		
			throw new PlayerDoesNotInvited(refuserUUID, islandInvitor);
			
		}		
		
	}
	
	public void playerKickIsland(T owner, T target) throws PlayerDoesNotHavePlotException, RestrictActionToPlotOwnerException, PlayerIsNotMemberPlotException, IOException {
		
		Plot island = this.playerGetIsland(owner);	
		
		if(this.playerIsOwner(owner)) {
			
			if(island.containesMember(this.getMinecraftUUID(target))) {
				
				island.removeMember(this.getMinecraftUUID(target));
				island.save();
				
				this.islandIndex.unsetIslandLocation(this.getMinecraftUUID(target));
				
			}else {
				
				throw new PlayerIsNotMemberPlotException(this.getMinecraftUUID(target),island);
				
			}
			
		}else {
			
			throw new RestrictActionToPlotOwnerException(island,this.getMinecraftUUID(owner));
			
		}
		
	}
	
	public void playerSetHomeIsland(T setter, PlotHome loc) throws PlayerDoesNotHavePlotException, RestrictActionToPlotOwnerException, IOException {
		
		Plot island = this.playerGetIsland(setter);
		
		if(this.playerIsOwner(setter)) {
			
			island.setHome(loc);
			island.save();
			
		}else {
			
			throw new RestrictActionToPlotOwnerException(island,this.getMinecraftUUID(setter));
			
		}
		
	}
	
	
	public boolean playerIsOwner(Plot island, T player) {
		
		if(island.getOwner().equals(this.getMinecraftUUID(player).toString())) {
			
			return true;
			
		}
		
		return false;
		
	}
	
	public boolean playerIsOwner(T player) {
		
		try {
			
			Plot island = this.playerGetIsland(player);
			
			return this.playerIsOwner(island, player);
			
		} catch (PlayerDoesNotHavePlotException e) {
			
			return false;
			
		}
		
	}
	
	public boolean playerHasIsland(T player) {
		
		return this.islandIndex.contains(this.getMinecraftUUID(player));
		
	}

	public Plot getIsland(PlotLocation parseFromString) {
		
		return Plot.get(this.islandPath, parseFromString);
		
	}

	public Path getIslandPath() {
		return islandPath;
	}

	public PlotIndex getIslandIndex() {
		return islandIndex;
	}

	public PlotPurger getIslandPurger() {
		return islandPurger;
	}

	public PlotInvites getIslandInvites() {
		return islandInvites;
	}
	
}
