package player;

import java.util.ArrayList;
import java.util.Hashtable;

import Board.Stockpile;
import map.RESOURCE;
import map.TileCtrl;
import map.Colour;
import map.GainsAmount;
import pile.Pile;

public class PlayerCtrl {
	
	private static PlayerCtrl single_instance = null;
	private ArrayList<Player> list = new ArrayList<Player>();

	public PlayerCtrl() {}
	
	public void addPlayer(Player p) {
		list.add(p);	
	}
	
	public Player getPlayer(Colour c) {
		for(Player p: list) {
			if(p.getColour().equals(c)) {
				return p;
			}
		}
		return null;
	}

	public void giveDiceResources(int die_result) {
		TileCtrl A = TileCtrl.getInstance();
		Stockpile stockpile = Stockpile.getInstance();
		
		GainsAmount map = new GainsAmount();
		map = A.getGainsAmount(die_result);
		
		if(checkStockpile(getTotalResources(map))) {
			for(Colour c: Colour.values()) {
				if(!c.equals(Colour.NONE)) {
					Player p = getPlayer(c);
					Pile pile = map.getPileforColour(c);
					
					for(RESOURCE r: pile.getPile().keySet()) {
						p.getPlayerPile().incrementPile(r, pile.getPile().get(r));
						stockpile.decrementPile(r, pile.getPile().get(r));
					}
				}
				
			}
		}
	}
	
	public Hashtable<RESOURCE, Integer> getTotalResources(GainsAmount map) {
		Hashtable<RESOURCE, Integer> required = null;
		for(Colour c: Colour.values()) {
			if(!c.equals(Colour.NONE)) {
				for(RESOURCE r: map.getPileforColour(c).getPile().keySet()) {
					required.put(r, required.get(r) + map.getPileforColour(c).getPile().get(r));
				}
			}
			
		}
		return required;
	}
	
	public boolean checkStockpile(Hashtable<RESOURCE, Integer> required) {
		Stockpile stockpile = Stockpile.getInstance();
		for(RESOURCE r: RESOURCE.values()) {
			if(!r.equals(RESOURCE.NONE)) {
				if(required.get(r) > stockpile.getPile().get(r)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static PlayerCtrl getInstance() {
		if(single_instance == null) {
			single_instance = new PlayerCtrl();
		}
		
		return single_instance;
	}


}
