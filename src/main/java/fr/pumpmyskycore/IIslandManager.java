package fr.pumpmyskycore;

import java.io.IOException;
import java.util.UUID;

import fr.pumpmyskycore.exceptions.IslandIsNotBeEmptyException;
import fr.pumpmyskycore.exceptions.PlayerAlreadyHaveIslandException;
import fr.pumpmyskycore.exceptions.PlayerDoesNotHaveIslandException;
import fr.pumpmyskycore.exceptions.PlayerNotInThisIsland;

public interface IIslandManager<T> {
	
	public UUID getMinecraftUUID(T player);
	
	public Island playerCreateIsland(T player) throws PlayerAlreadyHaveIslandException, PlayerDoesNotHaveIslandException, IOException;
	
	public Island playerGetIsland(T player) throws PlayerDoesNotHaveIslandException;
	
	public void playerLeaveIsland(T player) throws PlayerDoesNotHaveIslandException, IslandIsNotBeEmptyException, IOException;
	
	public void playerAddIsland( Island island, T player) throws PlayerDoesNotHaveIslandException, IOException;
	
	public void playerRemoveIsland( Island island, T player) throws PlayerDoesNotHaveIslandException, PlayerNotInThisIsland, IOException;
	
	public boolean playerHasIsland(T player);
	
	public void deleteIsland(Island i);
	
}