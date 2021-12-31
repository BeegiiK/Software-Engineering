package Model.ResourcePile;

import Enum.RESOURCE;

public class PlayerPile extends Pile{
	
	// Constructor, player resource pile will have one wood and molasses resource
	// for initialisation
	public PlayerPile() {
		super();
		Stockpile stockpile = Stockpile.getInstance();
		stockpile.decrementPile(RESOURCE.WOOD, 1);
		stockpile.decrementPile(RESOURCE.MOLASSES, 1);
		incrementPile(RESOURCE.WOOD, 1);
		incrementPile(RESOURCE.MOLASSES, 1);
	}

}
