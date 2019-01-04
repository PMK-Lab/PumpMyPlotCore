package pumpmyskycore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import fr.pumpmyskycore.IslandIndex;
import fr.pumpmyskycore.IslandLocation;
import fr.pumpmyskycore.IslandManager.IslandManagerConstant;
import pumpmyskycore.utils.TestIslandManager;

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
