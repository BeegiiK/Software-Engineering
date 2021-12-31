package test_cases;

import org.junit.*;

import View.Menu_PlayerActions;
import View.GameSetup.Menu_CreateGame;

import static org.junit.Assert.*;


public class InitiliaseGame_numberOfPlayers {
	@Test
	public void numberOfPlayers_invalidKeys_1() {
		Menu_CreateGame game = new Menu_CreateGame();
		String invalidKey1 = " ";
		String invalidKey2 = "q";
		
		assertFalse("Checking for space key",game.validDigit(invalidKey1));
		assertFalse("Checking for character q",game.validDigit(invalidKey2));
	}
	
	@Test
	public void numberOfPlayers_withinLimits_2() {
		Menu_CreateGame game = new Menu_CreateGame();
		String three = "3";
		String four = "4";
		
		assertTrue("Checking for 3 players",game.validNumOfPlayers(three));
		assertTrue("Checking for 4 players",game.validNumOfPlayers(four));
	}
	
	@Test
	public void numberOfPlayers_outsideLimits_3() {
		Menu_CreateGame game = new Menu_CreateGame();
		String two = "2";
		String five = "5";
		String negativeOne = "-1";
		String zero = "0";
		
		assertFalse("Checking for 2 players",game.validNumOfPlayers(two));
		assertFalse("Checking for 5 players",game.validNumOfPlayers(five));
		assertFalse("Checking for -1 players",game.validNumOfPlayers(negativeOne));
		assertFalse("Checking for 0 players",game.validNumOfPlayers(zero));
	}
}
