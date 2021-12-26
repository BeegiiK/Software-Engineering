package pile;

import java.util.Hashtable;

import logistic.RESOURCE;

public class Pile {
	
	protected Hashtable<RESOURCE, Integer> pile = new Hashtable<RESOURCE, Integer>();
	
	public Pile() {
		for(RESOURCE r: RESOURCE.values()) {
			if(!r.equals(RESOURCE.NONE)) {
				pile.put(r, 0);
			}
		}
	}
	
	// decrement pile
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
	
	// check if a pile can be decremented with given int value
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

	public Hashtable<RESOURCE, Integer> getPile(){
		return pile;
	}
}
