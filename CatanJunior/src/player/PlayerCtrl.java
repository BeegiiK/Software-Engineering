package player;

import java.util.ArrayList;
import java.util.Hashtable;

import Board.Stockpile;
import map.RESOURCE;
import map.TileCtrl;
import map.COLOUR;
import map.GainsAmount;
import pile.Pile;

public class PlayerCtrl {
	
	private static PlayerCtrl single_instance = null;
	private ArrayList<Player> list = new ArrayList<Player>();

	public PlayerCtrl() {}
	
	public void addPlayer(Player p) {
		list.add(p);	
	}
	
	public Player getPlayer(COLOUR c) {
		for(Player p: list) {
			if(p.getColour().equals(c)) {
				return p;
			}
		}
		return null;
	}
	
	public ArrayList<COLOUR> getListOfColours(){
		ArrayList<COLOUR> c = new ArrayList<COLOUR>();
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

		for(COLOUR c: getListOfColours()) {
				Pile pile = map.getPileforColour(c);
				
				for(RESOURCE r: pile.getPile().keySet()) {
					stockpile.decrementPile(r, pile.getPile().get(r));
				}
		}
		
		for(COLOUR c: getListOfColours()) {
			Player p = getPlayer(c);
			Pile pile = map.getPileforColour(c);
			
			for(RESOURCE r: pile.getPile().keySet()) {
				p.getPlayerPile().incrementPile(r, pile.getPile().get(r));
				//System.out.println(r +" - "+ pile.getPile().get(r));
			}
		}
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
