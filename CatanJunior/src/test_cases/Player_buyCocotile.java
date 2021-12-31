package test_cases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Board.CocoTiles;
import Game.game;
import logistic.typesOfCocoTiles;
import map.COLOUR;
import map.RESOURCE;
import player.Player;
import player.PlayerCtrl;

public class Player_buyCocotile {
	@Test
	public void test_navigateTurnInput_BuyCocoTile_checkPiles_1() {
		CocoTiles ct = new CocoTiles();
		Player p = new Player("Derek",COLOUR.ORANGE);
		PlayerCtrl Pl = PlayerCtrl.getInstance();
		Pl.addPlayer(p);
		
		String actual = ct.confirmedBuyCocoTile(p);
		String expected = null;
		
		assertEquals("Check if user can't buy a cocotile when not enough resources",expected,actual);
	}
	
	@Test
	public void test_navigateTurnInput_BuyCocoTile_2() {
		CocoTiles ct = new CocoTiles();
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
		String cocotiles = typesOfCocoTiles.GOAT_CUTLASSES.toString();
		Player p = new Player("Derek",COLOUR.ORANGE);
		CocoTiles ct = new CocoTiles();
		int expected = 2;
		
		ct.takeAction(cocotiles,p);
		
		int actual1 = p.getPlayerPile().getPile().get(RESOURCE.GOATS);	
		int actual2 = p.getPlayerPile().getPile().get(RESOURCE.CUTLASSES);	
		assertEquals("Check if the player resource goat increased by two after this cocotile",expected,actual1);
		assertEquals("Check if the player resource cutlass increased by two after this cocotile",expected,actual2);
	}
	
	@Test
	public void test_navigateTurnInput_MolassesWoodTile_4() {
		String cocotiles = typesOfCocoTiles.MOLASSES_WOOD.toString();
		Player p = new Player("Derek",COLOUR.ORANGE);
		CocoTiles ct = new CocoTiles();
		int expected = 3;
		
		ct.takeAction(cocotiles,p);
		
		int actual1 = p.getPlayerPile().getPile().get(RESOURCE.MOLASSES);	
		int actual2 = p.getPlayerPile().getPile().get(RESOURCE.WOOD);		
		assertEquals("Check if the player resource molasses increased by two after this cocotile",expected,actual1);
		assertEquals("Check if the player resource wood increased by two after this cocotile",expected,actual2);
	}
	
	@Test
	public void test_mostCoco_5() {
		CocoTiles ct = new CocoTiles();
		Player p1 = new Player("Derek",COLOUR.ORANGE);
		Player p2 = new Player("Larry",COLOUR.RED);
		PlayerCtrl Pl = PlayerCtrl.getInstance();
		game g = new game();
		
		p1.getPlayerPile().incrementPile(RESOURCE.CUTLASSES, 1);
		p1.getPlayerPile().incrementPile(RESOURCE.GOLD, 1);
		p2.getPlayerPile().incrementPile(RESOURCE.CUTLASSES, 1);
		p2.getPlayerPile().incrementPile(RESOURCE.GOLD, 1);
		
		Pl.addPlayer(p1);
		Pl.addPlayer(p2);
		g.MostCoco();
		
		boolean player1 = p1.getLeading();
		boolean player2 = p2.getLeading();
		assertFalse("Check if player 1 is not leading with most cocotiles",player1);
		assertFalse("Check if player 2 is not leading with most cocotiles",player2);
		
		ct.confirmedBuyCocoTile(p1);
		g.MostCoco();
		player1 = p1.getLeading();
		player2 = p2.getLeading();
		assertTrue("Check if player 1 is leading with most cocotiles since cocotile was bought",player1);
		assertFalse("Check if player 2 is not leading with most cocotiles",player2);

		ct.confirmedBuyCocoTile(p2);
		g.MostCoco();
		player1 = p1.getLeading();
		player2 = p2.getLeading();
		assertFalse("Check if player 1 is not leading since a tie for most cocotiles exist",player1);
		assertFalse("Check if player 2 is not leading since a tie for most cocotiles exist",player2);
	}
}
