package fr.pumpmyplotcore.exceptions;

import java.util.UUID;

public class UnExistPlayerIndexException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -692633748292244696L;

	private UUID minecraftUUID;
	
	public UnExistPlayerIndexException(UUID uuid) {
		
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
