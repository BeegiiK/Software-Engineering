package Model.ResourcePile;

import java.util.Hashtable;

import Enum.RESOURCE;

public class Pile {
	
	// Pile class returning a type dictionary where the key value is the resource type
	// and the corresponding value is the amount of that resource left
	protected Hashtable<RESOURCE, Integer> pile = new Hashtable<RESOURCE, Integer>();
	
	// Constructor, all resource types get zero (empty)
	public Pile() {
		for(RESOURCE r: RESOURCE.values()) {
			if(!r.equals(RESOURCE.NONE)) {
				pile.put(r, 0);
			}
		}
	}
	
	// decrement pile and checking if player resources can be decremented
	public boolean decrementPile(RESOURCE key, int value) {
		if(pile.get(key) < value) {
			System.out.println("Cannot decrement");
			return false;
		}
		else {
			pile.put(key, pile.get(key) - value);
			return true;
		}
	}
	
	// increment pile
	public boolean incrementPile(RESOURCE key, int value) {
		pile.put(key, pile.get(key) + value);
		return true;
	}
	
	// check if a pile can be decremented with given int value for resource
	public boolean checkDecrement(RESOURCE r, int amount) {
		if(pile.get(r) < amount) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "Pile [pile=" + pile + "]";
	}

	// return the pile dictionary
	public Hashtable<RESOURCE, Integer> getPile(){
		return pile;
	}
	
	// return an empty pile be calling constructor
	public Pile returnEmptyPile() {
		Pile p = new Pile();
		return p;
	}
}
