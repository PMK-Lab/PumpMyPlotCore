package fr.pumpmyskycore.exceptions;

import java.util.UUID;

import fr.pumpmyskycore.Island;

public class PlayerDoesNotInvited extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1909610738522499154L;
	
	private UUID uuid;
	private Island island;
	
	public PlayerDoesNotInvited(UUID uuid, Island island) {
		super();
		this.uuid = uuid;
		this.island = island;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public UUID getUuid() {
		return uuid;
	}
	
	public Island getIsland() {
		return island;
	}
	
}
