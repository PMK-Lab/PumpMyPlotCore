package fr.pumpmyskycore;

import java.io.File;
import java.nio.file.Path;

public abstract class IslandManager<T> implements IIslandManager<T>{

	public abstract class IslandConstant {
		
		public static final int ISLAND_SIZE = 4096; // 16 * 256 chunk per island
		public static final int ISLAND_SIDE_NUM = 80; 	// 80*80 = 6400 islands
		public static final String ISLAND_FOLDER_NAME = "islands";
		
	}
	
	protected Path islandPath;
	
	public IslandManager(Path configPath) {
		
		this.islandPath = new File(configPath + File.separator + IslandConstant.ISLAND_FOLDER_NAME).toPath();
		
		this.initIslandFolder();
		
	}

	private void initIslandFolder() {
		
		File file = this.islandPath.toFile();
		
		if(file.exists()) {
			file.mkdir();
		}
		
		this.setupIslandSubFolder();
		
	}
	
	private void setupIslandSubFolder(Path path) {
		
		for (int i = 1; i <= IslandConstant.ISLAND_SIDE_NUM ; i++) {
			
			File f = new File(path + File.separator + i);
			
			if(f.isDirectory() & f.exists()) {
				continue;
			}
			
			f.mkdirs();
			
		}
		
	}
	
	
	
}
