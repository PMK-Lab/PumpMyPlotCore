package pumpmyskycore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import fr.pumpmyskycore.IslandLocation;
import fr.pumpmyskycore.exceptions.IslandLocationParsingException;

public class IslandLocationTest {

	@Test
	public void constantShouldHaveRightValues() {
		
		assertEquals(IslandLocation.X_STRING, ".x");
		assertEquals(IslandLocation.Y_STRING, ".y");
		
	}
	
	@Test
	public void constructorCorrectlyInitializingValuesAndGetterReturningThem() {
		
		IslandLocation loc = new IslandLocation(1, 1);
		
		assertEquals(loc.getX(), 1);
		assertEquals(loc.getY(), 1);
		
		loc = new IslandLocation(58, 25);
		
		assertEquals(loc.getX(), 58);
		assertEquals(loc.getY(), 25);
		
	}
	
	@Test
	public void toPathMethodShouldBeReturnCorrectPath() {
		
		IslandLocation loc = new IslandLocation(28, 82);
		
		File fileLoc = new File(loc.getX() + File.separator + loc.getY());
		
		assertEquals(loc.toPath(), fileLoc.toPath());
		
	}
	
	@Test
	public void parsingFromStringReturnCorrectIslandLocation() throws IslandLocationParsingException {
		
		IslandLocation loc = new IslandLocation(45, 95);
		
		assertEquals(loc.getX(), IslandLocation.parseFromString("45_95").getX());
		assertEquals(loc.getY(), IslandLocation.parseFromString("45_95").getY());
		
	}
	
	@Test
	public void parsingFromStringThrowsException() {
		
		Executable exec = () -> IslandLocation.parseFromString("11");
		
		assertThrows(IslandLocationParsingException.class, exec);
		
		Executable exec2 = () -> IslandLocation.parseFromString("_");
		
		assertThrows(IslandLocationParsingException.class, exec2);
		
		Executable exec3 = () -> IslandLocation.parseFromString("_1");
		
		assertThrows(IslandLocationParsingException.class, exec3);
		
		Executable exec4 = () -> IslandLocation.parseFromString("1_");
		
		assertThrows(IslandLocationParsingException.class, exec4);
		
		Executable exec5 = () -> IslandLocation.parseFromString("a_1");
		
		assertThrows(IslandLocationParsingException.class, exec5);
		
		Executable exec6 = () -> IslandLocation.parseFromString("1_a");
		
		assertThrows(IslandLocationParsingException.class, exec6);
		
	}
	
}
