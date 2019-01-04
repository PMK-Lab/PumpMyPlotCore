package pumpmyskycore;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import fr.pumpmyskycore.IslandManager.IslandManagerConstant;
import pumpmyskycore.utils.TestIslandManager;

@TestInstance(Lifecycle.PER_CLASS)
public class IslandManagerFolderTest{

	private TestIslandManager manager;
	
	@BeforeAll
	public void init() throws IOException, InvalidConfigurationException{
		
		manager = TestIslandManager.initManager(this.getClass());
		
	}
	
	@Test
	public void managerCorectlyBuildedIslandFolder() {
		
		File file = new File(this.getClass().getSimpleName() + File.separator + IslandManagerConstant.ISLAND_FOLDER_NAME);
		
		assertEquals(file.exists() && file.isDirectory(), true);
		
		boolean test = true;
		
		for (int x = 1; x <= IslandManagerConstant.ISLAND_SIDE_NUM ; x++) {
			
			File f = new File(file + File.separator + x);
			
			if(!f.isDirectory() || !f.exists()) {
				
				test = false;
				
			}
			
		}
		
		assertEquals(test, true);
		
	}
	
	@Test
	public void managerCorectlyBuildedIslandIndex() {
		
		File file = new File(this.getClass().getSimpleName() + File.separator + IslandManagerConstant.ISLAND_FOLDER_NAME + File.separator + IslandManagerConstant.ISLAND_INDEX_FILE_NAME);
		assertEquals(file.exists(), true);
		
	}
	
	@Test
	public void managerCorectlyBuildedIslandPurger() {
		
		File file = new File(this.getClass().getSimpleName() + File.separator + IslandManagerConstant.ISLAND_FOLDER_NAME + File.separator + IslandManagerConstant.ISLAND_PURGER_FILE_NAME);
		assertEquals(file.exists(), true);
		
	}
	
	@Test
	public void managerCorectlyBuildedIslandInvites() {
		
		File file = new File(this.getClass().getSimpleName() + File.separator + IslandManagerConstant.ISLAND_FOLDER_NAME + File.separator + IslandManagerConstant.ISLAND_INVITES_FILE_NAME);
		assertEquals(file.exists(), true);
		
	}

	public TestIslandManager getManager() {
		return manager;
	}
	
}
