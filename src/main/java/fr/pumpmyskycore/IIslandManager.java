package fr.pumpmyskycore;

import java.io.File;

public interface IIslandManager<T> {
	
	public Island createIsland(T player);
	
	public Island getIsland(File f);
	
	public Island getIsland(IslandLocation l);
	
	public Island playerGetIsland(T player);
	
	public void playerSetIsland(T player);
	
	public void playerUnsetIsland(T player);
	
	public boolean playerHasIsland(T player);
	
	public void deleteIsland(Island i);
	
}