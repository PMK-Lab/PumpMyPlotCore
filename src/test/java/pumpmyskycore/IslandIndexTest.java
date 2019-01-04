package pumpmyskycore;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import fr.pumpmyskycore.IslandIndex;

@TestInstance(Lifecycle.PER_CLASS)
public class IslandIndexTest {
	
	private IslandIndex index;
	private File file;
	
	public void init() throws IOException, InvalidConfigurationException {
		
		this.file = new File(this.getClass().getSimpleName() + "_" + System.currentTimeMillis());
		this.index = IslandIndex.init(this.file.toPath());
		
	}
	
	@Test
	public void correctlyInitialize() {
		
		
		
	}
	
}
