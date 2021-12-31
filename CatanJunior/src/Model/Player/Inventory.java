package Model.Player;

import java.util.Hashtable;

import Enum.RESOURCE;

public enum Inventory {
	// Ship and Lair inventory types for a player
	SHIP,
	LAIR;

	// Return the resource cost for an inventory in order for a player to build 
	public static Hashtable<RESOURCE, Integer> cost(Inventory i){
		Hashtable<RESOURCE, Integer> cost = new Hashtable<RESOURCE, Integer>();
		
		// Lair cost
		if(i.equals(Inventory.LAIR)) {
			cost.put(RESOURCE.CUTLASSES, 1);
			cost.put(RESOURCE.MOLASSES, 1);
			cost.put(RESOURCE.GOATS, 1);
			cost.put(RESOURCE.WOOD, 1);		
			
			return cost;
		}
		// Ship cost 
		else {
			cost.put(RESOURCE.GOATS, 1);
			cost.put(RESOURCE.WOOD, 1);		
			
			return cost;
		}
	}
}
