package Board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import logistic.Resources;
import logistic.typesOfCocoTiles;
import player.Player;
import Game.game;

public class CocoTiles extends Stockpile {
	
	private Stack<String> cocoTiles = new Stack<String>();
	
	public CocoTiles() {
		for(int i = 0; i<11; i++) {
			cocoTiles.add(typesOfCocoTiles.MOVEGHOSTPIRATE.toString());
		}
		for(int j=0; j<3; j++) {
			cocoTiles.add(typesOfCocoTiles.SHIP_PRIATE.toString());
			cocoTiles.add(typesOfCocoTiles.GOAT_CUTLASSES.toString());
			cocoTiles.add(typesOfCocoTiles.MOLASSES_WOOD.toString());
		}
		shuffle();
	}
	
	public void shuffle() {
		Collections.shuffle(cocoTiles);
	}
	
	public String getCocoTile() {
		return cocoTiles.pop();
	}
	
	public void takeAction(Player p, String tile) {
		if(tile == typesOfCocoTiles.GOAT_CUTLASSES.toString()) {
			p.getPlayerResources().put(Resources.GOATS.toString(), p.getPlayerResources().get(Resources.GOATS) + 2);
			p.getPlayerResources().put(Resources.CUTLASSES.toString(), p.getPlayerResources().get(Resources.CUTLASSES) + 2);
			
			// need to check if this will make a resource zero
			stockPile.put(Resources.GOATS.toString(), stockPile.get(Resources.GOATS) - 2);
			stockPile.put(Resources.CUTLASSES.toString(), stockPile.get(Resources.CUTLASSES) - 2);
		}
		else if(tile == typesOfCocoTiles.MOLASSES_WOOD.toString()) {
			p.getPlayerResources().put(Resources.MOLASSES.toString(), p.getPlayerResources().get(Resources.MOLASSES) + 2);
			p.getPlayerResources().put(Resources.WOOD.toString(), p.getPlayerResources().get(Resources.WOOD) + 2);
			
			// need to check if this will make a resource zero
			stockPile.put(Resources.MOLASSES.toString(), stockPile.get(Resources.MOLASSES) - 2);
			stockPile.put(Resources.GOATS.toString(), stockPile.get(Resources.GOATS) - 2);
		}
		else {
			// move ghost captain
		}
	}
	
	// a method to check if the cocotile will disrupt the stockpile, i.e. 0 or 1 for a resource
	
	public void checkForMostCocoTile(Player p) {
		int count = 0;
		ArrayList<Player> list = returnAllPlayers();
		for(Player people : list) {
			if(people.getPlayerName() != p.getPlayerName()) {
				if(p.getUsedCocoTiles().size() > people.getUsedCocoTiles().size()) {
					count++;
				}
			}
		}
		if(count == list.size()-1) {
			p.setLeading(true);
			// need to modify the ghost captain priority
		}
		else {
			p.setLeading(false);
			// need to modify the ghost captain priority
		}	
	}

}
