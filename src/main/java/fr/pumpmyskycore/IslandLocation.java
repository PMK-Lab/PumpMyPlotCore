package fr.pumpmyskycore;

public class IslandLocation {

	public static final String X_STRING = ".x";
	public static final String Y_STRING = ".y";
	
	private String x;
	private String y;
	
	public IslandLocation(String x, String y) {
		this.x = x;
		this.y = y;
	}
	
	public String getX() {
		
		return this.x;
		
	}
	
	public String getY() {
		
		return this.y;
		
	}

}
