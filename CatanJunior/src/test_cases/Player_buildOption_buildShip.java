package test_cases;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Controller.Player.PlayerCtrl;
import Enum.COLOUR;
import Enum.RESOURCE;
import Model.Player.Inventory;
import Model.Player.Player;
import View.GameComponents.Menu_Build;

public class Player_buildOption_buildShip {
	@Test
	public void test_playerBuild_buildShip_1() {
		Player p = new Player("Derek",COLOUR.WHITE);
		PlayerCtrl ctrl = PlayerCtrl.getInstance();
		Menu_Build bui = new Menu_Build();
		
		p.setPlayerTurn(true);
		ctrl.addPlayer(p);
		
		p.getPlayerPile().incrementPile(RESOURCE.GOATS, 1);
		bui.purchaseInventory("ship");
		bui.getShipLocations("3");
		
		int expected = 0;
		int res1 = p.getPlayerPile().getPile().get(RESOURCE.GOATS);
		int res2 = p.getPlayerPile().getPile().get(RESOURCE.WOOD);
	
		int expectedShip = 7;
		int actual = p.getInventory().get(Inventory.SHIP);

		assertEquals("Check if the goat resource is empty after building ship",expected,res1);
		assertEquals("Check if the wood resource is empty after building ship",expected,res2);
		assertEquals("Check if the ship inventory decreased by one after building",expectedShip, actual);
	}
}
