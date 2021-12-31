package test_cases;

import org.junit.*;
import static org.junit.Assert.*;

import Game.game;
import map.COLOUR;
import player.Player;


public class Player_OptionsScreen {
	@Test
	public void test_validPlayerOption_EndTurnOption() {
		game g = game.getInstance();
		String s1 = "0";
		String s2 = " ";
		String s3 = "4";
		Player p = new Player("Derek",COLOUR.ORANGE);
		
		assertFalse("Checking if inputs other than 4 returns false",g.checkChosenOption(s1,p));
		assertFalse("Checking if inputs other than 4 returns false",g.checkChosenOption(s2,p));
		assertTrue("Checking if input 4 returns true to end player turn", g.checkChosenOption(s3,p));
	}
}
