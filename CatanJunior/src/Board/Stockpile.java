package Board;

import java.util.ArrayList;
import java.util.Hashtable;

import logistic.Resources;
import player.Player;
import Game.game;

public class Stockpile extends game{
	
	protected Hashtable<String, Integer> stockPile = new Hashtable<String, Integer>();

	//when game is started, 18 of each resources is created
	public Stockpile() {
		for(Resources type : Resources.values()) {
			stockPile.put(type.toString(), 18);
		}
	}
	
	// trading with stock pile, as many times as they want. Need to check if desired key is greater than 1
	public void tradeWithStockpile(Player p, String desiredKey, String unwantedKey1, String unwantedKey2) {
		if(checkIfSameTwoCards(unwantedKey1, unwantedKey2)) {
			if(stockPile.get(desiredKey) >= 1) {
				Hashtable<String, Integer> resource = p.getPlayerResources();
				
				stockPile.put(unwantedKey1, stockPile.get(unwantedKey1) + 1);
				stockPile.put(unwantedKey2, stockPile.get(unwantedKey2) + 1);
				
				resource.put(unwantedKey1, resource.get(unwantedKey2) - 1);
				resource.put(unwantedKey2, resource.get(unwantedKey2) - 1);
				resource.put(desiredKey, resource.get(desiredKey) + 1);
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
	
	//Check if stock pile supply is run out of 1 type
	public void checkSupply() {
		for(Resources type : Resources.values()) {
			int count = 0;
			if(stockPile.get(type) == 0) {
				ArrayList<Player> list = returnAllPlayers();
				for(int i=0; i<list.size(); i++) {
					count += list.get(i).getPlayerResources().get(type);
					list.get(i).getPlayerResources().put(type.toString(), null);
				}
			}
			stockPile.put(type.toString(), count);
		}
	}

}
