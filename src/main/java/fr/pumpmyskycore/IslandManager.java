package fr.pumpmyskycore;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import fr.pumpmyskycore.exceptions.PlayerAlreadyHaveIslandException;
import fr.pumpmyskycore.exceptions.PlayerDoesNotHaveIslandException;

public abstract class IslandManager<T> implements IIslandManager<T>{

	public abstract class IslandConstant {
		
		public static final int ISLAND_SIZE = 4096; // 16 * 256 chunk per island
		public static final int ISLAND_SIDE_NUM = 80; 	// 80*80 = 6400 islands
		public static final String ISLAND_FOLDER_NAME = "islands";
		public static final String ISLAND_INDEX_FILE_NAME = "islands.yml";		
		
	}
	
	protected Path islandPath;
	protected IslandIndex islandIndex;
	
	public IslandManager(Path configPath) throws IOException {
		
		this.islandPath = new File(configPath + File.separator + IslandConstant.ISLAND_FOLDER_NAME).toPath();
		
		
		this.initIslandFolder();
		this.islandIndex = IslandIndex.init(this.islandPath);
	}

	private void initIslandFolder() {
		
		File file = this.islandPath.toFile();
		
		if(file.exists()) {
			file.mkdir();
		}
		
		for (int i = 1; i <= IslandConstant.ISLAND_SIDE_NUM ; i++) {
			
			File f = new File(this.islandPath + File.separator + i);
			
			if(f.isDirectory() & f.exists()) {
				continue;
			}
			
			f.mkdirs();
			
		}
		
	}
	
	@Override
	public Island getIsland(File f) {
		return null;
		//return Island.load(f);	
	}
	
	@Override
	public Island getIsland(IslandLocation l) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Island createIsland(T player) throws PlayerAlreadyHaveIslandException, PlayerDoesNotHaveIslandException {
		
		if(this.playerHasIsland(player)) {
			
			throw new PlayerAlreadyHaveIslandException(this.getMinecraftUUID(player), this.playerGetIsland(player));
			
		}
		
		return Island.create(this.islandIndex,this.getMinecraftUUID(player));
		
	}
	
	@Override
	public Island playerGetIsland(T player) throws PlayerDoesNotHaveIslandException {
		
		if(!this.playerHasIsland(player)) {
			
			throw new PlayerDoesNotHaveIslandException(this.getMinecraftUUID(player));
			
		}
		
		return Island.get(this.islandIndex,this.getMinecraftUUID(player));
		
	}
	
}
