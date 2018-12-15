package fr.pumpmyskycore.exceptions;

import java.util.UUID;

import fr.pumpmyskycore.Island;

public class IslandIsNotBeEmptyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7443197913897442548L;
	private UUID playerUUID;
	private Island island;
	
	public IslandIsNotBeEmptyException(UUID playerUUID, Island island) {
		super();
		this.playerUUID = playerUUID;
		this.island = island;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public UUID getPlayerUUID() {
		return playerUUID;
	}

	public Island getIsland() {
		return island;
	}
	
	

}
