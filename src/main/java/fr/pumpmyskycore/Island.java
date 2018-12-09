package fr.pumpmyskycore;

import java.util.UUID;

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

	private IslandIndex islandIndex;
	
	public Island() {
		
		this.islandIndex = new IslandIndex();
		
	}
	
	public IslandIndex getIndex() {
		
		return this.islandIndex;
		
	}

}
