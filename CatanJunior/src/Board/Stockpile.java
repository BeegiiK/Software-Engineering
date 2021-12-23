package Board;

import java.util.ArrayList;
import java.util.Hashtable;

import logistic.Resources;
import player.Player;
import Game.game;

public class Stockpile {
	
	private static Stockpile single_instance = null;

	private Hashtable<String, Integer> stockPile = new Hashtable<String, Integer>();

	//when game is started, 18 of each resources is created
	public Stockpile() {
		for(Resources type : Resources.values()) {
			stockPile.put(type.toString(), 18);
		}
	}
	
	// decrement/increment stock-pile
	public void decrementStockpile(String key, int value) {
		if(stockPile.get(key) < value) {
			System.out.println("Cannot decrement");
		}
		else {
			stockPile.put(key, stockPile.get(key) - value);
		}
	}
	
	public void incrementStockPile(String key, int value) {
		stockPile.put(key, stockPile.get(key) + value);
	}
	
	// trading with stock pile, as many times as they want. Need to check if desired key is greater than 1
	public void tradeWithStockpile(Player p, String desiredKey, String unwantedKey1, String unwantedKey2) {
		if(checkIfSameTwoCards(unwantedKey1, unwantedKey2)) {
			if(stockPile.get(desiredKey) >= 1) {
				
				stockPile.put(unwantedKey1, stockPile.get(unwantedKey1) + 1);
				stockPile.put(unwantedKey2, stockPile.get(unwantedKey2) + 1);
				
				p.decrementResource(unwantedKey1, 2);
				p.incrementResource(desiredKey, 1);
			}
		}
	}
	
	public boolean checkIfSameTwoCards(String key1, String key2) {
		if(key1 == key2) {
			return true;
		}
		else {
			return false;
		}
	}
	/*
	//Check if stock pile supply is run out of 1 type
	public void checkSupply() {
		for(Resources type : Resources.values()) {
			int count = 0;
			if(stockPile.get(type.toString()) == 0) {
				ArrayList<Player> list = returnAllPlayers();
				for(int i=0; i<list.size(); i++) {
					count += list.get(i).getPlayerResources().get(type.toString());
					list.get(i).getPlayerResources().put(type.toString(), 0);
				}
			}
			stockPile.put(type.toString(), count);
		}
	}

	@Override
	public String toString() {
		return "Stockpile [stockPile=" + stockPile + "]";
	}
	*/
	
	public Hashtable<String, Integer> getStockPile(){
		return this.stockPile;
	}
	
	public static Stockpile getInstance() {
		if(single_instance == null) {
			single_instance = new Stockpile();
		}
		
		return single_instance;
	}
}
