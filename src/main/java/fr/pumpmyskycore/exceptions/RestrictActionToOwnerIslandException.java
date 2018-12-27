package fr.pumpmyskycore.exceptions;

import java.util.UUID;

import fr.pumpmyskycore.Island;

public class RestrictActionToOwnerIslandException extends Exception {

	private Island island;
	private UUID actionMaker;

	public RestrictActionToOwnerIslandException(Island island, UUID actionMaker) {
		
		this.island = island;
		this.actionMaker = actionMaker;
		
	}

	public Island getIsland() {
		return island;
	}

	public UUID getActionMaker() {
		return actionMaker;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1439608763156578488L;

}
