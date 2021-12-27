package Board;

import java.util.Arrays;

import logistic.RESOURCE;
import pile.Pile;
import player.Player;

public class MarketPlace_0 extends Pile{
	
	// Constructor
	public MarketPlace_0(Stockpile stock) {
		resetMarketPlace(stock);
	}
	
	// reset marketplace
	public void resetMarketPlace(Stockpile stock) {
		for(RESOURCE type : RESOURCE.values()) {
			incrementPile(type, 1);
			stock.decrementPile(type, 1);
		}
	}
		
	// Reset marketplace when condition is true
	public void QueryResetMarketPlace(Stockpile stockpile) {
		if(allElementsEquals()) {
			stockpile.incrementPile(checkResourcesForMax(), 5);
			decrementPile(checkResourcesForMax(), 5);
			for(RESOURCE r: RESOURCE.values()) {
				incrementPile(r,1);
			}
		}
	}
	
	//returns if resource has 5 else return null
	public RESOURCE checkResourcesForMax() {
		for(RESOURCE r: RESOURCE.values()) {
			if(getPile().get(r) == 5) {
				return r;
			}
		}
		return null;
	}
	
	//need to check if marketplace has 5 of the same resources
	public boolean allElementsEquals() {
		if(checkResourcesForMax() != null) {
			return true;
		}
		return false;
	}
	
	//need to check if player has already traded once with marketplace
	public boolean isTraded(Player p) {
		return p.getTradedWithMarketPlace();
	}
	
	// Actual trading method between player and marketplace
	public boolean tradeWithMarketPlace(Player p, RESOURCE desiredKey, RESOURCE removedKey, Stockpile stock) {
		if(!isTraded(p)) {
			p.getPlayerPile().incrementPile(desiredKey, 1);
			decrementPile(desiredKey, 1);
			incrementPile(removedKey, 1);
			
			QueryResetMarketPlace(stock);
			p.setTradedWithMarketPlace(true);
			return true;
		}
		else {
			System.out.println("Player has already traded with the marketplace during their turn.");
			return false;
		}
	}

	@Override
	public String toString() {
		return "MarketPlace [marketplace=" + (getPile()) + "]";
	}
	
	
}	

	
	



