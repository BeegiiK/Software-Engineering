package Model.ResourcePile;

import Enum.RESOURCE;

public class PlayerPile extends Pile{
	
	private Stockpile stockpile = Stockpile.getInstance();

	public PlayerPile() {
		super();
		stockpile.decrementPile(RESOURCE.WOOD, 1);
		stockpile.decrementPile(RESOURCE.MOLASSES, 1);
		incrementPile(RESOURCE.WOOD, 1);
		incrementPile(RESOURCE.MOLASSES, 1);
	}

}
