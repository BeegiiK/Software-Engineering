package test_cases;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Controller.Player.PlayerCtrl;
import Enum.COLOUR;
import Enum.RESOURCE;
import Model.Player.Inventory;
import Model.Player.Player;
import View.GameComponents.Menu_Build;

public class Player_buildOption_buildLair {
	@Test
	public void test_playerBuild_buildLair_1() {
		Player p = new Player("Derek",COLOUR.WHITE);
		PlayerCtrl ctrl = PlayerCtrl.getInstance();
		Menu_Build bui = new Menu_Build();
		
		p.setPlayerTurn(true);
		ctrl.addPlayer(p);
		
		p.getPlayerPile().incrementPile(RESOURCE.CUTLASSES, 1);
		p.getPlayerPile().incrementPile(RESOURCE.GOATS, 1);
		bui.purchaseInventory("lair");
		bui.getLairLocations("6");
		
		int expected = 0;
		int res1 = p.getPlayerPile().getPile().get(RESOURCE.MOLASSES);
		int res2 = p.getPlayerPile().getPile().get(RESOURCE.WOOD);
		int res3 = p.getPlayerPile().getPile().get(RESOURCE.CUTLASSES);
		int res4 = p.getPlayerPile().getPile().get(RESOURCE.GOATS);
	
		int expectedLair = 6;
		int actual = p.getInventory().get(Inventory.LAIR);

		assertEquals("Check if the molasses resource is empty after building lair",expected,res1);
		assertEquals("Check if the wood resource is empty after building lair",expected,res2);
		assertEquals("Check if the cutlasses resource is empty after building lair",expected,res3);
		assertEquals("Check if the goats resource is empty after buidling lair",expected,res4);
		assertEquals("Check if lairs in player inventory decreased by one",expectedLair, actual);
	}
}
