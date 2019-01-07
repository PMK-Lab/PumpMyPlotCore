package fr.pumpmyskycore.exceptions;

import java.util.UUID;

public class PlayerDoesNotHavePlotException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5668454859345512441L;
	private UUID playerUUID;
	
	public PlayerDoesNotHavePlotException(UUID playerUUID) {
		// TODO Auto-generated constructor stub
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public UUID getPlayerUUID() {
		return playerUUID;
	}
	
}
