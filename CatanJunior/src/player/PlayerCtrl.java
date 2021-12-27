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
	
	public ArrayList<Colour> getListOfColours(){
		ArrayList<Colour> c = new ArrayList<Colour>();
		for(Player p: list) {
			c.add(p.getColour());
		}
		return c;
	}

	public void giveDiceResources(int die_result) {
		TileCtrl A = TileCtrl.getInstance();
		Stockpile stockpile = Stockpile.getInstance();
		
		GainsAmount map = new GainsAmount();
		map = A.getGainsAmount(die_result);

		if(checkStockpile(getTotalResources(map))) {
			for(Colour c: getListOfColours()) {
					Player p = getPlayer(c);
					Pile pile = map.getPileforColour(c);
					
					for(RESOURCE r: pile.getPile().keySet()) {
						p.getPlayerPile().incrementPile(r, pile.getPile().get(r));
						stockpile.decrementPile(r, pile.getPile().get(r));
						System.out.println(r +" - "+ pile.getPile().get(r));
					}
			}
		}
	}
	
	public Pile getTotalResources(GainsAmount map) {
		Pile required = new Pile();
		for(Colour c: Colour.values()) {
			if(!c.equals(Colour.NONE)) {
				for(RESOURCE r: map.getPileforColour(c).getPile().keySet()) {
					required.incrementPile(r, map.getPileforColour(c).getPile().get(r));
				}
			}
		}
		return required;
	}
	
	public boolean checkStockpile(Pile pile) {
		Stockpile stockpile = Stockpile.getInstance();
		for(RESOURCE r: pile.getPile().keySet()) {
			if(pile.getPile().get(r) > stockpile.getPile().get(r)) {
				return false;
			}
		}
		return true;
	}
	
	public Player getActivePlayer() {
		for(Player p: list) {
			if(p.getPlayerTurn()) {
				return p;
			}
		}
		return null;
	}
	
	public boolean checkPlayerPile(Hashtable<RESOURCE, Integer> required) {
		Player p = getActivePlayer();
		for(RESOURCE r: required.keySet()) {
			if(required.get(r) > p.getPlayerPile().getPile().get(r)) {
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
	
	public int getNumofPlayers() {
		return list.size();
	}
	
	public ArrayList<Player> getPlayerList(){
		return list;
	}


}
