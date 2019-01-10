package fr.pumpmyplotcore.exceptions;

import fr.pumpmyplotcore.Plot;
import fr.pumpmyplotcore.PlotHomeLocation;

public class InvalidePlotHomeLocationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8545358645206896623L;

	private Plot plot;
	private PlotHomeLocation loc;
	
	public InvalidePlotHomeLocationException(Plot plot, PlotHomeLocation loc) {
		
		this.plot = plot;
		this.loc = loc;
		
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Plot getPlot() {
		return plot;
	}

	public PlotHomeLocation getLoc() {
		return loc;
	}

}
