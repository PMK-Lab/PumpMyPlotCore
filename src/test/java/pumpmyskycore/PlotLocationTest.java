package pumpmyskycore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import fr.pumpmyplotcore.PlotLocation;
import fr.pumpmyplotcore.exceptions.PlotLocationParsingException;

public class PlotLocationTest {

	@Test
	public void constantShouldHaveRightValues() {
		
		assertEquals(PlotLocation.X_STRING, ".x");
		assertEquals(PlotLocation.Z_STRING, ".z");
		
	}
	
	@Test
	public void constructorCorrectlyInitializingValuesAndGetterReturningThem() {
		
		PlotLocation loc = new PlotLocation(1, 1);
		
		assertEquals(loc.getX(), 1);
		assertEquals(loc.getZ(), 1);
		
		loc = new PlotLocation(58, 25);
		
		assertEquals(loc.getX(), 58);
		assertEquals(loc.getZ(), 25);
		
	}
	
	@Test
	public void toPathMethodShouldBeReturnCorrectPath() {
		
		PlotLocation loc = new PlotLocation(28, 82);
		
		File fileLoc = new File(loc.getX() + File.separator + loc.getZ());
		
		assertEquals(loc.toPath(), fileLoc.toPath());
		
	}
	
	@Test
	public void parsingFromStringReturnCorrectIslandLocation() throws PlotLocationParsingException {
		
		PlotLocation loc = new PlotLocation(45, 95);
		
		assertEquals(loc.getX(), PlotLocation.parseFromString("45_95").getX());
		assertEquals(loc.getZ(), PlotLocation.parseFromString("45_95").getZ());
		
	}
	
	@Test
	public void parsingFromStringThrowsException() {
		
		Executable exec = () -> PlotLocation.parseFromString("11");
		
		assertThrows(PlotLocationParsingException.class, exec);
		
		Executable exec2 = () -> PlotLocation.parseFromString("_");
		
		assertThrows(PlotLocationParsingException.class, exec2);
		
		Executable exec3 = () -> PlotLocation.parseFromString("_1");
		
		assertThrows(PlotLocationParsingException.class, exec3);
		
		Executable exec4 = () -> PlotLocation.parseFromString("1_");
		
		assertThrows(PlotLocationParsingException.class, exec4);
		
		Executable exec5 = () -> PlotLocation.parseFromString("a_1");
		
		assertThrows(PlotLocationParsingException.class, exec5);
		
		Executable exec6 = () -> PlotLocation.parseFromString("1_a");
		
		assertThrows(PlotLocationParsingException.class, exec6);
		
	}
	
}
