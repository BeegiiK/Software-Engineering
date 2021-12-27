package player;

import Board.Stockpile;
import map.RESOURCE;
import pile.Pile;

public class PlayerPile extends Pile{
	
	private Stockpile stockpile = Stockpile.getInstance();

	public PlayerPile() {
		super();
		incrementPile(RESOURCE.WOOD, 1);
		incrementPile(RESOURCE.MOLASSES, 1);
		stockpile.decrementPile(RESOURCE.WOOD, 1);
		stockpile.decrementPile(RESOURCE.MOLASSES, 1);
		
		//remove after test
		incrementPile(RESOURCE.CUTLASSES, 2);
		incrementPile(RESOURCE.GOLD, 2);
		stockpile.decrementPile(RESOURCE.CUTLASSES, 2);
		stockpile.decrementPile(RESOURCE.GOLD, 2);
		incrementPile(RESOURCE.WOOD, 2);
		incrementPile(RESOURCE.MOLASSES, 2);
		stockpile.decrementPile(RESOURCE.WOOD, 2);
		stockpile.decrementPile(RESOURCE.MOLASSES, 2);
		incrementPile(RESOURCE.GOATS, 2);
		stockpile.decrementPile(RESOURCE.GOATS, 2);
	}

}
