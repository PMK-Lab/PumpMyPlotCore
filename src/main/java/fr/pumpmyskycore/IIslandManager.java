package fr.pumpmyskycore;

import java.io.IOException;
import java.util.UUID;

import fr.pumpmyskycore.exceptions.IslandIsNotEmptyException;
import fr.pumpmyskycore.exceptions.PlayerAlreadyHaveIslandException;
import fr.pumpmyskycore.exceptions.PlayerDoesNotHaveIslandException;

public interface IIslandManager<T> {
	
	public UUID getMinecraftUUID(T player);
	
	public boolean playerHasIsland(T player);
	
	public Island playerCreateIsland(T player) throws PlayerAlreadyHaveIslandException, PlayerDoesNotHaveIslandException, IOException;
	
	public Island playerGetIsland(T player) throws PlayerDoesNotHaveIslandException;
	
	public boolean playerLeaveIsland(T player) throws PlayerDoesNotHaveIslandException, IslandIsNotEmptyException, IOException;
	
	public void playerInviteIsland(Island island, T player);
	
	public void playerUninviteIsland(Island island, T player);
	
}