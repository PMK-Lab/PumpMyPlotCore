package fr.pumpmyskycore;

import java.io.File;
import java.nio.file.Path;
import java.util.UUID;

public class Island {
	
	public static Island get(Path path,IslandLocation islandLocation) {
		
		Island island = new Island();
		island.load(new File(path + File.separator + islandLocation.toPath()));
		
		return island;
		
	}
	
	private void load(File f) {
		// TODO Auto-generated method stub
		
	}

	public static Island create(IslandIndex islandIndex, UUID uniqueId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void add(IslandIndex islandIndex, UUID uniqueId) {
		// TODO Auto-generated method stub
		
	}
	
	public void remove(IslandIndex islandIndex, UUID uniqueId) {
		// TODO Auto-generated method stub
		
	}
	
	public Island() {
		
		
		
	}



}
