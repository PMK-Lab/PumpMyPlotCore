package fr.pumpmyskycore;

import java.io.File;
import java.nio.file.Path;
import java.util.UUID;

public class Island {
	
	public static Island create(UUID uniqueId) {
		
		return null;
		
	}
	
	public static Island get(Path path,IslandLocation islandLocation) {
		
		Island island = new Island();
		island.load(new File(path + File.separator + islandLocation.toPath()));
		
		return island;
		
	}
	
	public static void delete(Path path, IslandLocation islandLocation) {
		
		
		
	}
	
	private void load(File f) {
		
		
		
		
	}
	

	public void add(UUID uniqueId) {
		// TODO Auto-generated method stub
		
	}
	
	public void remove(UUID uniqueId) {
		// TODO Auto-generated method stub
		
	}
	
	public Island() {
		
		
		
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}



}
