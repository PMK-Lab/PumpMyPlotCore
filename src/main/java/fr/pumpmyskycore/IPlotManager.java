package fr.pumpmyskycore;

import java.util.UUID;

public interface IPlotManager<T> {
	
	public UUID getMinecraftUUID(T player);
	
	public void pastePlotSchematic(PlotLocation islandLocation);
	
}