package fr.pumpmyplotcore.exceptions;

import java.util.UUID;

import fr.pumpmyplotcore.Plot;

public class RestrictActionToPlotOwnerException extends Exception {

	private Plot island;
	private UUID actionMaker;

	public RestrictActionToPlotOwnerException(Plot island, UUID actionMaker) {
		
		this.island = island;
		this.actionMaker = actionMaker;
		
	}

	public Plot getIsland() {
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
