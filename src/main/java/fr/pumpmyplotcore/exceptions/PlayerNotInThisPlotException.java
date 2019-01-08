package fr.pumpmyplotcore.exceptions;

import java.util.UUID;

import fr.pumpmyplotcore.Plot;

public class PlayerNotInThisPlotException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2479579204256895474L;
	private UUID playerUUID;
	private Plot playerIsland;
	private Plot targetIsland;

	public PlayerNotInThisPlotException(UUID playerUUID, Plot playerIsland, Plot targetIsland) {
		
		super("Player " + playerUUID.toString() + " already have island ! (id : " + playerIsland + " )\n He can't join island : " + targetIsland);
		
		this.playerUUID = playerUUID;
		this.playerIsland = playerIsland;
		this.targetIsland = targetIsland;
		
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public UUID getPlayerUUID() {
		return playerUUID;
	}

	public Plot getPlayerIsland() {
		return playerIsland;
	}

	public Plot getTargetIsland() {
		return targetIsland;
	}

}
