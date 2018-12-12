package fr.pumpmyskycore;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.bukkit.configuration.InvalidConfigurationException;

import fr.pumpmyskycore.exceptions.PlayerAlreadyHaveIslandException;
import fr.pumpmyskycore.exceptions.PlayerDoesNotHaveIslandException;
import fr.pumpmyskycore.exceptions.PlayerNotInThisIsland;

public abstract class IslandManager<T> implements IIslandManager<T>{

	public abstract class IslandManagerConstant {
		
		public static final int ISLAND_SIZE = 4096; // 16 * 256 chunk per island
		public static final int ISLAND_SIDE_NUM = 80; 	// 80*80 = 6400 islands
		public static final String ISLAND_FOLDER_NAME = "islands";
		public static final String ISLAND_INDEX_FILE_NAME = "islands.yml";
		public static final String ISLAND_CHAT_PREFIX = "§6§l[§r§2Pump§eMy§aSky§r§6§l]";		
		
	}
	
	protected Path islandPath;
	protected IslandIndex islandIndex;
	
	public IslandManager(Path configPath) throws IOException, InvalidConfigurationException {
		
		this.islandPath = new File(configPath + File.separator + IslandManagerConstant.ISLAND_FOLDER_NAME).toPath();
		
		
		this.initIslandFolder();
		this.islandIndex = IslandIndex.init(this.islandPath);
	}

	private void initIslandFolder() {
		
		File file = this.islandPath.toFile();
		
		if(file.exists()) {
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
	
	public IslandLocation createFirstFreeLocFile() throws IOException {
		
		for (int x = 1; x <= IslandManagerConstant.ISLAND_SIDE_NUM ; x++) {
			
			for (int y = 1; y <= IslandManagerConstant.ISLAND_SIDE_NUM ; y++) {
				
				IslandLocation loc = new IslandLocation("" + x,"" + y);
				File f = new File(this.islandPath + File.separator + loc.toPath());
				
				if(f.exists()) {
					continue;
				}else {
					f.createNewFile();
				}
				
				return loc;
				
			}
			
		}
		
		System.out.println("NOT ENOUGH ISLANDS !!!!!!!!!!!!§");
		return null;
		
	}
	
	@Override
	public Island createIsland(T player) throws PlayerAlreadyHaveIslandException, PlayerDoesNotHaveIslandException, IOException {
		
		if(this.playerHasIsland(player)) {
			
			throw new PlayerAlreadyHaveIslandException(this.getMinecraftUUID(player), this.playerGetIsland(player));
			
		}
		
		IslandLocation freeLoc = this.createFirstFreeLocFile();
		
		this.islandIndex.setIslandLocation(this.getMinecraftUUID(player), freeLoc);
		
		return Island.create(this.islandPath,freeLoc,this.getMinecraftUUID(player));
		
	}
	
	@Override
	public Island playerGetIsland(T player) throws PlayerDoesNotHaveIslandException {
		
		if(!this.playerHasIsland(player)) {
			
			throw new PlayerDoesNotHaveIslandException(this.getMinecraftUUID(player));
			
		}
		
		return Island.get(this.islandPath,this.islandIndex.getIslandLocation(this.getMinecraftUUID(player)));
		
	}
	
	@Override
	public void playerAddIsland(Island island, T player) throws PlayerDoesNotHaveIslandException, IOException {
		
		if(!this.playerHasIsland(player)) {
			
			throw new PlayerDoesNotHaveIslandException(this.getMinecraftUUID(player));
			
		}
		
		island.addMember(this.getMinecraftUUID(player));
		island.save();
		
	}
	
	@Override
	public void playerRemoveIsland(Island island, T player) throws PlayerDoesNotHaveIslandException, PlayerNotInThisIsland, IOException {
		
		if(!this.playerHasIsland(player)) {
			
			throw new PlayerDoesNotHaveIslandException(this.getMinecraftUUID(player));
			
		}else if(!this.playerGetIsland(player).equals(island)) {
			
			throw new PlayerNotInThisIsland(this.getMinecraftUUID(player), this.playerGetIsland(player) , island);
			
		}
		
		island.removeMember(this.getMinecraftUUID(player));
		island.save();
		
	}
	
	@Override
	public boolean playerHasIsland(T player) {
		
		return this.islandIndex.contains(this.getMinecraftUUID(player));
		
	}

	@Override
	public void deleteIsland(Island i) {
		// TODO Auto-generated method stub
		
	}
	
}
