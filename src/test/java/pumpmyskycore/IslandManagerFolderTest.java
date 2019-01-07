package pumpmyskycore;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import fr.pumpmyskycore.PlotManager.PlotManagerConstant;
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
		
		File file = this.manager.getFile();
		
		assertTrue(file.exists() && file.isDirectory());
		
		boolean test = true;
		
		for (int x = 1; x <= PlotManagerConstant.ISLAND_SIDE_NUM ; x++) {
			
			File f = new File(manager.getIslandPath() + File.separator + x);
			System.out.print(f.getName() + " ;");
			
			if(!f.isDirectory() || !f.exists()) {
				
				test = false;
				
			}
			
		}
		System.out.print("\n");
		assertTrue(test);
		
	}
	
	@Test
	public void managerCorectlyBuildedIslandIndex() {
		
		File file = new File(this.manager.getFile(),PlotManagerConstant.ISLAND_FOLDER_NAME + File.separator + PlotManagerConstant.ISLAND_INDEX_FILE_NAME);
		assertTrue(file.exists());
		
	}
	
	@Test
	public void managerCorectlyBuildedIslandPurger() {
		
		File file = new File(this.manager.getFile(),PlotManagerConstant.ISLAND_FOLDER_NAME + File.separator + PlotManagerConstant.ISLAND_PURGER_FILE_NAME);
		assertTrue(file.exists());
		
	}
	
	@Test
	public void managerCorectlyBuildedIslandInvites() {
		
		File file = new File(this.manager.getFile(),PlotManagerConstant.ISLAND_FOLDER_NAME + File.separator + PlotManagerConstant.ISLAND_INVITES_FILE_NAME);
		assertTrue(file.exists());
		
	}

	public TestIslandManager getManager() {
		return manager;
	}
	
}
