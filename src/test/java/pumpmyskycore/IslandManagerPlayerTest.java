package pumpmyskycore;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import pumpmyskycore.utils.TestIslandManager;

@TestInstance(Lifecycle.PER_CLASS)
public class IslandManagerPlayerTest {

private TestIslandManager manager;
	
	@BeforeAll
	public void initManager() throws IOException, InvalidConfigurationException {
		
		File file = new File(this.getClass().getSimpleName());
		
		file.mkdir();
			
		manager = new TestIslandManager(file.toPath());	
		
	}
	
}
