package fr.pumpmyplotcore;

import java.util.UUID;

public interface IPlotManager<T> {
	
	public UUID getMinecraftUUID(T player);
	
}