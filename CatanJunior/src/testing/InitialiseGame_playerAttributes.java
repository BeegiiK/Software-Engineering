package testing;

import org.junit.*;
import static org.junit.Assert.*;

import Game.ColourHandling;
import Game.gameInitialisation;
import map.COLOUR;
import player.Player;
import player.PlayerCtrl;

public class InitialiseGame_playerAttributes {
	COLOUR expected_w = COLOUR.WHITE;
	COLOUR expected_o = COLOUR.ORANGE;
	COLOUR expected_r = COLOUR.RED;
	COLOUR expected_b = COLOUR.BLUE;
	
	@Test
	public void test_playerAttributes_playerName_unique_1() {
		gameInitialisation game = new gameInitialisation();
		PlayerCtrl Pl = PlayerCtrl.getInstance();
		
		assertTrue("Check if name Derek can be inputted",game.checkName("Derek"));
		
		Player player = new Player("Derek", expected_w);
		Pl.addPlayer(player);
		assertFalse("Check if name Derek cannot be another player name",game.checkName("Derek"));	
	}
	
	@Test
	public void test_playerAttributes_playerColour_letter_2() {
		gameInitialisation game = new gameInitialisation();
		ColourHandling c_h = new ColourHandling();
		String w = "w";
		String o = "o";
		String r = "r";
		String b = "b";
		
		assertEquals("Check if w chooses colour white",expected_w, game.convertColour(w,c_h));
		assertEquals("Check if o chooses colour orange",expected_o, game.convertColour(o,c_h));
		assertEquals("Check if r chooses colour red",expected_r, game.convertColour(r,c_h));
		assertEquals("Check if b chooses colour blue",expected_b, game.convertColour(b,c_h));
	}
	@Test
	public void test_playerAttributes_playerColour_uppercaseLetter_3() {
		gameInitialisation game = new gameInitialisation();
		ColourHandling c_h = new ColourHandling();
		String W = "W";
		String O = "O";
		String R = "R";
		String B = "B";
		
		assertEquals("Check if W chooses colour white",expected_w, game.convertColour(W,c_h));
		assertEquals("Check if O chooses colour orange",expected_o, game.convertColour(O,c_h));
		assertEquals("Check if R chooses colour red",expected_r, game.convertColour(R,c_h));
		assertEquals("Check if B chooses colour blue",expected_b, game.convertColour(B,c_h));
	}
	@Test
	public void test_playerAttributes_playerColour_incorrect_4() {
		gameInitialisation game = new gameInitialisation();
		ColourHandling c_h = new ColourHandling();
		String ww = "ww";
		String orange = "orange";
		String Red = "Red";
		String BLUE = "BLUE";
		
		assertNull("Check if ww is an invalid colour option",game.convertColour(ww,c_h));
		assertNull("Check if orange is an invalid colour option",game.convertColour(orange,c_h));
		assertNull("Check if Red is an invalid colour option",game.convertColour(Red,c_h));
		assertNull("Check if BLUE is an invalid colour option",game.convertColour(BLUE,c_h));
	}
	@Test
	public void test_playerAttributes_playerColour_sameColour_5() {
		gameInitialisation game = new gameInitialisation();
		ColourHandling c_h = new ColourHandling();
		String r = "r";
		
		assertEquals("Check if red can be chosen",expected_r, game.convertColour(r,c_h));
		assertNull("Check if red can't be chosen again",game.convertColour(r, c_h));
	}

}
