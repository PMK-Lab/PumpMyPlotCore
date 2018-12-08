package fr.pumpmyskycore;

import java.io.File;

import fr.pumpmyskycore.exceptions.PlayerAlreadyHaveIslandException;
import fr.pumpmyskycore.exceptions.PlayerDoesNotHaveIslandException;
import fr.pumpmyskycore.exceptions.PlayerNotInThisIsland;

public interface IIslandManager<T> {
	
	public Island createIsland(T player) throws PlayerAlreadyHaveIslandException, PlayerDoesNotHaveIslandException;
	
	public Island getIsland(File f);
	
	public Island getIsland(IslandLocation l);
	
	public Island playerGetIsland(T player) throws PlayerDoesNotHaveIslandException;
	
	public void playerAddIsland( Island island, T player) throws PlayerDoesNotHaveIslandException;
	
	public void playerRemoveIsland( Island island, T player) throws PlayerDoesNotHaveIslandException, PlayerNotInThisIsland;
	
	public boolean playerHasIsland(T player);
	
	public void deleteIsland(Island i);
	
}