package test_cases;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Controller.Player.PlayerCtrl;
import Enum.COLOUR;
import Model.Player.Player;
import View.GameComponents.Menu_Build;

public class Player_buildOption_ship {
	@Test
	public void test_playerBuild_ShipOption_1() {
		Player p = new Player("Derek",COLOUR.BLUE);
		PlayerCtrl ctrl = PlayerCtrl.getInstance();
		Menu_Build bui = new Menu_Build();
	
		p.setPlayerTurn(true);
		ctrl.addPlayer(p);
		
		String s1 = "N";
		String s2 = "!_";
		
		boolean actual1 = bui.checkShipInput(s1);
		boolean actual2 = bui.checkShipInput(s2);
		
		assertFalse("Check if input N returns false",actual1);
		assertTrue("Check if input !_ returns true and print error message",actual2);
	}
	@Test
	public void test_playerBuild_notEnoughResources_BuyShip_2() {
		Player p = new Player("Derek",COLOUR.BLUE);
		PlayerCtrl ctrl = PlayerCtrl.getInstance();
		Menu_Build bui = new Menu_Build();
		
		p.setPlayerTurn(true);
		ctrl.addPlayer(p);
		
		boolean actual1 = bui.buyShipInput();
		assertTrue("Check if player has enough resources to purchase ship",actual1);
	}
	@Test
	public void test_playerBuild_noAvailableShip_3() {
		Player p = new Player("Derek",COLOUR.WHITE);
		PlayerCtrl ctrl = PlayerCtrl.getInstance();
		Menu_Build bui = new Menu_Build();

		p.setPlayerTurn(true);
		ctrl.addPlayer(p);
		
		bui.getShipLocations("3");
		bui.getShipLocations("27");
		
		boolean actual1 = bui.checkAvailableShips();
		assertFalse("Check if player returns false if no available ship locations exist",actual1);
	}
}
