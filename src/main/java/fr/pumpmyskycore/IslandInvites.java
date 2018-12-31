package fr.pumpmyskycore;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import fr.pumpmyskycore.IslandManager.IslandManagerConstant;

public class IslandInvites {
	
	public final static String INVITES_STRING = "invites.";
	
	public static IslandInvites init(Path indexPath) throws IOException, InvalidConfigurationException {
		
		File file = new File(indexPath + File.separator + IslandManagerConstant.ISLAND_INVITES_FILE_NAME);			
		if(!file.exists()) {
			file.createNewFile();
		}
		
		FileConfiguration fileConf = new YamlConfiguration();
		fileConf.load(file);
		
		return new IslandInvites(file,fileConf);
		
	}

	private File file;
	private FileConfiguration fileConf;

	private IslandInvites(File f, FileConfiguration fc) throws IOException {
		
		this.file = f;
		this.fileConf = fc;
		
		this.init();
		
	}
	
	private void init() throws IOException {
		
		this.fileConf.createSection("invites");
		this.save();
		
	}
	
	private void save() throws IOException {
		
		this.fileConf.save(this.file);
		
	}

	public File getFile() {
		return file;
	}

	public FileConfiguration getYaml() {
		return this.fileConf;
	}

	public boolean contains(UUID minecraftUUID) {
		
		return this.fileConf.contains(INVITES_STRING + minecraftUUID.toString());
		
	}
	
	public List<String> getPlayerInvites(UUID uuid){
		
		List<String> invitesList = new ArrayList<>();
		
		if(this.contains(uuid)) {
			
			
			
		}
		
		return invitesList;		
		
	}
	
	public boolean isInvite(Island island, UUID uuid) {
		
		return true;
		
	}
	
}
