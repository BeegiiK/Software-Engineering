package map;

import java.util.ArrayList;

import Board.Stockpile;
import player.Player;

public class MarketPlace {
	
	private MarketPlace single_instance = null;
	private ArrayList<RESOURCE> stand = new ArrayList<RESOURCE>();
	
	private MarketPlace() {
		stand.add(0, RESOURCE.CUTLASSES);
		stand.add(1, RESOURCE.GOATS);
		stand.add(2, RESOURCE.GOLD);
		stand.add(3, RESOURCE.WOOD);
		stand.add(4, RESOURCE.MOLASSES);
		
		Stockpile S = Stockpile.getInstance();
		
		S.decrementPile(RESOURCE.CUTLASSES, 1);
		S.decrementPile(RESOURCE.WOOD, 1);
		S.decrementPile(RESOURCE.MOLASSES, 1);
		S.decrementPile(RESOURCE.GOLD, 1);
		S.decrementPile(RESOURCE.GOATS, 1);
		
	}
	
	public String toString(int id) {
		return stand.get(id).label;
	}
	
	public void swap(Player p, RESOURCE out, RESOURCE in) {
		
		stand.add(stand.indexOf(out), in);
	}
	
	//public ArrayList<RESOURCE> 
	
	

}
