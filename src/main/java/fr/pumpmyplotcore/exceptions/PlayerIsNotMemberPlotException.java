package fr.pumpmyplotcore.exceptions;

import java.util.UUID;

import fr.pumpmyplotcore.Plot;

public class PlayerIsNotMemberPlotException extends Exception {

	private UUID uuid;
	private Plot island;

	public PlayerIsNotMemberPlotException(UUID uuid, Plot island) {
		
		this.uuid = uuid;
		this.island = island;
		
	}

	public UUID getUuid() {
		return uuid;
	}

	public Plot getIsland() {
		return island;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5532131247089794958L;

}
