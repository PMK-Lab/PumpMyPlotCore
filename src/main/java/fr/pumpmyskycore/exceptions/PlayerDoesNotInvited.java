package fr.pumpmyskycore.exceptions;

import java.util.UUID;

import fr.pumpmyskycore.Plot;

public class PlayerDoesNotInvited extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1909610738522499154L;
	
	private UUID uuid;
	private Plot island;
	
	public PlayerDoesNotInvited(UUID uuid, Plot island) {
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
	
	public Plot getIsland() {
		return island;
	}
	
}
