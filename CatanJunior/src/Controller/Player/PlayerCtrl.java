package Controller.Player;

import java.util.ArrayList;
import java.util.Hashtable;

import Controller.Board.TileCtrl;
import Enum.COLOUR;
import Enum.RESOURCE;
import Model.Player.Player;
import Model.ResourcePile.Pile;
import Model.ResourcePile.Stockpile;

public class PlayerCtrl {
	
	// Initialise singleton for player control
	private static PlayerCtrl single_instance = null;
	// List of players in the game instance
	private ArrayList<Player> list = new ArrayList<Player>();
	
	// Add new player to list
	public void addPlayer(Player p) {
		list.add(p);	
	}
	
	// Return player from colour attribute
	public Player getPlayer(COLOUR c) {
		for(Player p: list) {
			if(p.getColour().equals(c)) {
				return p;
			}
		}
		return null;
	}
	
	// Return arraylist of colours chosen by players 
	public ArrayList<COLOUR> getListOfColours(){
		ArrayList<COLOUR> c = new ArrayList<COLOUR>();
		for(Player p: list) {
			c.add(p.getColour());
		}
		return c;
	}

	// Assign dice resources according to a die roll number to players
	public void giveDiceResources(int die_result) {
		TileCtrl A = TileCtrl.getInstance();
		Stockpile stockpile = Stockpile.getInstance();
		
		GainsAmount map = new GainsAmount();
		map = A.getGainsAmount(die_result);
		
		// For each colour, take away resources from stockpile
		for(COLOUR c: getListOfColours()) {
				Pile pile = map.getPileforColour(c);
				
				for(RESOURCE r: pile.getPile().keySet()) {
					stockpile.decrementPile(r, pile.getPile().get(r));
				}
		}
		
		// For each colour, give resources to each player that was rewarded from dice roll
		for(COLOUR c: getListOfColours()) {
			Player p = getPlayer(c);
			Pile pile = map.getPileforColour(c);
			
			for(RESOURCE r: pile.getPile().keySet()) {
				p.getPlayerPile().incrementPile(r, pile.getPile().get(r));
			}
		}
	}
	
	// Get the current player who's turn is active
	public Player getActivePlayer() {
		for(Player p: list) {
			if(p.getPlayerTurn()) {
				return p;
			}
		}
		return null;
	}
	
	// Check if active player has enough resources to be decremented from required
	public boolean checkPlayerPile(Hashtable<RESOURCE, Integer> required) {
		Player p = getActivePlayer();
		for(RESOURCE r: required.keySet()) {
			if(required.get(r) > p.getPlayerPile().getPile().get(r)) {
				return false;
			}
		}
		return true;
	}
	
	// return instance of the player control
	public static PlayerCtrl getInstance() {
		if(single_instance == null) {
			single_instance = new PlayerCtrl();
		}
		
		return single_instance;
	}
	
	// return the total number of players
	public int getNumofPlayers() {
		return list.size();
	}
	
	// return list of players
	public ArrayList<Player> getPlayerList(){
		return list;
	}

}
