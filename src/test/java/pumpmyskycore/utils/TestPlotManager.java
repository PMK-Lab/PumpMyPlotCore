package pumpmyskycore.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;

import fr.pumpmyskycore.PlotLocation;
import fr.pumpmyskycore.PlotManager;

public class TestPlotManager extends PlotManager<FakePlayer> {

	protected File file;
	
	public TestPlotManager(File file) throws IOException, InvalidConfigurationException {
		super(file.toPath());
		this.file = file;
	}

	@Override
	public UUID getMinecraftUUID(FakePlayer player) {
		
		return player.getUuid();
		
	}
	
	public static TestPlotManager initManager(Class<?> c) throws IOException, InvalidConfigurationException {
	
		File file = new File("TEST" + File.separator + c.getSimpleName() + "_" + System.currentTimeMillis());
		
		file.mkdir();
			
		return new TestPlotManager(file);	
		
	}

	public File getFile() {
		return file;
	}

	@Override
	public void pastePlotSchematic(PlotLocation islandLocation) {
		// TODO Auto-generated method stub
		
	}

}
