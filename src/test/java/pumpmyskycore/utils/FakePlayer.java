package pumpmyskycore.utils;

import java.util.UUID;

public class FakePlayer {

	private UUID uuid;
	
	public FakePlayer(UUID uuid) {
		
		this.uuid = uuid;
		
	}

	public UUID getUuid() {
		return uuid;
	}
	
}
