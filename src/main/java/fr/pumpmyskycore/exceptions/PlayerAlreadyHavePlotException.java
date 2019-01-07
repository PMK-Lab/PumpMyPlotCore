package fr.pumpmyskycore.exceptions;

import java.util.UUID;

import fr.pumpmyskycore.Plot;

public class PlayerAlreadyHavePlotException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8295450156496621732L;
	private UUID playerUUID;
	private Plot island;
	
	public PlayerAlreadyHavePlotException(UUID playerUUID, Plot island) {
		
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

	public Plot getIsland() {
		return island;
	}
	

}
