package test_cases;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Controller.Player.PlayerCtrl;
import Enum.COCOTILE_TYPES;
import Enum.COLOUR;
import Enum.RESOURCE;
import Model.Player.Player;
import View.GameComponents.Menu_CocoTiles;

public class Player_buyCocotile {
	@Test
	public void test_navigateTurnInput_BuyCocoTile_checkPiles_1() {
		Menu_CocoTiles ct = new Menu_CocoTiles();
		Player p = new Player("Derek",COLOUR.ORANGE);
		PlayerCtrl Pl = PlayerCtrl.getInstance();
		Pl.addPlayer(p);
		
		String actual = ct.confirmedBuyCocoTile(p);
		String expected = null;
		
		assertEquals("Check if user can't buy a cocotile when not enough resources",expected,actual);
	}
	
	@Test
	public void test_navigateTurnInput_BuyCocoTile_2() {
		Menu_CocoTiles ct = new Menu_CocoTiles();
		Player p = new Player("Derek",COLOUR.ORANGE);
		PlayerCtrl Pl = PlayerCtrl.getInstance();
		Pl.addPlayer(p);

		int expectedBefore = 0;
		int expectedAfter = 1;
		
		assertEquals("Check if player used cocotiles is empty",expectedBefore, p.getUsedCocoTiles().size());

		p.getPlayerPile().incrementPile(RESOURCE.CUTLASSES, 1);
		p.getPlayerPile().incrementPile(RESOURCE.GOLD, 1);
		ct.confirmedBuyCocoTile(p);
		
		assertEquals("Check if player used cocotiles is 1 after purchase",expectedAfter,p.getUsedCocoTiles().size());
		
		int res1 = p.getPlayerPile().getPile().get(RESOURCE.CUTLASSES);
		int res2 = p.getPlayerPile().getPile().get(RESOURCE.MOLASSES);
		int res3 = p.getPlayerPile().getPile().get(RESOURCE.GOLD);

		int expected = 0;
		assertEquals("Check if the player resource cutlass is empty after purchase",expected,res1);
		assertEquals("Check if the player resource molasses is empty after purchase",expected,res2);
		assertEquals("Check if the player resource gold is empty after purchase",expected,res3);
	}
	
	@Test
	public void test_navigateTurnInput_GoatCutlassTile_3() {
		String cocotiles = COCOTILE_TYPES.GOAT_CUTLASSES.toString();
		Player p = new Player("Derek",COLOUR.ORANGE);
		Menu_CocoTiles ct = new Menu_CocoTiles();
		int expected = 2;
		
		ct.takeAction(cocotiles,p);
		
		int actual1 = p.getPlayerPile().getPile().get(RESOURCE.GOATS);	
		int actual2 = p.getPlayerPile().getPile().get(RESOURCE.CUTLASSES);	
		assertEquals("Check if the player resource goat increased by two after this cocotile",expected,actual1);
		assertEquals("Check if the player resource cutlass increased by two after this cocotile",expected,actual2);
	}
	
	@Test
	public void test_navigateTurnInput_MolassesWoodTile_4() {
		String cocotiles = COCOTILE_TYPES.MOLASSES_WOOD.toString();
		Player p = new Player("Derek",COLOUR.ORANGE);
		Menu_CocoTiles ct = new Menu_CocoTiles();
		int expected = 3;
		
		ct.takeAction(cocotiles,p);
		
		int actual1 = p.getPlayerPile().getPile().get(RESOURCE.MOLASSES);	
		int actual2 = p.getPlayerPile().getPile().get(RESOURCE.WOOD);		
		assertEquals("Check if the player resource molasses increased by two after this cocotile",expected,actual1);
		assertEquals("Check if the player resource wood increased by two after this cocotile",expected,actual2);
	}
}
