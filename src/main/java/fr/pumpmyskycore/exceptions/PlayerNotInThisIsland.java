package fr.pumpmyskycore.exceptions;

import java.util.UUID;

import fr.pumpmyskycore.Island;

public class PlayerNotInThisIsland extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2479579204256895474L;
	private UUID playerUUID;
	private Island playerIsland;
	private Island targetIsland;

	public PlayerNotInThisIsland(UUID playerUUID, Island playerIsland, Island targetIsland) {
		
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

	public Island getPlayerIsland() {
		return playerIsland;
	}

	public Island getTargetIsland() {
		return targetIsland;
	}

}
