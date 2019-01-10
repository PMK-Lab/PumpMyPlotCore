package fr.pumpmyplotcore;

public class PlotHomeLocation {

	private double x;
	private double y;
	private double z;
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getZ() {
		return z;
	}
	
	public PlotHomeLocation(double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}	
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof PlotHomeLocation && this.x == ((PlotHomeLocation) obj).getX() && this.y == ((PlotHomeLocation) obj).getY() && this.z == ((PlotHomeLocation) obj).getZ();
	}
	
}
