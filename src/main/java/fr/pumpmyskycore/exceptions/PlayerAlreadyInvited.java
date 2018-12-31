package fr.pumpmyskycore.exceptions;

import java.util.UUID;

import fr.pumpmyskycore.Island;

public class PlayerAlreadyInvited extends Exception {

	private UUID uuid;
	private Island island;

	public UUID getUuid() {
		return uuid;
	}



	public Island getIsland() {
		return island;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public PlayerAlreadyInvited(UUID uuid, Island island) {
		super();
		this.uuid = uuid;
		this.island = island;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5018520994682001246L;

}
