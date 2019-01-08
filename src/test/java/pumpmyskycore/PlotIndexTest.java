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

import fr.pumpmyplotcore.PlotIndex;
import fr.pumpmyplotcore.PlotLocation;
import fr.pumpmyplotcore.PlotManager.PlotManagerConstant;
import pumpmyskycore.utils.TestPlotManager;

@TestInstance(Lifecycle.PER_CLASS)
public class PlotIndexTest {
	
	private File file;
	
	@BeforeAll
	public void init() throws IOException, InvalidConfigurationException {
		
		this.file = new File("TEST" + File.separator + this.getClass().getSimpleName() + "_" + System.currentTimeMillis());
		
	}
	
	@Test
	public void returnCorrectIslandLocatioonOnCreatingFirstFreeLocFile() throws IOException, InvalidConfigurationException {
		
		TestPlotManager manager = new TestPlotManager(this.file);
		
		PlotLocation loc = null;
		
		for (int x = 1; x <= 3 ; x++) {
			
			for (int z = 1; z <= PlotManagerConstant.PLOT_SIDE_NUM ; z++) {
				
				loc = manager.getPlotIndex().createFirstFreeLocFile(manager.getPlotPath());				
				System.out.println(x + ":" + z + "\t" + loc);
				
				assertEquals(loc.getX(),x);
				assertEquals(loc.getZ(),z);
				
				File f = new File(manager.getPlotPath() + File.separator + x + File.separator + z);
				assertTrue(f.exists());
				
			}
			System.out.println("");
		}
		
		assertFalse(loc == null);
		
		File f = new File(manager.getPlotPath() + File.separator + 2 + File.separator + 50);
		assertTrue(f.delete());
		
		loc = manager.getPlotIndex().createFirstFreeLocFile(manager.getPlotPath());				
		System.out.println(2 + ":" + 50 + "\t" + loc);
		
		assertEquals(loc.getX(),2);
		assertEquals(loc.getZ(),50);		
		
	}
	
	//@Test
	public void initIndexSection() throws IOException, InvalidConfigurationException {
		
		PlotIndex index = PlotIndex.init(file.toPath());
		
		assertTrue(index.getYaml().isConfigurationSection("islands"));
		
	}
	
}
