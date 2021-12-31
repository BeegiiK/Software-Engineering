package test_cases;

import org.junit.*;
import static org.junit.Assert.*;

import Game.MoveGhostCaptain;
import Game.game;
import map.COLOUR;
import map.RESOURCE;
import map.TileCtrl;
import player.Player;
import player.PlayerCtrl;

public class Player_DiceRoll {
	@Test
	public void test_playerCheckDiceRollInput_1() {
		game g = game.getInstance();
		String i1 = "r";
		String i2 = "R";
		String i3 = "!!!!";
		
		assertTrue("Check if user input r rolls the die",g.checkDieRoll(i1));
		assertTrue("Check if user input R rolls the die",g.checkDieRoll(i2));
		assertFalse("Check if user input !!!! does not roll the die",g.checkDieRoll(i3));
	}
	
	@Test
	public void test_playerCheckDiceRoll_playerResourceAllocation_2() {
		game g = new game();
		Player p1 = new Player("Derek",COLOUR.WHITE);
		Player p2 = new Player("Larry",COLOUR.ORANGE);
		Player p3 = new Player("James",COLOUR.RED);

		PlayerCtrl Pl = PlayerCtrl.getInstance();

		Pl.addPlayer(p1);
		Pl.addPlayer(p2);
		Pl.addPlayer(p3);
		g.chooseStartingLocs();
		
		int die_result = 4;
		Pl.giveDiceResources(die_result);
		
		int expected_m = 2;
		int expected_c = 1;
		int actual_p1 = p1.getPlayerPile().getPile().get(RESOURCE.MOLASSES);
		int actual_p2 = p2.getPlayerPile().getPile().get(RESOURCE.CUTLASSES);
		int actual_p3 = p3.getPlayerPile().getPile().get(RESOURCE.MOLASSES);

		assertEquals("Check if white player got allocated 1 molasses from dice roll",expected_m,actual_p1);
		assertEquals("Check if orange player got allocated 1 cutlass from dice roll",expected_c,actual_p2);
		assertEquals("Check if red player got allocated 1 molasses from dice roll",expected_m,actual_p3);
	}
	
	@Test
	public void test_playerCheckDiceRoll_ghostPirateBlockAllocation_3() {
		game g = new game();
		Player p1 = new Player("Derek",COLOUR.WHITE);
		Player p2 = new Player("Larry",COLOUR.ORANGE);
		Player p3 = new Player("James",COLOUR.RED);
		PlayerCtrl Pl = PlayerCtrl.getInstance();
		MoveGhostCaptain moveUX = new MoveGhostCaptain();
		TileCtrl cont = TileCtrl.getInstance();

		Pl.addPlayer(p1);
		Pl.addPlayer(p2);
		Pl.addPlayer(p3);
		g.chooseStartingLocs();
		
		String tileLoc = "1";
		p3.setPlayerTurn(true);
		moveUX.move(tileLoc);
		
		int expected = 3;
		int actual = p3.getPlayerPile().getPile().get(RESOURCE.WOOD);
		int expectedLoc = 1;
		int actualLoc = cont.getCurrGhostCaptainLoc();
		assertEquals("Check if ghost captain location is at tile 1 as expected",expectedLoc,actualLoc);
		assertEquals("Check if moving ghost captain to tile 1 gives two wood resources to player",expected,actual);
		
		int die_result = 3;
		Pl.giveDiceResources(die_result);

		expected = 1;
		int actual_p1 = p1.getPlayerPile().getPile().get(RESOURCE.WOOD);
		int actual_p2 = p2.getPlayerPile().getPile().get(RESOURCE.WOOD);
		
		assertEquals("Check if ghost captain prevented resource allocation on dice roll",expected,actual_p1);
		assertEquals("Check if ghost captain prevented resource allocation on dice roll",expected,actual_p2);
	}
	
}
