package fr.pumpmyplotcore.exceptions;

public class PlotLocationParsingException extends Exception {

	private String parsingString;
	
	public PlotLocationParsingException(String string) {
		super("Impossible to parse \"" + string + "\" to Island ID");
		this.parsingString = string;
	}

	public String getParsingString() {
		return parsingString;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2531969246057424467L;
	
	

}
