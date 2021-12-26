package player;

import java.util.ArrayList;
import java.util.Hashtable;

import Board.Stockpile;
import Game.game;
import logistic.Inventory;
import logistic.RESOURCE;
import map.Colour;
import pile.Pile;

public class PlayerCtrl {
	
	private static PlayerCtrl single_instance = null;
	private ArrayList<Player> list = new ArrayList<Player>();
	private Stockpile stockpile = Stockpile.getInstance();

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

	public void giveDiceResources(Hashtable<Colour, Pile> map, Hashtable<RESOURCE, Integer> required) {
		if(checkStockpile(required)) {
			for(Colour c: map.keySet()) {
				Player p = getPlayer(c);
				Pile pile = map.get(c);
				
				for(RESOURCE r: pile.getPile().keySet()) {
					p.getPlayerPile().incrementPile(r, pile.getPile().get(r));
				}
			}
		}
		
	}
	
	public boolean checkStockpile(Hashtable<RESOURCE, Integer> required) {
		for(RESOURCE r: RESOURCE.values()) {
			if(required.get(r) > stockpile.getPile().get(r)) {
				return false;
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
