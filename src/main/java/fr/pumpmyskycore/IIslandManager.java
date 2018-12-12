package fr.pumpmyskycore;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import fr.pumpmyskycore.exceptions.PlayerAlreadyHaveIslandException;
import fr.pumpmyskycore.exceptions.PlayerDoesNotHaveIslandException;
import fr.pumpmyskycore.exceptions.PlayerNotInThisIsland;

public interface IIslandManager<T> {
	
	public UUID getMinecraftUUID(T player);
	
	public IslandLocation createFirstFreeLocFile() throws IOException;
	
	public Island createIsland(T player) throws PlayerAlreadyHaveIslandException, PlayerDoesNotHaveIslandException, IOException;
	
	public Island getIsland(File f);
	
	public Island getIsland(IslandLocation l);
	
	public Island playerGetIsland(T player) throws PlayerDoesNotHaveIslandException;
	
	public void playerAddIsland( Island island, T player) throws PlayerDoesNotHaveIslandException, IOException;
	
	public void playerRemoveIsland( Island island, T player) throws PlayerDoesNotHaveIslandException, PlayerNotInThisIsland, IOException;
	
	public boolean playerHasIsland(T player);
	
	public void deleteIsland(Island i);
	
}