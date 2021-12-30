package testing;

import org.junit.*;
import static org.junit.Assert.*;
import Game.game;

public class playerDiceRoll {
	@Test
	public void test_playerCheckDiceRollInput() {
		game g = game.getInstance();
		String i1 = "r";
		String i2 = "R";
		String i3 = "!!!!";
		
		assertTrue("Check if user input r rolls the die",g.checkDieRoll(i1));
		assertTrue("Check if user input R rolls the die",g.checkDieRoll(i2));
		assertFalse("Check if user input !!!! does not roll the die",g.checkDieRoll(i3));
	}
	

}
