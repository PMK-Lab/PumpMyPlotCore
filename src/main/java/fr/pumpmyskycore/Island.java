package fr.pumpmyskycore;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.UUID;

import fr.pumpmyskycore.IslandManager.IslandConstant;

public class Island {
	
	public static Island create(UUID uniqueId) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Island get(UUID uniqueId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void add(UUID uniqueId) {
		// TODO Auto-generated method stub
		
	}
	
	public void remove(UUID uniqueId) {
		// TODO Auto-generated method stub
		
	}
	
	private class IslandIndex {
		
		
		
	}

	private IslandIndex islandIndex;
	
	public Island() {
		
		this.islandIndex = new IslandIndex();
		
	}
	
	public IslandIndex getIndex() {
		
		return this.islandIndex;
		
	}

}
