package Board;

import java.util.ArrayList;
import java.util.Collections;

import logistic.Inventory;
import logistic.Resources;
import player.Player;

public class BuildingCostsTile extends CocoTiles{
	
	//Buying a lair
	public void buyLair(Player p) {
		ArrayList<Resources> requiredResources = new ArrayList<Resources>();
		Collections.addAll(requiredResources,Resources.CUTLASSES, Resources.MOLASSES, Resources.GOATS, Resources.WOOD);
		
		if(checkIfPlayerHas(p, requiredResources)) {
			for(Resources r : requiredResources) {
				p.getPlayerResources().put(r.toString(), p.getPlayerResources().get(r) - 1);
				stockPile.put(r.toString(), stockPile.get(r) + 1);
			}
			p.getPlayerInventory().put(Inventory.LAIR.toString(), p.getPlayerInventory().get(Inventory.LAIR) + 1);
			//need to update something here
		}
		else {
			System.out.println("Player does not have enough resources to buy a lair");
		}
	}
	
	// Buying a Ship
	public void buyShip(Player p) {
		ArrayList<Resources> requiredResources = new ArrayList<Resources>();
		Collections.addAll(requiredResources,Resources.GOATS, Resources.WOOD);
		
		if(checkIfPlayerHas(p, requiredResources)) {
			for(Resources r : requiredResources) {
				p.getPlayerResources().put(r.toString(), p.getPlayerResources().get(r) - 1);
				stockPile.put(r.toString(), stockPile.get(r) + 1);
			}
			p.getPlayerInventory().put(Inventory.SHIP.toString(), p.getPlayerInventory().get(Inventory.SHIP) + 1);
		}
		else {
			System.out.println("Player does not have enough resources to buy a ship");
		}
	}
	
	// Buying a cocoTile
	public void buyCocoTile(Player p) {
		ArrayList<Resources> requiredResources = new ArrayList<Resources>();
		Collections.addAll(requiredResources,Resources.CUTLASSES, Resources.MOLASSES, Resources.GOLD);
		
		if(checkIfPlayerHas(p, requiredResources)) {
			for(Resources r : requiredResources) {
				p.getPlayerResources().put(r.toString(), p.getPlayerResources().get(r) - 1);
				stockPile.put(r.toString(), stockPile.get(r) + 1);
			}
			takeAction(p, getCocoTile());
			p.getUsedCocoTiles().add(getCocoTile());
			checkForMostCocoTile(p);
			
		}
		else {
			System.out.println("Player does not have enough resources to buy a ship");
		}
	}

	// checks if player has the right requirements to buy an element
	public boolean checkIfPlayerHas(Player p,  ArrayList<Resources> list) {
		for(Resources r : list) {
			if(p.getPlayerResources().get(r) == 0) {
				return false;
			}
		}
		return true;	
	}

}
