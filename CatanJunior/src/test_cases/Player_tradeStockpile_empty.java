package test_cases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import Board.Stockpile;
import map.COLOUR;
import map.RESOURCE;
import player.Player;
import player.PlayerCtrl;

public class Player_tradeStockpile_empty {
	@Test
	public void test_playerTrade_stockpileOption_EmptyStockpile_5() {
		Stockpile sp = Stockpile.getInstance();
		
		Player p1 = new Player("Derek",COLOUR.RED);
		PlayerCtrl cont = PlayerCtrl.getInstance();
		cont.addPlayer(p1);
		
		p1.getPlayerPile().incrementPile(RESOURCE.CUTLASSES, 1);
		p1.getPlayerPile().incrementPile(RESOURCE.GOLD, 1);
		p1.getPlayerPile().incrementPile(RESOURCE.GOATS, 1);
		
		sp.decrementPile(RESOURCE.CUTLASSES, 18);
		sp.decrementPile(RESOURCE.GOLD, 18);
		sp.decrementPile(RESOURCE.MOLASSES, 17);
		sp.decrementPile(RESOURCE.GOATS, 18);
		sp.decrementPile(RESOURCE.WOOD, 17);

		int expected = 0;
		int actualCutlass = p1.getPlayerPile().getPile().get(RESOURCE.CUTLASSES);
		int actualGold = p1.getPlayerPile().getPile().get(RESOURCE.GOLD);
		int actualGoats = p1.getPlayerPile().getPile().get(RESOURCE.GOATS);
		int actualMolasses = p1.getPlayerPile().getPile().get(RESOURCE.MOLASSES);
		int actualWood = p1.getPlayerPile().getPile().get(RESOURCE.WOOD);

		assertEquals("Cutlass resource should be empty for player",expected, actualCutlass);
		assertEquals("Gold resource should be empty for player",expected, actualGold);
		assertEquals("Goats resource should be empty for player",expected, actualGoats);
		assertEquals("Molasses resource should be empty for player",expected, actualMolasses);
		assertEquals("Wood resource should be empty for player",expected, actualWood);
		assertFalse("Stockpile should now not be empty, taken from player",sp.getPile().isEmpty());
	}
}
