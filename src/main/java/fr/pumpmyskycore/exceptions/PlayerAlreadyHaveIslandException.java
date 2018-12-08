package fr.pumpmyskycore.exceptions;

import java.util.UUID;

import fr.pumpmyskycore.Island;

public class PlayerAlreadyHaveIslandException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8295450156496621732L;
	private UUID playerUUID;
	private Island island;
	
	public PlayerAlreadyHaveIslandException(UUID playerUUID, Island island) {
		
		super("Player " + playerUUID.toString() + " already have island ! (id : " + island + " )");
		
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
