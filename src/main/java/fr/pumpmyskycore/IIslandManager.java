package fr.pumpmyskycore;

import java.util.UUID;

public interface IIslandManager<T> {
	
	public UUID getMinecraftUUID(T player);
	
}