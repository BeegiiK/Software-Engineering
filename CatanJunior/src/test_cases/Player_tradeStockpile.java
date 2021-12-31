package test_cases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import Enum.COLOUR;
import Enum.RESOURCE;
import Model.Player.Player;
import Model.ResourcePile.Stockpile;
import View.Menu_PlayerActions;

public class Player_tradeStockpile {
	@Test
	public void test_playerTrade_stockpileOption_invalidEntry_1() {
		Menu_PlayerActions g = new Menu_PlayerActions();
		Player p = new Player("Derek",COLOUR.WHITE);
		String s1 = "ghfj";
		String s2 = "2";
		String s3 = "1";
		
		boolean actual1 = g.stockpileOptionSelect(s1,p);
		boolean actual2 = g.stockpileOptionSelect(s2,p);
		boolean actual3 = g.stockpileOptionSelect(s3,p);
		
		assertTrue("Check if invalid input in stockpile option screen returns true",actual1);
		assertTrue("Check if input 2 in stockpile option screen returns false",actual2);
		assertFalse("Check if input 1 in stockpile option screen returns false",actual3);
	}
	
	@Test
	public void test_playerTrade_stockpileOption_noValidResources_2() {
		Menu_PlayerActions g = new Menu_PlayerActions();
		Player p = new Player("Derek",COLOUR.BLUE);
		
		int actual = g.checkResourceForTwo(p);
		int expected = 0;
		
		assertEquals("Check if player resources with less than two cannot trade with stockpile",expected,actual);
	}
	
	@Test
	public void test_playerTrade_stockpileOption_invalidUnwantedResourceOption_3() {
		Menu_PlayerActions g = new Menu_PlayerActions();
		ArrayList<RESOURCE> i = new ArrayList<RESOURCE>();
		i.add(RESOURCE.CUTLASSES);
		i.add(RESOURCE.GOATS);
		int j = i.size();
		
		String input1 = "1";
		String input2 = "300";
		
		RESOURCE actual1 = g.checkUnwantedResourceStockPile(j, i, input1);
		RESOURCE actual2 = g.checkUnwantedResourceStockPile(j, i, input2);
		
		assertNotNull("Check if chosen resource in in provided list of resources to give from player",actual1);
		assertNull("Check if input 300 is not in the provided list of resources to give from player",actual2);
	}
	
	@Test
	public void test_playerTrade_stockpileOption_tradeStockpile_4() {
		Player p = new Player("Derek",COLOUR.RED);
		p.getPlayerPile().incrementPile(RESOURCE.WOOD, 1);
		Stockpile stockpile = Stockpile.getInstance();
		
		RESOURCE unwanted = RESOURCE.WOOD;
		RESOURCE desired = RESOURCE.CUTLASSES;
		
		p.getPlayerPile().decrementPile(unwanted, 2);
		stockpile.incrementPile(unwanted, 2);
		stockpile.decrementPile(desired, 1);
		p.getPlayerPile().incrementPile(desired, 1);
		
		int expectedWood = 0;
		int expectedCutlass = 1;
		int actualWood = p.getPlayerPile().getPile().get(unwanted);
		int actualCutlass = p.getPlayerPile().getPile().get(desired);
		
		assertEquals("Check if wood resource decreased to two and added to stockpile",expectedWood,actualWood);
		assertEquals("Check if cutlass resource increased by one and taken from stockpile",expectedCutlass,actualCutlass);
	}
	
}
