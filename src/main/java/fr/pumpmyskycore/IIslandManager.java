package fr.pumpmyskycore;

public interface IIslandManager<T> {

	public Island createIsland(T player);
	
	public Island playerGetIsland(T player);
	
	public void playerSetIsland(T player);
	
	public void playerUnsetIsland(T player);
	
	public boolean playerHasIsland(T player);
	
}
