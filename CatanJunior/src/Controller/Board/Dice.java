package Controller.Board;

import java.util.Random;

public class Dice {
	
	// Roll dice using a randomn number generator and return result
	public int roll() {
		Random rand = new Random();
		return rand.nextInt(6) + 1;
	}
}
