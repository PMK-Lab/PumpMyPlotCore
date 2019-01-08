package fr.pumpmyplotcore.exceptions;

import java.util.UUID;

import fr.pumpmyplotcore.Plot;

public class PlayerAlreadyInvitedPlotException extends Exception {

	private UUID uuid;
	private Plot island;

	public UUID getUuid() {
		return uuid;
	}



	public Plot getIsland() {
		return island;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public PlayerAlreadyInvitedPlotException(UUID uuid, Plot island) {
		super();
		this.uuid = uuid;
		this.island = island;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5018520994682001246L;

}
