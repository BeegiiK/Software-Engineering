package test_cases;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Controller.Player.PlayerCtrl;
import Enum.COLOUR;
import Enum.RESOURCE;
import Model.Player.Player;
import View.Menu_PlayerActions;
import View.GameComponents.Menu_CocoTiles;

public class Player_buyCocotile_mostCoco {
	@Test
	public void test_mostCoco_5() {
		Menu_CocoTiles ct = new Menu_CocoTiles();
		Player p1 = new Player("Derek",COLOUR.ORANGE);
		Player p2 = new Player("Larry",COLOUR.RED);
		PlayerCtrl Pl = PlayerCtrl.getInstance();
		Menu_PlayerActions g = new Menu_PlayerActions();
		
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
		System.out.println(player1);
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
