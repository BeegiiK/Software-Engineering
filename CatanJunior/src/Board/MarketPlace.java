package Board;

import java.util.Arrays;

import logistic.Resources;
import player.Player;

public class MarketPlace extends Trade{
	
	private String[] marketplace = new String[5];
	
	// Constructor
	public MarketPlace(Stockpile stock) {
		resetMarketPlace(stock);
	}
	
	// getter
	public String[] getMarketplace() {
		return this.marketplace;
	}
	
	//change marketplace material
	public void updateMarketPlace(int i, String key) {
		marketplace[i] = key;
	}
	
	public String seeMarketPlace(int i) {
		return marketplace[i];
	}
	
	// reset marketplace
	public void resetMarketPlace(Stockpile stock) {
		int i = 0;
		for(Resources type : Resources.values()) {
			updateMarketPlace(i,type.toString());
			stock.decrementStockpile(type.toString(), 1);
			i++;
		}
	}
	
	// Reset marketplace when condition is true
	public void QueryResetMarketPlace(Stockpile stockpile) {
		if(allElementsEquals(getMarketplace())) {
			String key = marketplace[0];
			stockpile.incrementStockPile(key, 5);
			
			resetMarketPlace(stockpile);
		}
	}
	//need to check if marketplace has 5 of the same resources
	public boolean allElementsEquals(String[] arr) {
		int count = 0;
		for(int i=0; i<arr.length-1; i++) {
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
	public void tradeWithMarketPlace(Player p, String desiredKey, String removedKey, Stockpile stock) {
		if(!isTraded(p)) {
			p.incrementResource(desiredKey, 1);
			
			for(int i=0; i<5; i++) {
				if(seeMarketPlace(i) == desiredKey) {
					updateMarketPlace(i, removedKey);
				}
			}
			
			QueryResetMarketPlace(stock);
		}
		else {
			System.out.println("Player has already traded with the marketplace during their turn.");
		}
	}

	@Override
	public String toString() {
		return "MarketPlace [marketplace=" + Arrays.toString(marketplace) + "]";
	}
	
	
}	

	
	



