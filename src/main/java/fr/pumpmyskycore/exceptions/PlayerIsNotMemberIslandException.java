package fr.pumpmyskycore.exceptions;

import java.util.UUID;

public class PlayerIsNotMemberIslandException extends Exception {

	private UUID uuid;
	private Island island;

	public PlayerIsNotMemberIslandException(UUID uuid, Island island) {
		
		this.uuid = uuid;
		this.island = island;
		
	}

	public UUID getUuid() {
		return uuid;
	}

	public Island getIsland() {
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
