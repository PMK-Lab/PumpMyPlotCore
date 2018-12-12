package fr.pumpmyskycore;

import java.io.File;
import java.nio.file.Path;

public class IslandLocation {

	public static final String X_STRING = ".x";
	public static final String Y_STRING = ".y";
	
	private int x;
	private int y;
	
	public IslandLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		
		return this.x;
		
	}
	
	public int getY() {
		
		return this.y;
		
	}

	public Path toPath() {
		
		return new File( this.x + File.separator + this.y).toPath();
		
	}

}
