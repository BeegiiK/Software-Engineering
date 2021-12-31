package test_cases;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import Controller.Board.MarketPlaceCtrl;
import Enum.COLOUR;
import Enum.RESOURCE;
import Model.Player.Player;
import View.Menu_PlayerActions;

public class Player_tradeMarketplace {
	@Test
	public void test_playerTrade_marketplaceOption_1() {
		Menu_PlayerActions g = new Menu_PlayerActions();
		Player p = new Player("Derek",COLOUR.WHITE);
		String s1 = "ghfj";
		String s2 = "2";
		String s3 = "1";
		
		boolean actual1 = g.marketplaceOptionSelect(s1,p);
		boolean actual2 = g.marketplaceOptionSelect(s2,p);
		boolean actual3 = g.marketplaceOptionSelect(s3,p);
		
		assertTrue("Check if invalid input in stockpile option screen returns true",actual1);
		assertTrue("Check if input 2 in stockpile option screen returns false",actual2);
		assertFalse("Check if input 1 in stockpile option screen returns false",actual3);
	}
	
	@Test
	public void test_playerTrade_marketplaceTrade_2() {
		MarketPlaceCtrl mp = MarketPlaceCtrl.getInstance();
		Player p = new Player("Derek",COLOUR.ORANGE);
		RESOURCE mp_desired = RESOURCE.CUTLASSES;
		RESOURCE mp_unwanted = RESOURCE.GOATS;
		
		mp.swap(p, mp_desired, mp_unwanted);
		ArrayList<RESOURCE> list = mp.getMarketPlaceResources();
		boolean actual_desired = false;
		boolean actual_unwanted = false;

		
		if(list.contains(mp_desired)) {
			actual_desired = true;
		}
		if(list.contains(mp_unwanted)) {
			actual_unwanted = true;
		}
		
		assertFalse("Check if the wanted resource by player is removed from marketplace",actual_desired);
		assertTrue("Check if the unwanted resource from player is in marketplace",actual_unwanted);
	}
	
	@Test
	public void test_playerTrade_marketplace_allSameResources_3() {
		MarketPlaceCtrl mp = MarketPlaceCtrl.getInstance();
		Player p = new Player("Derek",COLOUR.ORANGE);
		
		RESOURCE cutlass = RESOURCE.CUTLASSES;
		RESOURCE goats = RESOURCE.GOATS;
		RESOURCE wood = RESOURCE.WOOD;
		RESOURCE molasses = RESOURCE.MOLASSES;
		RESOURCE give = RESOURCE.GOLD;
		
		mp.swap(p, cutlass, give);
		mp.swap(p, goats, give);
		mp.swap(p, wood, give);
		mp.swap(p, molasses, give);
		
		if(mp.allSameResources()) { 
			mp.shuffle();
		}
		
		boolean b1 = mp.getMarketPlaceResources().contains(cutlass);
		boolean b2 = mp.getMarketPlaceResources().contains(goats);
		boolean b3 = mp.getMarketPlaceResources().contains(wood);
		boolean b4 = mp.getMarketPlaceResources().contains(molasses);
		boolean b5 = mp.getMarketPlaceResources().contains(give);

		assertTrue("Check marketplace if it contains a cutlass resource",b1);
		assertTrue("Check marketplace if it contains a goat resource",b2);
		assertTrue("Check marketplace if it contains a wood resource",b3);
		assertTrue("Check marketplace if it contains a molasses resource",b4);
		assertTrue("Check marketplace if it contains a gold resource",b5);
	}
	
	@Test
	public void test_playerTrade_marketplace_playerAlreadyTraded_4() {
		Menu_PlayerActions g = new Menu_PlayerActions();
		Player p = new Player("Derek",COLOUR.ORANGE);
		
		p.setTradedWithMarketPlace(true);
		boolean actual = g.proceedMarketPlaceTrade(p);
		
		assertFalse("Check if player can't trade with marketplace for a second time in the same turn",actual);
	}
	
	
}
