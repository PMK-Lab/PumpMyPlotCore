package fr.pumpmyskycore;

public interface IIslandManager<T> {

	public Island createIsland(T player);
	
	public Island playerGetIsland(T player);
	
	public void playerSetIsland(T player);
	
	public void deleteIsland(Island i);
	
	public boolean playerHasIsland(T player);
	
}
