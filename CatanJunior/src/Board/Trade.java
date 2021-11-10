package Board;

import java.util.HashMap;

public abstract class Trade {
	
	protected HashMap<String, Integer> pile = new HashMap<String, Integer>();
	
	protected void take(HashMap<String, Integer> B) {
		for(String key : B.keySet()) {
			pile.put(key, pile.get(key) + B.get(key));
			B.put(key, B.get(key) )
		}
	}
	
	protected void give(HashMap<String, Integer> B) {
		for(String key : B.keySet()) {
			if(pile.get(key) < B.get(key)) {
				System.out.println("Cannot be taken");
			}
			else {
				pile.put(key, pile.get(key) - B.get(key));
			}
		}
	}

}

//1. fix trade function
// do for only one material rather than multiple materials
// needs an amount to decrease by...
