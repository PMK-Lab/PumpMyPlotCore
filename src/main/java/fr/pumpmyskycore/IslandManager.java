package fr.pumpmyskycore;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;

import fr.pumpmyskycore.exceptions.IslandIsNotEmptyException;
import fr.pumpmyskycore.exceptions.PlayerAlreadyHaveIslandException;
import fr.pumpmyskycore.exceptions.PlayerAlreadyInvited;
import fr.pumpmyskycore.exceptions.PlayerDoesNotHaveIslandException;
import fr.pumpmyskycore.exceptions.PlayerDoesNotInvited;
import fr.pumpmyskycore.exceptions.RestrictActionToOwnerIslandException;

public abstract class IslandManager<T> implements IIslandManager<T>{

	public abstract class IslandManagerConstant {
		
		public static final int ISLAND_SIZE = 4096; // 16 * 256 chunk per island
		public static final int ISLAND_SIDE_NUM = 80; 	// 80*80 = 6400 islands
		public static final String ISLAND_FOLDER_NAME = "islands";
		public static final String ISLAND_CHAT_PREFIX = "§6§l[§r§2Pump§eMy§aSky§r§6§l]";
		
		public static final String ISLAND_INDEX_FILE_NAME = "islands.yml";
		public static final String ISLAND_PURGER_FILE_NAME = "purge.yml";
		public static final String ISLAND_INVITES_FILE_NAME = "invites.yml";
		
	}
	
	protected Path islandPath;
	protected IslandIndex islandIndex;
	protected IslandPurger islandPurger;
	protected IslandInvites islandInvites;
	
	public IslandManager(Path configPath) throws IOException, InvalidConfigurationException {
		
		this.islandPath = new File(configPath + File.separator + IslandManagerConstant.ISLAND_FOLDER_NAME).toPath();		
		
		this.initIslandFolder();
		this.islandIndex = IslandIndex.init(this.islandPath);
		this.islandPurger = IslandPurger.init(this.islandPath);
		this.islandInvites = IslandInvites.init(this);
		
	}

	private void initIslandFolder() {
		
		File file = this.islandPath.toFile();
		
		if(!file.exists()) {
			file.mkdir();
		}
		
		for (int x = 1; x <= IslandManagerConstant.ISLAND_SIDE_NUM ; x++) {
			
			File f = new File(this.islandPath + File.separator + x);
			
			if(f.isDirectory() & f.exists()) {
				continue;
			}
			
			f.mkdirs();
			
		}
		
	}
	
	public Island playerCreateIsland(T player) throws PlayerAlreadyHaveIslandException, PlayerDoesNotHaveIslandException, IOException {
		
		if(this.playerHasIsland(player)) {
			
			throw new PlayerAlreadyHaveIslandException(this.getMinecraftUUID(player), this.playerGetIsland(player));
			
		}
		
		IslandLocation freeLoc = this.islandIndex.createFirstFreeLocFile(this.islandPath);
		
		this.islandIndex.setIslandLocation(this.getMinecraftUUID(player), freeLoc);
		
		return Island.create(this.islandPath,freeLoc,this.getMinecraftUUID(player));
		
	}
	
	public Island playerGetIsland(T player) throws PlayerDoesNotHaveIslandException {
		
		if(!this.playerHasIsland(player)) {
			
			throw new PlayerDoesNotHaveIslandException(this.getMinecraftUUID(player));
			
		}
		
		return Island.get(this.islandPath,this.islandIndex.getIslandLocation(this.getMinecraftUUID(player)));
		
	}
	
	public boolean playerLeaveIsland(T player) throws PlayerDoesNotHaveIslandException, IslandIsNotEmptyException, IOException {
		
		if(!this.playerHasIsland(player)) {
			
			throw new PlayerDoesNotHaveIslandException(this.getMinecraftUUID(player));
			
		}
		
		Island island = this.playerGetIsland(player);
		
		if(this.getMinecraftUUID(player).toString().equals(island.getOwner())) {
			
			if(island.getMembersList().isEmpty()) {
				
				this.islandIndex.unsetIslandLocation(this.getMinecraftUUID(player));
				
				island.purge();
				
				this.islandPurger.addIsland(island);
				
				return true;
				
			}else {
				
				throw new IslandIsNotEmptyException(this.getMinecraftUUID(player),island);
				
			}
			
		}else {
			
			this.islandIndex.unsetIslandLocation(this.getMinecraftUUID(player));
			
			return false;
			
		}	
		
	}
	
	public void playerInviteIsland(T invitor, T invited) throws PlayerDoesNotHaveIslandException, RestrictActionToOwnerIslandException, PlayerAlreadyInvited, IOException {
		
		Island island = this.playerGetIsland(invitor);
		
		if(this.playerIsOwner(invitor)) {
			
			this.islandInvites.addInvites(this.getMinecraftUUID(invited), island);			
			
		}else {
			
			throw new RestrictActionToOwnerIslandException(island,this.getMinecraftUUID(invitor));
			
		}
		
	}
	
	public void playerUninviteIsland(T uninvitor, T uninvited) throws PlayerDoesNotHaveIslandException, RestrictActionToOwnerIslandException, PlayerDoesNotInvited, IOException {
		
		Island island = this.playerGetIsland(uninvitor);
		
		if(this.playerIsOwner(uninvitor)) {
			
			this.islandInvites.removeInvites(this.getMinecraftUUID(uninvited), island);			
			
		}else {
			
			throw new RestrictActionToOwnerIslandException(island,this.getMinecraftUUID(uninvitor));
			
		}
		
	}
	
	public void playerAcceptInviteIsland(T joiner, T target) throws PlayerAlreadyHaveIslandException, PlayerDoesNotHaveIslandException {
		
		if(this.playerHasIsland(joiner)) {
			
			throw new PlayerAlreadyHaveIslandException(this.getMinecraftUUID(joiner), this.playerGetIsland(joiner));
			
		}else {
			
			
			
		}
		
	}
	
	public void playerRefuseInviteIsland() {
		
	}
	
	public void playerKickIsland(T joiner, T target) {
		
		
		
	}
	
	
	public boolean playerIsOwner(Island island, T player) {
		
		if(island.getOwner().equals(this.getMinecraftUUID(player).toString())) {
			
			return true;
			
		}
		
		return false;
		
	}
	
	public boolean playerIsOwner(T player) {
		
		try {
			
			Island island = this.playerGetIsland(player);
			
			return this.playerIsOwner(island, player);
			
		} catch (PlayerDoesNotHaveIslandException e) {
			
			return false;
			
		}
		
	}
	
	public boolean playerHasIsland(T player) {
		
		return this.islandIndex.contains(this.getMinecraftUUID(player));
		
	}

	public Island getIsland(IslandLocation parseFromString) {
		
		return Island.get(this.islandPath, parseFromString);
		
	}

	public Path getIslandPath() {
		return islandPath;
	}

	public IslandIndex getIslandIndex() {
		return islandIndex;
	}

	public IslandPurger getIslandPurger() {
		return islandPurger;
	}

	public IslandInvites getIslandInvites() {
		return islandInvites;
	}
	
}
