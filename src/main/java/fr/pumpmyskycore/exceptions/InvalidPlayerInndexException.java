package fr.pumpmyskycore.exceptions;

import java.util.UUID;

public class InvalidPlayerInndexException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -692633748292244696L;

	private UUID minecraftUUID;
	
	public InvalidPlayerInndexException(UUID uuid) {
		
		super("");
		this.minecraftUUID = uuid;
		
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public UUID getMinecraftUUID() {
		return minecraftUUID;
	}
	
}
