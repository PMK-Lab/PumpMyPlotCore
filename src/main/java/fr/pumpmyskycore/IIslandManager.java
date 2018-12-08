package fr.pumpmyskycore;

public interface IIslandManager<T> {

	public Island createIsland(T player);
	
	public Island getIsland(T player);
	
	public void unsetIsland(T player);
	
	public void deleteIsland(Island i);
	
	public boolean playerHasIsland(T player);
	
}
