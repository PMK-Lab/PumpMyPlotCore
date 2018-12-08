package fr.pumpmyskycore;

import java.io.File;

public class IslandManager {

	public abstract class IslandConstant {
		
		public static final int ISLAND_SIZE = 4096; // 16 * 256 chunk per island
		public static final int ISLAND_SIDE_NUM = 80; 	// 80*80 = 6400 islands
		public static final String ISLAND_FOLDER_NAME = "islands";
		
	}

	public void initIslandFolder(File f) {
		
		if(!f.exists()) {
			f.mkdir();
		}
		
		this.setupIslandSubFolder(f.toPath());
		
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
