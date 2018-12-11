package fr.pumpmyskycore;

import java.io.File;
import java.nio.file.Path;

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

	public Path toPath() {
		
		return new File( this.x + File.separator + this.y).toPath();
		
	}

}
