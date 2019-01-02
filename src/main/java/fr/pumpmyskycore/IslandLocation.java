package fr.pumpmyskycore;

import java.io.File;
import java.nio.file.Path;

import fr.pumpmyskycore.exceptions.IslandLocationParsingException;

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
	
	public static IslandLocation parseFromString(String string) throws IslandLocationParsingException {
		
		if(string.contains("_")) {
			
			try {
				
				String stringX = string.split("_")[0];
				String stringY = string.split("_")[1];
				
				int x = Integer.parseInt(stringX);
				int y = Integer.parseInt(stringY);
				
				return new IslandLocation(x, y);
				
			} catch (Exception e) {
				
				throw new IslandLocationParsingException(string);
				
			}
			
			
			
		}else {
			throw new IslandLocationParsingException(string);
		}
		
	}

	public Path toPath() {
		
		return new File( this.x + File.separator + this.y).toPath();
		
	}

}
