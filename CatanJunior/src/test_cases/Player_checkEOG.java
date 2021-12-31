package test_cases;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Board.CocoTiles;
import Game.game;
import logistic.Inventory;
import map.COLOUR;
import map.RESOURCE;
import player.Player;
import player.PlayerCtrl;

public class Player_checkEOG {
	@Test
	public void test_playerEOG_check_1() {
		Player p = new Player("Derek",COLOUR.BLUE);
		PlayerCtrl pl = PlayerCtrl.getInstance();
		CocoTiles ct = new CocoTiles();
		game g = new game();

		
		p.setPlayerTurn(true);
		pl.addPlayer(p);
		p.getInventory().put(Inventory.LAIR, 1);
		
		boolean actual = g.checkForEOG();

		assertFalse("Check that EOG is not reached as one cocotile needs to be bought",actual);

		p.getPlayerPile().incrementPile(RESOURCE.CUTLASSES, 1);
		p.getPlayerPile().incrementPile(RESOURCE.GOLD, 1);
		ct.confirmedBuyCocoTile(p);
		g.MostCoco();
		
		actual = g.checkForEOG();
		
		assertTrue("Check that the EOG has reached after buying one cocotile with one lair left to play",actual);
	}
	
	@Test
	public void test_playerEOG_check_2() {
		Player p = new Player("Derek",COLOUR.BLUE);
		PlayerCtrl pl = PlayerCtrl.getInstance();
		game g = new game();

		
		p.setPlayerTurn(true);
		pl.addPlayer(p);
		p.getInventory().put(Inventory.LAIR, 0);
		
		boolean actual = g.checkForEOG();
		
		assertTrue("Check that the EOG has reached after all lairs are played on the board",actual);
	}
}
