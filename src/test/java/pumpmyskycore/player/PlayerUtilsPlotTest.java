package pumpmyskycore.player;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;
import org.junit.jupiter.api.Test;

import fr.pumpmyplotcore.Plot;
import fr.pumpmyplotcore.exceptions.PlayerAlreadyHavePlotException;
import fr.pumpmyplotcore.exceptions.PlayerDoesNotHavePlotException;
import pumpmyskycore.utils.FakePlayer;
import pumpmyskycore.utils.TestPlotManager;

public class PlayerUtilsPlotTest {

	@Test
	public void playerIsOwner() throws IOException, InvalidConfigurationException, PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		FakePlayer player1 = new FakePlayer(UUID.randomUUID());		
		FakePlayer player2 = new FakePlayer(UUID.randomUUID());
		
		Plot is1 = manager.playerCreatePlot(player1);

		assertTrue(manager.playerIsOwner(player1));
		assertFalse(manager.playerIsOwner(player2));
		
		
		assertTrue(manager.playerIsOwner(is1,player1));
		assertFalse(manager.playerIsOwner(is1,player2));
		
	}
	
	@Test
	public void playerHasPlotReturnCorrectValue() throws IOException, InvalidConfigurationException, PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		UUID uuid = UUID.randomUUID();
		FakePlayer player = new FakePlayer(uuid);
		
		assertFalse(manager.playerHasPlot(player));
		
		manager.playerCreatePlot(player);
		
		assertTrue(manager.playerHasPlot(player));		
		
	}
	
}
