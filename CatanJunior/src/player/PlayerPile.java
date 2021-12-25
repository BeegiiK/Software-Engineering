package player;

import Board.Stockpile;
import logistic.RESOURCE;
import pile.Pile;

public class PlayerPile extends Pile{
	
	private Stockpile stockpile = Stockpile.getInstance();

	public PlayerPile() {
		super();
		incrementPile(RESOURCE.WOOD, 1);
		incrementPile(RESOURCE.MOLASSES, 1);
		stockpile.decrementPile(RESOURCE.WOOD, 1);
		stockpile.decrementPile(RESOURCE.MOLASSES, 1);
	}

}