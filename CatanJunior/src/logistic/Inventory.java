package logistic;

import java.util.Hashtable;

public enum Inventory {
	SHIP,
	LAIR;
	
	public static Hashtable<RESOURCE,Integer> cost(Inventory i){
		Hashtable<RESOURCE, Integer> cost = null;
		
		if(i.equals(Inventory.LAIR)) {
			cost.put(RESOURCE.CUTLASSES, 1);
			cost.put(RESOURCE.MOLASSES, 1);
			cost.put(RESOURCE.GOATS, 1);
			cost.put(RESOURCE.WOOD, 1);		
			
			return cost;
		}
		else {
			cost.put(RESOURCE.GOATS, 1);
			cost.put(RESOURCE.WOOD, 1);		
			
			return cost;
		}
	}
}
