package Board;

import java.util.ArrayList;
import java.util.Hashtable;

import logistic.RESOURCE;
import pile.Pile;
import player.Player;
import Game.game;

public class Stockpile extends Pile{
	
	private static Stockpile single_instance = null;

	//when game is started, 18 of each resources is created
	public Stockpile() {
		for(RESOURCE type : RESOURCE.values()) {
			pile.put(type, 18);
		}
	}
	
	// trading with stock pile, as many times as they want. Need to check if desired key is greater than 1
	public boolean tradeWithStockpile(Player p, RESOURCE desiredKey, RESOURCE unwantedKey1, RESOURCE unwantedKey2) {
		if(checkIfSameTwoCards(unwantedKey1, unwantedKey2)) {
			if(pile.get(desiredKey) >= 1) {
				incrementPile(unwantedKey1, 2);
				p.getPlayerPile().decrementPile(unwantedKey1, 2);
				p.getPlayerPile().incrementPile(desiredKey, 1);
				return true;
			}
		}
			System.out.println("You need to give two cards of the same reosurce type.");
			return false;
	}
	
	public boolean checkIfSameTwoCards(RESOURCE key1, RESOURCE key2) {
		if(key1 == key2) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//Check if stock pile supply is run out of 1 type
	public void checkSupply(ArrayList<Player> listP) {
		for(RESOURCE type : RESOURCE.values()) {
			int count = 0;
			if(pile.get(type) == 0) {
				for(int i=0; i<listP.size(); i++) {
					count += listP.get(i).getPlayerPile().getPile().get(type);
					listP.get(i).getPlayerPile().getPile().put(type, 0);
				}
			}
			pile.put(type, count);
		}
	}
	
	// method for giving out resources depending on map positions
	public void
	

	@Override
	public String toString() {
		return "Stockpile [stockPile=" + pile + "]";
	}
	
	public static Stockpile getInstance() {
		if(single_instance == null) {
			single_instance = new Stockpile();
		}
		
		return single_instance;
	}
}
