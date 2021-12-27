package map;

import java.util.ArrayList;

import Board.Stockpile;
import player.Player;

public class MarketPlace {
	
	private static MarketPlace single_instance = null;
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
	
	public static MarketPlace getInstance() {
		if(single_instance == null) {
			single_instance = new MarketPlace();
		}
		
		return single_instance;
	}
	
	public void swap(Player p, RESOURCE out, RESOURCE in) {

		p.getPlayerPile().decrementPile(in, 1);
		p.getPlayerPile().incrementPile(out, 1);
		stand.add(stand.indexOf(out), in);
	}
	
	public ArrayList<String> getMarketPlaceResources(){
		ArrayList<String> A = new ArrayList<String>();
		for(int i = 0; i < stand.size(); i = i+1) {
			if(!stand.contains(stand.get(i).label)) {
				A.add(stand.get(i).label);
			}
		}
		return A;
	}
	
	public String getMPlabel(int id) {
		return ("M"+id+" "+stand.get(id-1).label);
	}
	
	//public ArrayList<RESOURCE> 
	
	

}
