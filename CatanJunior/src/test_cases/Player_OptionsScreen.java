package test_cases;

import org.junit.*;

import Enum.COLOUR;
import Model.Player.Player;
import View.Menu_PlayerActions;

import static org.junit.Assert.*;


public class Player_OptionsScreen {
	@Test
	public void test_validPlayerOption_EndTurnOption() {
		Menu_PlayerActions g = Menu_PlayerActions.getInstance();
		String s1 = "0";
		String s2 = " ";
		String s3 = "4";
		Player p = new Player("Derek",COLOUR.ORANGE);
		
		assertFalse("Checking if inputs other than 4 returns false",g.checkChosenOption(s1,p));
		assertFalse("Checking if inputs other than 4 returns false",g.checkChosenOption(s2,p));
		assertTrue("Checking if input 4 returns true to end player turn", g.checkChosenOption(s3,p));
	}
}
