package test_cases;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Controller.Player.PlayerCtrl;
import Enum.COLOUR;
import Enum.RESOURCE;
import Model.Player.Player;
import Model.Player.Inventory;
import View.Menu_PlayerActions;
import View.GameComponents.Menu_CocoTiles;

public class Player_checkEOG {
	@Test
	public void test_playerEOG_check_1() {
		Player p = new Player("Derek",COLOUR.BLUE);
		PlayerCtrl pl = PlayerCtrl.getInstance();
		Menu_CocoTiles ct = new Menu_CocoTiles();
		Menu_PlayerActions g = new Menu_PlayerActions();

		
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
		Menu_PlayerActions g = new Menu_PlayerActions();

		
		p.setPlayerTurn(true);
		pl.addPlayer(p);
		p.getInventory().put(Inventory.LAIR, 0);
		
		boolean actual = g.checkForEOG();
		
		assertTrue("Check that the EOG has reached after all lairs are played on the board",actual);
	}
}
