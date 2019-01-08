package fr.pumpmyplotcore.exceptions;

import java.util.UUID;

import fr.pumpmyplotcore.Plot;

public class PlotIsNotEmptyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7443197913897442548L;
	private UUID playerUUID;
	private Plot island;
	
	public PlotIsNotEmptyException(UUID playerUUID, Plot island) {
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

	public Plot getIsland() {
		return island;
	}
	
	

}
