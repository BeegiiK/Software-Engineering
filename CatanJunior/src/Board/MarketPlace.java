package Board;

import java.util.Hashtable;
import logistic.Resources;
import player.Player;

public class MarketPlace extends Trade{
	
	private String[] marketplace;
	
	// Constructor
	public MarketPlace(Hashtable<String, Integer> stockpile) {
		resetMarketPlace(stockpile);
	}
	
	// reset marketplace
	public void resetMarketPlace(Hashtable<String, Integer> stockpile) {
		int i = 0;
		for(Resources type : Resources.values()) {
			marketplace[i] = type.toString();
			stockpile.put(type.toString(), stockpile.get(type.toString()) - 1);
			i++;
		}
	}
	
	// Reset marketplace when condition is true
	public void QueryResetMarketPlace(Hashtable<String, Integer> stockpile) {
		String key = marketplace[0];
		stockpile.put(key, stockpile.get(key) + 5);
		
		resetMarketPlace(stockpile);
	}
	//need to check if marketplace has 5 of the same resources
	public boolean allElementsEquals(String[] arr) {
		int count = 0;
		for(int i=0; i<arr.length; i++) {
			if(arr[i] == arr[i+1]) {
				count++;
			}
		}
		if(count == 5) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//need to check if player has already traded once with marketplace
	public boolean isTraded(Player p) {
		return p.getTradedWithMarketPlace();
	}
	
	// Actual trading method between player and marketplace
	public void tradeWithMarketPlace(Player p, String desiredKey, String removedKey) {
		if(!isTraded(p)) {
			p.getPlayerResources().put(desiredKey, p.getPlayerResources().get(desiredKey) + 1);
			
			for(int i=0; i<5; i++) {
				if(marketplace[i] == desiredKey) {
					marketplace[i] = removedKey;
				}
			}
			
			if(allElementsEquals(marketplace)) {
				
			}
		}
		else {
			System.out.println("Player has already traded with the marketplace during their turn.");
		}
	}
}	

	
	



