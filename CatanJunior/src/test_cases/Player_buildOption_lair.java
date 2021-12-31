package test_cases;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Controller.Player.PlayerCtrl;
import Enum.COLOUR;
import Enum.RESOURCE;
import Model.Player.Player;
import View.GameComponents.Menu_Build;

public class Player_buildOption_lair {
	@Test
	public void test_playerBuild_LairOption_1() {
		Player p = new Player("Derek",COLOUR.BLUE);
		PlayerCtrl ctrl = PlayerCtrl.getInstance();
		Menu_Build bui = new Menu_Build();
	
		p.setPlayerTurn(true);
		ctrl.addPlayer(p);
		
		String s1 = "N";
		String s2 = "!_";
		
		boolean actual1 = bui.checkLairInput(s1);
		boolean actual2 = bui.checkLairInput(s2);
		
		assertFalse("Check if input N returns false",actual1);
		assertTrue("Check if input !_ returns true and print error message",actual2);
	}
	@Test
	public void test_playerBuild_notEnoughResources_BuyLair_2() {
		Player p = new Player("Derek",COLOUR.BLUE);
		PlayerCtrl ctrl = PlayerCtrl.getInstance();
		Menu_Build bui = new Menu_Build();
		
		p.setPlayerTurn(true);
		ctrl.addPlayer(p);
		
		boolean actual1 = bui.buyLairInput();
		assertTrue("Check if player has enough resources to purchase lair",actual1);
	}
	@Test
	public void test_playerBuild_noAvailableLairs_3() {
		Player p = new Player("Derek",COLOUR.WHITE);
		PlayerCtrl ctrl = PlayerCtrl.getInstance();
		Menu_Build bui = new Menu_Build();

		p.setPlayerTurn(true);
		ctrl.addPlayer(p);
		
		p.getPlayerPile().incrementPile(RESOURCE.CUTLASSES, 1);
		p.getPlayerPile().incrementPile(RESOURCE.GOATS, 1);
		
		bui.getLairLocations("6");
		bui.getLairLocations("24");
		
		boolean actual1 = bui.checkAvailableLairs();
		assertFalse("Check if player returns false if no available lair locations exist",actual1);
	}
}
