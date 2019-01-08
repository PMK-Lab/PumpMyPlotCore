package fr.pumpmyplotcore;

import java.io.File;
import java.nio.file.Path;

import fr.pumpmyplotcore.exceptions.PlotLocationParsingException;

public class PlotLocation {

	public static final String X_STRING = ".x";
	public static final String Z_STRING = ".z";
	
	private int x;
	private int z;
	
	public PlotLocation(int x, int z) {
		this.x = x;
		this.z = z;
	}
	
	public int getX() {
		
		return this.x;
		
	}
	
	public int getZ() {
		
		return this.z;
		
	}
	
	public static PlotLocation parseFromString(String string) throws PlotLocationParsingException {
		
		if(string.contains("_")) {
			
			try {
				
				String stringX = string.split("_")[0];
				String stringZ = string.split("_")[1];
				
				int x = Integer.parseInt(stringX);
				int z = Integer.parseInt(stringZ);
				
				return new PlotLocation(x, z);
				
			} catch (Exception e) {
				
				throw new PlotLocationParsingException(string);
				
			}
			
			
			
		}else {
			throw new PlotLocationParsingException(string);
		}
		
	}
	
	@Override
	public String toString() {
		return "[" + this.getClass().getName() + "] x=" + this.x + " |z=" + this.z;
	}

	public Path toPath() {
		
		return new File( this.x + File.separator + this.z).toPath();
		
	}

}
